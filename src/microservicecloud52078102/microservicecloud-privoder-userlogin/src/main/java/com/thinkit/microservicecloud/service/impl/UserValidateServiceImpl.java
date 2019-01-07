package com.thinkit.microservicecloud.service.impl;

import com.thinkit.microservicecloud.dao.UserValidate;
import com.thinkit.microservicecloud.entities.userlogin.User;
import com.thinkit.microservicecloud.service.UserValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserValidateServiceImpl implements UserValidateService {


    @Autowired
    private UserValidate dao;

    @Override
    public List<User> selectByPhone(String phone) {
        return dao.selectByPhone(phone);
    }

    @Override
    public List<User> selectByEmail(String email) {
        return dao.selectByEmail(email);
    }

    @Override
    public List<User> selectByUserName(String username) {
        return dao.selectByUserName(username);
    }
}
