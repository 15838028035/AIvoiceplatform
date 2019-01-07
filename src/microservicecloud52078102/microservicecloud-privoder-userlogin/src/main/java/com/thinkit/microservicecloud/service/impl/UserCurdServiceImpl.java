package com.thinkit.microservicecloud.service.impl;

import com.thinkit.microservicecloud.dao.BasicInfoCurd;
import com.thinkit.microservicecloud.dao.UserCurd;
import com.thinkit.microservicecloud.entities.userlogin.PhonePassword;
import com.thinkit.microservicecloud.entities.userlogin.User;
import com.thinkit.microservicecloud.entities.userlogin.UserLogin;
import com.thinkit.microservicecloud.entities.userlogin.UserRegister;
import com.thinkit.microservicecloud.service.UserCurdService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserCurdServiceImpl implements UserCurdService {

    private Logger logger = LoggerFactory.getLogger(UserCurdServiceImpl.class);
    @Autowired
    private UserCurd dao;

    @Autowired
    private BasicInfoCurd basicInfoCurd;

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    //Propagation.REQUIRED 传播特性 如果当前存在事务，则加入该事务；如果当前没有事务，则创建一个新的事务。这是默认值。
    //Isolation.DEFAULT  默认的隔离级别是 读提交 read_committed
    public void insert(UserRegister info) {
        logger.info("start");
        dao.insert(info);
        basicInfoCurd.insert_basicinfo(info);
        logger.info("end");
    }

    @Override
    public List<User> userLogin(UserLogin userLogin) {
        return dao.userLogin(userLogin);
    }

    @Override
    public void resetPassword(PhonePassword info) {
        dao.resetPassword(info);
    }
}
