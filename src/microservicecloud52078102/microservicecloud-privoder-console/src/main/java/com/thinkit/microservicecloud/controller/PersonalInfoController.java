package com.thinkit.microservicecloud.controller;

import com.thinkit.microservicecloud.entities.console.Personal_Certificate;
import com.thinkit.microservicecloud.entities.userlogin.ResultInfo;
import com.thinkit.microservicecloud.service.impl.PersonalCertificateServiceImpl;
import com.thinkit.microservicecloud.util.GenerateSessionId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.Result;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
public class PersonalInfoController {

    private Logger logger = LoggerFactory.getLogger(PersonalInfoController.class);

    @Value("${personal_certificate.filepath}")
    private  String personal_filepath;

    @Autowired
    private PersonalCertificateServiceImpl service;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @RequestMapping(value = "/provider/console/personal_certificate/{session}",method = RequestMethod.POST)
    public ResultInfo person(HttpServletRequest request, @RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2, @PathVariable("session") String session){

        ResultInfo result = new ResultInfo();
        result.setLogin("");
        result.setResult("");

        selectDb(0);
        String userid =  getValue(session);
        if(userid==null || userid.equals("")){
            result.setCode("1002");
            result.setMsg("session 校验失败，请重新登录");

            return result;
        }

        String id_number =  request.getParameter("id_number");
        String realName =  request.getParameter("realName");
        String address =  request.getParameter("address");


        if(id_number.equals("") || realName.equals("") || address.equals("") || id_number == null || realName==null  || address==null){
            result.setCode("1002");
            result.setMsg("参数不合法");
            return result;
        }


        logger.info("selectDb 0, session --> userid: "+session+"-->"+userid);
        logger.info(file1.getOriginalFilename());
        logger.info(file2.getOriginalFilename());

       String file1_name =  GenerateSessionId.getSid()+file1.getOriginalFilename().substring(file1.getOriginalFilename().lastIndexOf("."),file1.getOriginalFilename().length());
       String file2_name =  GenerateSessionId.getSid()+file2.getOriginalFilename().substring(file2.getOriginalFilename().lastIndexOf("."),file2.getOriginalFilename().length());

        logger.info(file1_name);
        logger.info(file2_name);
        try {
            uploadfile(file1.getBytes(),personal_filepath, file1_name);
            uploadfile(file2.getBytes(),personal_filepath, file2_name);
        } catch (IOException e) {
            e.printStackTrace();
        }


        Personal_Certificate person = new Personal_Certificate();
        person.setID_number(id_number);
        person.setRealName(realName);
        person.setAddress(address);
        if(!userid.equals("") && userid!=null){
            person.setUser_id(Integer.parseInt(userid));
        }
        person.setPhoto1(personal_filepath+file1_name);
        person.setPhoto2(personal_filepath+file2_name);


        service.create(person);

        result.setCode("1001");
        result.setMsg("已提交审核");
        return result;
    }




    public  void  uploadfile(byte[] file , String  filepath, String filename){
        File target = new File(filepath);
        if(!target.exists()){
            target.mkdirs();
        }

        FileOutputStream out = null;
        try {
            out = new FileOutputStream(filepath+filename);
            out.write(file);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public  String  getValue(String key){

        return redisTemplate.opsForValue().get(key);

    }

    public void selectDb(int dbindex){

        JedisConnectionFactory jedisConnectionFactory  = ( JedisConnectionFactory )  redisTemplate.getConnectionFactory();
        jedisConnectionFactory.setDatabase(dbindex);
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
    }

}
