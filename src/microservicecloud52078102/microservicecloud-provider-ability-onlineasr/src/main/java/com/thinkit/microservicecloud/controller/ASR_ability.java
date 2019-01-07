package com.thinkit.microservicecloud.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.thinkit.microservicecloud.entities.Results;
import com.thinkit.microservicecloud.entities.TokenJson;
import com.thinkit.microservicecloud.entities.ability.asr.RequestAsrBody;
import com.thinkit.microservicecloud.entities.ability.asr.ResponseAsrBody;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.List;

@RestController
public class ASR_ability {

    @Value("${audiokeeper.ip}")
    private String audiokeeper_ip;

    @Value("${audiokeeper.port}")
    private int audiokeeper_port;

    @Value("${audiokeeper.connect_timeout}")
    private int connect_timeout;

    @Value("${audiokeeper.read_timeout}")
    private int read_timeout;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    private Logger logger = LoggerFactory.getLogger(ASR_ability.class);

    @RequestMapping(value = "/provider/ability/asrbak",method = RequestMethod.POST)
    public ResponseAsrBody asr(@RequestBody RequestAsrBody info){

        logger.info("请求参数："+info.toString());

        ResponseAsrBody responseAsrBody = new ResponseAsrBody(0,"success","");

        // 音频格式format 和 采样率rate(默认8000) 暂不做处理


        if(info.getCuid()==null || info.getSid()==null || info.getData()==null || info.getAuth()==null|| info.getFormat()==null){
            responseAsrBody.setErr_code(1001); // 1001 输入参数不正确
            responseAsrBody.setErr_msg("输入参数不正确");
            return  responseAsrBody;
        }

        if(info.getCuid().trim().equals("")|| info.getSid().trim().equals("") || info.getAuth().trim().equals("") || info.getFormat().equals("") || info.getIdx()<=0 || info.getData().length==0 ) {
            responseAsrBody.setErr_code(1001); // 1001 输入参数不正确
            responseAsrBody.setErr_msg("输入参数不正确");
            return responseAsrBody;
        }

        synchronized (this){
            String value = get(info.getAuth());
            if(value==null){
                responseAsrBody.setErr_code(1002); // 1002 鉴权失败
                responseAsrBody.setErr_msg("鉴权失败");
                return  responseAsrBody;
            }

            TokenJson tokenJson = JSON.parseObject(value,new TypeReference<TokenJson>(){});

            // 根据idx来判断是不是新的线程访问
            if(info.getIdx()==1){
                // 判断是否超过访问线程数
                if(tokenJson.getAccess_thread()>=tokenJson.getAuth_thread()){
                    logger.warn("用户请求QPS超过限制，cuid: "+info.getCuid()+",您已超过访问线程数-H: "+tokenJson.toString());
                    responseAsrBody.setErr_code(1003); // 1003 用户请求QPS超限
                    responseAsrBody.setErr_msg("用户请求QPS超限");
                    return  responseAsrBody;
                }else {
                    tokenJson.setAccess_thread(tokenJson.getAccess_thread()+1);
                    tokenJson.getAccess_sids().add(info.getSid());
                    set(info.getAuth(),JSON.toJSONString(tokenJson));
                    logger.info("redis 增加线程 sid:"+info.getSid());
                    logger.info(tokenJson.toString());
                }
            }else{

                boolean isExist = false;
                List<String> access_sids = tokenJson.getAccess_sids();
                for(String access_id : access_sids){
                    if(access_id.equals(info.getSid())){
                        logger.debug("该语音包为中间包");
                        isExist = true;
                        break;
                    }
                }


                if(!isExist){

                    if(tokenJson.getAccess_thread()>=tokenJson.getAuth_thread()){
                        logger.warn("用户请求QPS超过限制，cuid: "+info.getCuid()+",您已超过访问线程数-M: "+tokenJson.toString());
                        responseAsrBody.setErr_code(1003); // 1003 用户请求QPS超限
                        responseAsrBody.setErr_msg("用户请求QPS超限");
                        return  responseAsrBody;
                    }

                    responseAsrBody.setErr_code(1003); // 1003 用户请求QPS超限
                    responseAsrBody.setErr_msg("用户请求QPS超限");
                    return  responseAsrBody; //"中间数据包非法传入"
                }

            }

            // 根据来判islast断是不是最后一包
            if(info.isIslast()){
                tokenJson.setAccess_thread(tokenJson.getAccess_thread()-1);
                tokenJson.getAccess_sids().remove(info.getSid());
                set(info.getAuth(),JSON.toJSONString(tokenJson));
                logger.info("redis 已清除 sid:"+info.getSid());
                logger.info(tokenJson.toString());
            }
        }


        if(info.getData().length>2000){
            responseAsrBody.setErr_code(1004); // 1003 用户请求QPS超限
            responseAsrBody.setErr_msg("用户输入每包音频过长");
            return  responseAsrBody;
        }



        String result = "";
        if(info.isIslast()) {
            String url = "http://ip:port/asr?sid="+info.getSid()+"&idx="+info.getIdx()+"&islast=1";
            result =  connetEngine(url,info.getData());

        }else {
            String url = "http://ip:port/asr?sid="+info.getSid()+"&idx="+info.getIdx()+"&islast=0";
            result = connetEngine(url,info.getData());
        }


        if(result.equals("cannot connect audiokeeper")){
            responseAsrBody.setErr_code(1011); // 1003 用户请求QPS超限
            responseAsrBody.setErr_msg("连接识别服务超时");
            return  responseAsrBody;
        }


        if(result.contains("xml")){

            try {
               Results results = (Results) xmlStrToOject(Results.class,result);

               if(results.getErrors()!=null)
                   System.out.println("desciption:" + results.getErrors().getDescription());
               if(results.getResult()!=null) {
                   responseAsrBody.setResult(results.getResult().getPhoneme());


                   System.out.println("time:" + results.getResult().getTime());
               }
                return responseAsrBody;

            } catch (Exception e) {
                e.printStackTrace();
            }

        }


        responseAsrBody.setResult(result);
        return responseAsrBody;
    }


