package com.thinkit.microservicecloud.dao;

import com.thinkit.microservicecloud.entities.console.AppService;
import com.thinkit.microservicecloud.entities.console.UserApp;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ServiceCurd {

   public  void createService(AppService info);
}
