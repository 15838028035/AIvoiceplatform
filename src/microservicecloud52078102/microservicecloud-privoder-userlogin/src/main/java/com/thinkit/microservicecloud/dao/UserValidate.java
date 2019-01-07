package com.thinkit.microservicecloud.dao;

import com.thinkit.microservicecloud.entities.userlogin.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserValidate {

    public List<User> selectByPhone(String phone);


    public List<User> selectByEmail(String email);


    public List<User> selectByUserName(String username);

}
