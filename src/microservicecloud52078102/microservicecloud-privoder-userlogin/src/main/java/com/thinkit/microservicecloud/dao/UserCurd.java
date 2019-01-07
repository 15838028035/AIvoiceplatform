package com.thinkit.microservicecloud.dao;

import com.thinkit.microservicecloud.entities.userlogin.PhonePassword;
import com.thinkit.microservicecloud.entities.userlogin.User;
import com.thinkit.microservicecloud.entities.userlogin.UserLogin;
import com.thinkit.microservicecloud.entities.userlogin.UserRegister;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserCurd {

    public int  insert(UserRegister info);


    public List<User> userLogin(UserLogin userLogin);

    public void resetPassword(PhonePassword info);


}
