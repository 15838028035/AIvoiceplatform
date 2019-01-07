package com.thinkit.microservicecloud.service.impl;

import com.thinkit.microservicecloud.dao.ProductCurd;
import com.thinkit.microservicecloud.entities.console.ServiceProduct;
import com.thinkit.microservicecloud.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductCurd service;

    @Override
    public List<ServiceProduct> selectProducts() {
        return service.selectProducts();
    }

    @Override
    public ServiceProduct selectById(int id) {
        return service.selectById(id);
    }
}
