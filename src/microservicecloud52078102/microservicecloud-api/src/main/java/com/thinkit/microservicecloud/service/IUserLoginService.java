package com.thinkit.microservicecloud.service;


import com.thinkit.microservicecloud.entities.onlineasr.VoiceRec;
import com.thinkit.microservicecloud.entities.userlogin.*;
import com.thinkit.microservicecloud.entities.voiceprint.VoiceResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "MICROSERVICECLOUD-USERLOGIN")
public interface IUserLoginService {


    @RequestMapping(value = "/privoder/open/code/verification/{phone}",method = RequestMethod.GET)
    public String sendVerificationCode(@PathVariable("phone") String phone);

    @RequestMapping(value = "/user/login/forgetpassword/firstStepValidatePhoneCode",method = RequestMethod.POST)
    public ResultInfo firstStepValidatePhoneCode(@RequestBody PhoneCode info);

    @RequestMapping(value = "/user/login/forgetpassword/secondStepValidatePhoneCode",method = RequestMethod.POST)
    public ResultInfo  secondStepValidatePhoneCode(@RequestBody PhonePassword info);

    @RequestMapping(value ="/privoder/userRegister", method= RequestMethod.POST)
    public ResultInfo register(@RequestBody UserRegister info);


    @RequestMapping(value = "/privoder/userLogin" , method = RequestMethod.POST)
    public ResultInfo userLogin(@RequestBody UserLogin userLogin);

    @RequestMapping(value = "/privoder/userPhoneLogin" , method = RequestMethod.POST)
    public ResultInfo userPhoneLogin(@RequestBody PhoneCode info);

    @RequestMapping(value = "/privoder/userloginOut/{session}" , method = RequestMethod.GET)
    public  ResultInfo loginOut(@PathVariable("session") String session);

    @RequestMapping(value = "/privoder/userinfo/phone/{phone}",method = RequestMethod.GET)
    public ResultInfo validatePhone(@PathVariable("phone") String phone);

    @RequestMapping(value = "/privoder/userinfo/email/{email}",method = RequestMethod.GET)
    public ResultInfo validateEmail(@PathVariable("email") String email);

    @RequestMapping(value = "/privoder/userinfo/username/{username}",method = RequestMethod.GET)
    public ResultInfo validateUserName(@PathVariable("username") String username);
}
