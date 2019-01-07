package com.thinkit.microservicecloud.service;

import com.thinkit.microservicecloud.entities.console.Appdetail;
import com.thinkit.microservicecloud.entities.console.BasicInfo;
import com.thinkit.microservicecloud.entities.console.MyApps;
import com.thinkit.microservicecloud.entities.console.UserApp;

import java.util.List;

public interface BasicInfoService {
    public void insert_basicinfo(String username,int userid);

    public  void  update_basicinfo(BasicInfo info);
}