    public String get(String token){

        String value = redisTemplate.opsForValue().get(token);
        return value;
    }

    public void set(String token,String value){
        redisTemplate.opsForValue().set(token,value);
    }

    public    byte[] intToByteArray(int i) {
        byte[] result = new byte[4];
        result[0] = (byte) (i & 0xff);
        result[1] = (byte) (i >> 8 & 0xff);
        result[2] = (byte) (i >> 16 & 0xff);
        result[3] = (byte) (i >> 24 & 0xff);
        return result;
    }

    public  String connetEngine(String url,byte[] audio) {

        Socket client=null;
        DataOutputStream dos=null;
        DataInputStream dis=null;

        try {
            //client = new Socket(audiokeeper_ip, audiokeeper_port);
              client = new Socket();
              SocketAddress socketAddress = new InetSocketAddress(audiokeeper_ip, audiokeeper_port);
              client.connect(socketAddress, connect_timeout);//连不上的 connect_timeout 毫秒断掉连接

            // 响应阻塞超时，是client和服务端建立连接后，等待接收数据的超时时间
            client.setSoTimeout(read_timeout);
            dos = new DataOutputStream(client.getOutputStream());

            //第一次写出，0，url长度，audio长度
            byte[] firstLen       =  intToByteArray(0);
            byte[] urlLen         =  intToByteArray(url.getBytes().length);
            byte[] sendDataLen    =  intToByteArray(audio.length);
            byte[] dataAll = new byte[12];

            System.arraycopy(firstLen,0,dataAll,0,firstLen.length);
            System.arraycopy(urlLen,0,dataAll,firstLen.length,urlLen.length);
            System.arraycopy(sendDataLen,0,dataAll,urlLen.length+firstLen.length,sendDataLen.length);

            dos.write(dataAll);//发送长度
            dos.flush();

            //第二次写出，url
            dos.write(url.getBytes());
            dos.flush();

            //第三次写出，audio
            dos.write(audio);
            dos.flush();

//	            logger.info("任务下发引擎成功,等待返回结果...");

            dis=new DataInputStream(client.getInputStream());
            StringBuffer stringBuffer = new StringBuffer();
            byte[] bytes = new byte[1024];

            int length = 0;

            while((length = dis.read(bytes)) != -1){
                stringBuffer.append(new String(bytes,0,length));
            }



            logger.info("引擎返回结果:" + stringBuffer.toString());
            return stringBuffer.toString();

        }catch (Exception e) {
            logger.error("【!!!error】socket 连接异常!!!");
            return "cannot connect audiokeeper";
            //e.printStackTrace();
        }finally{
            try {
                if(null != dis){
                    dis.close();
                }
                if(null != dos){
                    dos.close();
                }
                if(null != client){
                    client.close();
                }
            } catch (Exception e) {
                logger.error("【!!!error】关闭流或socket失败");
            }
        }


    }

    /**
     * 将XML转为指定的POJO
     * @param clazz
     * @param xmlStr
     * @return
     * @throws Exception
     */
    public static Object xmlStrToOject(Class<?> clazz, String xmlStr) throws Exception {
        Object xmlObject = null;
        Reader reader = null;
        JAXBContext context = JAXBContext.newInstance(clazz);
        // XML 转为对象的接口
        Unmarshaller unmarshaller = context.createUnmarshaller();
        reader = new StringReader(xmlStr);
        //以文件流的方式传入这个string
        xmlObject = unmarshaller.unmarshal(reader);
        if (null != reader) {
            reader.close();
        }
        return xmlObject;
    }


}
