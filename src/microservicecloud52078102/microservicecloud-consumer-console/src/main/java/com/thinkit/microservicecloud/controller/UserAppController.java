package com.thinkit.microservicecloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.thinkit.microservicecloud.entities.console.Appdetail;
import com.thinkit.microservicecloud.entities.console.MyApps;
import com.thinkit.microservicecloud.entities.console.ReturnType;
import com.thinkit.microservicecloud.entities.console.UserApp;
import com.thinkit.microservicecloud.service.IConsoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserAppController {

    @Autowired
    private IConsoleService service;

    @RequestMapping(value = "/consumer/console/myapps/{session}",method = RequestMethod.GET)
    public List<MyApps> myapps(@PathVariable("session") String session){

      return   service.myapps(session);
    }


    @RequestMapping(value = "/consumer/console/createapp/{session}",method = RequestMethod.POST)
    public ReturnType createUserApp(@RequestBody UserApp info, @PathVariable String session){
        return  service.createUserApp(info,session);
    }

    @RequestMapping(value = "/consumer/console/appdetail/{session}/{appid}",method = RequestMethod.GET)
   // @HystrixCommand(fallbackMethod = "hystrix")
    public List<Appdetail> appdetail(@PathVariable("session") String session, @PathVariable("appid") String appid){
        return service.appdetail(session,appid);
    }


 /*   public List<Appdetail>  hystrix(@PathVariable("session") String session, @PathVariable("appid") String appid){
        System.out.println("ok hystrix");
       return null;
    }*/

    @RequestMapping(value = "/consumer/console/delapp/{session}/{id}",method = RequestMethod.DELETE)
    public ReturnType delapp(@PathVariable("session") String session, @PathVariable("id") int id){
        return  service.delapp(session,id);
    }
}
