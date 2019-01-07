package com.thinkit.microservicecloud.dao;

import com.thinkit.microservicecloud.entities.console.BasicInfo;
import com.thinkit.microservicecloud.entities.console.ServiceProduct;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BasicInfoCurd {

    public void insert_basicinfo(String username,int userid);

    public  void  update_basicinfo(BasicInfo info);
}
