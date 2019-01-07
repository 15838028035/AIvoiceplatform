package com.thinkit.microservicecloud.dao;

import com.thinkit.microservicecloud.entities.console.Appdetail;
import com.thinkit.microservicecloud.entities.console.MyApps;
import com.thinkit.microservicecloud.entities.console.ReturnType;
import com.thinkit.microservicecloud.entities.console.UserApp;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AppCurd {

    public void createApp(UserApp info);

    public List<MyApps> myapps(int userid);

    public List<Appdetail>  appdetail(String appid);

    public void delapp(int id);
    public void delapp_service(int id);
}
