package com.thinkit.microservicecloud.controller;

import com.thinkit.microservicecloud.entities.console.ServiceProduct;
import com.thinkit.microservicecloud.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ServiceProductController {

    @Autowired
    private ProductServiceImpl service;

    @RequestMapping(value="/privoder/console/serviceproduct/selectProducts/{session}",method = RequestMethod.GET)
    public List<ServiceProduct> selectProducts(){
        return  service.selectProducts();
    }
}
