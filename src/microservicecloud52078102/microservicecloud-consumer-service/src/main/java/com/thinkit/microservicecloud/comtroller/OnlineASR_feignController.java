package com.thinkit.microservicecloud.comtroller;

import com.thinkit.microservicecloud.entities.onlineasr.PointCut;
import com.thinkit.microservicecloud.entities.onlineasr.RealtimeAsrReq;
import com.thinkit.microservicecloud.entities.onlineasr.VoiceRec;
import com.thinkit.microservicecloud.entities.userlogin.ResultInfo;
import com.thinkit.microservicecloud.entities.voiceprint.VoiceResult;
import com.thinkit.microservicecloud.entities.voiceprint.VoiceUser;
import com.thinkit.microservicecloud.service.IOnlineAsrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
public class OnlineASR_feignController {


    @Autowired
    private IOnlineAsrService service;


    @RequestMapping(value = "/consumer/onlineasr/rec", method = RequestMethod.POST)
    public VoiceResult rec(@RequestBody VoiceRec vrec) {
        return service.rec(vrec);
    }

    @RequestMapping(value = "/consumer/onlineasr/recording", method = RequestMethod.POST)
    public Map<String,Object> recording(@RequestBody PointCut info, HttpServletResponse response) throws  Exception{
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods","POST,GET,OPTIONS,DELETE");
        response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type");

       return   service.recording(info);

    }

    @RequestMapping(value="/consumer/service/realtimeasr",method= RequestMethod.POST )
    public ResultInfo realtimeAsr(@RequestBody RealtimeAsrReq info) {
        return service.realtimeAsr(info);
    }


    @RequestMapping(value = "consumer/https/test", method = RequestMethod.POST)
    public String rec(){
        return service.rec();
    }
}
