package com.thinkit.microservicecloud.service.impl;

import com.thinkit.microservicecloud.dao.ServiceCurd;
import com.thinkit.microservicecloud.entities.console.AppService;
import com.thinkit.microservicecloud.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceServiceImpl implements ServiceService {

   @Autowired
    private ServiceCurd service;
    @Override
    public void createService(AppService info) {
           service.createService(info);
    }
}
