package com.thinkit.microservicecloud.service;


import com.thinkit.microservicecloud.entities.onlineasr.PointCut;
import com.thinkit.microservicecloud.entities.onlineasr.RealtimeAsrReq;
import com.thinkit.microservicecloud.entities.onlineasr.VoiceRec;
import com.thinkit.microservicecloud.entities.userlogin.ResultInfo;
import com.thinkit.microservicecloud.entities.voiceprint.VoiceResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@FeignClient(value = "MICROSERVICECLOUD-ONLINEASR")
public interface IOnlineAsrService {

    @RequestMapping(value = "/onlineasr/rec", method = RequestMethod.POST)
    public VoiceResult rec(@RequestBody VoiceRec vrec) ;


    @RequestMapping(value = "/onlineasr/recording", method = RequestMethod.POST)
    public Map<String,Object> recording(@RequestBody PointCut info) ;

    @RequestMapping(value="/provider/service/realtimeasr",method= RequestMethod.POST )
    public ResultInfo realtimeAsr(@RequestBody RealtimeAsrReq info) ;

    @RequestMapping(value = "/https/test", method = RequestMethod.POST)
    public String rec();
}
