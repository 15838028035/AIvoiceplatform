package com.thinkit.microservicecloud.service;

import com.thinkit.microservicecloud.entities.userlogin.PhonePassword;
import com.thinkit.microservicecloud.entities.userlogin.User;
import com.thinkit.microservicecloud.entities.userlogin.UserLogin;
import com.thinkit.microservicecloud.entities.userlogin.UserRegister;

import java.util.List;

public interface UserCurdService {

    public void  insert(UserRegister info);

    public List<User> userLogin(UserLogin userLogin);

    public void resetPassword(PhonePassword info);
}
