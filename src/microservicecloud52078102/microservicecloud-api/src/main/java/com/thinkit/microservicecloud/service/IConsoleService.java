package com.thinkit.microservicecloud.service;

import com.thinkit.microservicecloud.entities.console.*;
import com.thinkit.microservicecloud.entities.userlogin.ResultInfo;
import com.thinkit.microservicecloud.hystrix.HystrixIConsoleService;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@FeignClient(value = "MICROSERVICECLOUD-CONSOLE",fallback= HystrixIConsoleService.class)
public interface IConsoleService {

    @RequestMapping(value = "/provider/console/createapp/{session}",method = RequestMethod.POST)
    public ReturnType createUserApp(@RequestBody UserApp info, @PathVariable("session") String session);


    @RequestMapping(value = "/provider/console/myapps/{session}",method = RequestMethod.GET)
    public List<MyApps> myapps(@PathVariable("session") String session);


    @RequestMapping(value="/privoder/console/serviceproduct/selectProducts/{session}",method = RequestMethod.GET)
    public List<ServiceProduct> selectProducts(@PathVariable("session") String session);

    @RequestMapping(value = "/provider/console/appdetail/{session}/{appid}",method = RequestMethod.GET)
    public List<Appdetail> appdetail(@PathVariable("session") String session, @PathVariable("appid") String appid);


    @RequestMapping(value = "/provider/console/delapp/{session}/{id}",method = RequestMethod.DELETE)
    public ReturnType delapp(@PathVariable("session") String session, @PathVariable("id") int id);


    // 开发者认证
    @RequestMapping(value = "/provider/console/personal_certificate/{session}",method = RequestMethod.POST)
    public ResultInfo person( @RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2, @PathVariable("session") String session);

    }
