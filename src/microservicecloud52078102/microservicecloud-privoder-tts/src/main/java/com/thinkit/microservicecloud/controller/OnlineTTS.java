package com.thinkit.microservicecloud.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.thinkit.microservicecloud.entities.tts.ParamsTTS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RestController
public class OnlineTTS {


    Logger logger = LoggerFactory.getLogger(OnlineTTS.class);
    @Autowired
    private RestTemplate restTemplate;

    @Value("${onlinetts.ServerIp}")
    private  String ServerIp;

    @Value("${onlinetts.ServerPort}")
    private  String ServerPort;

  /*  @RequestMapping(value = "/service/tts", method = RequestMethod.POST)
    public void  onlinetts(@RequestBody ParamsTTS info){

        logger.info(info.toString());

        String url = "http://"+ServerIp+":"+ServerPort+"/tts.php?text="+info.getText()+"&lang="+info.getLang()+"&speaker="+info.getSpeaker()+"&speed="+info.getSpeed()+"&volume="+info.getVolume()+"";
        byte[] buff = null;
        try {
            buff = restTemplate.getForObject(url, byte[].class);
        }catch(Exception e){
            logger.error(e.getMessage());


        }
*/

     /*   System.out.println(responseEntity.getStatusCode());
        System.out.println(responseEntity.getBody());
*/
     //   logger.info("buff: "+ new String(buff));
      /*   ResponseEntity
                .ok()
                .contentLength(buff.length)
                .contentType(MediaType.parseMediaType("audio/wav"))
                .body(buff);
    }*/


/*    public ResponseEntity<> export() {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", "attachment; filename=" + System.currentTimeMillis() + ".xls");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("Last-Modified", new Date().toString());
        headers.add("ETag", String.valueOf(System.currentTimeMillis()));

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(100)
                .contentType(MediaType.parseMediaType("audio/wav"))
                .body(new byte[100]);
    }*/

    @RequestMapping(value = "/service/tts/{text}/{lang}/{speaker}/{speed}/{volume}", method = RequestMethod.GET)
    public byte[]  output(@PathVariable String text,@PathVariable String lang, @PathVariable String speaker,@PathVariable String speed,@PathVariable String volume) {

        logger.info("text:"+text+" , lang: "+lang+" , speaker: "+speaker+" , speed: "+speed+" , volume: "+volume);

        String url = "http://"+ServerIp+":"+ServerPort+"/tts.php?text="+text+"&lang="+lang+"&speaker="+speaker+"&speed="+speed+"&volume="+volume+"";
        byte[] buff = null;
        try {
            buff = restTemplate.getForObject(url, byte[].class);
        }catch(Exception e){
            logger.error(e.getMessage());

        }

     /*   response.setContentType("audio/wav");

        try {
            response.getOutputStream().write(buff);
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

     return buff;


    }


    @RequestMapping(value = "/test/{p1}/{p2}",method = RequestMethod.GET)
    public  void test(@PathVariable  String p1, @PathVariable  String p2){

        System.out.println(p1+"          "+p2);
    }
}
