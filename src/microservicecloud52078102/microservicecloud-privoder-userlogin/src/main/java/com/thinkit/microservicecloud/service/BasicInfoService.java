package com.thinkit.microservicecloud.service;

import com.thinkit.microservicecloud.entities.console.BasicInfo;
import com.thinkit.microservicecloud.entities.userlogin.UserRegister;

public interface BasicInfoService {
    public void insert_basicinfo(UserRegister userRegister);


}
