package com.thinkit.microservicecloud.comtroller;

import com.thinkit.microservicecloud.entities.tts.ParamsTTS;
import com.thinkit.microservicecloud.entities.voiceprint.VoiceResult;
import com.thinkit.microservicecloud.entities.voiceprint.VoiceUser;
import com.thinkit.microservicecloud.service.IOnlineTTSService;
import com.thinkit.microservicecloud.service.IVoicePrintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class OnlineTTS_feignController {

    @Autowired
    private IOnlineTTSService service;

    @RequestMapping(value = "/consumer/service/tts/{text}/{lang}/{speaker}/{speed}/{volume}", method = RequestMethod.GET)
    public void  output(@PathVariable String text, @PathVariable String lang, @PathVariable String speaker, @PathVariable String speed, @PathVariable String volume,HttpServletResponse response){

       byte[] buff = service.output(text,lang,speaker,speed,volume);

       response.setContentType("audio/wav");
        try {
            response.getOutputStream().write(buff);
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }

}




}
