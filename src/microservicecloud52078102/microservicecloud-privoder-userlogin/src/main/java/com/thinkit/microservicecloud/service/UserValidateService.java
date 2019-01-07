package com.thinkit.microservicecloud.service;

import com.thinkit.microservicecloud.entities.userlogin.User;

import java.util.List;

public interface UserValidateService {

    public List<User> selectByPhone(String phone);

    public List<User> selectByEmail(String email);


    public List<User> selectByUserName(String username);
}
