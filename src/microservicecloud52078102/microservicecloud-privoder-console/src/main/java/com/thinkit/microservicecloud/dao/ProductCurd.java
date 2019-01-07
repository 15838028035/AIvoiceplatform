package com.thinkit.microservicecloud.dao;

import com.thinkit.microservicecloud.entities.console.AppService;
import com.thinkit.microservicecloud.entities.console.ServiceProduct;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductCurd {

    public List<ServiceProduct> selectProducts();

    public  ServiceProduct selectById(int id);
}
