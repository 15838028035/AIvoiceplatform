package com.thinkit.microservicecloud.dao;

import com.thinkit.microservicecloud.entities.console.BasicInfo;
import com.thinkit.microservicecloud.entities.userlogin.UserRegister;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BasicInfoCurd {

    public void insert_basicinfo(UserRegister userRegister);

}
