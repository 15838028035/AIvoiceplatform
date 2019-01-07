package com.thinkit.microservicecloud.controller;

import com.thinkit.microservicecloud.entities.console.Appdetail;
import com.thinkit.microservicecloud.entities.console.MyApps;
import com.thinkit.microservicecloud.entities.console.ReturnType;
import com.thinkit.microservicecloud.entities.console.UserApp;
import com.thinkit.microservicecloud.entities.userlogin.ResultInfo;
import com.thinkit.microservicecloud.service.IConsoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class Certificate_Controller {

    @Autowired
    private IConsoleService service;

    // 开发者认证(测试未通过，暂时feign client 不支持文件上传，调用请直接走服务提供方)
    @RequestMapping(value = "/consumer/console/personal_certificate/{session}",method = RequestMethod.POST)
    public ResultInfo person(@RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2, @PathVariable("session") String session){
        return  service.person(file1,file2,session);
    }

}
