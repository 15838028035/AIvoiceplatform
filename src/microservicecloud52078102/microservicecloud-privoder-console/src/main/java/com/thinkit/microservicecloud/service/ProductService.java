package com.thinkit.microservicecloud.service;

import com.thinkit.microservicecloud.entities.console.ServiceProduct;
import com.thinkit.microservicecloud.entities.console.UserApp;

import java.util.List;

public interface ProductService {
    public List<ServiceProduct> selectProducts();

    public  ServiceProduct selectById(int id);
}
