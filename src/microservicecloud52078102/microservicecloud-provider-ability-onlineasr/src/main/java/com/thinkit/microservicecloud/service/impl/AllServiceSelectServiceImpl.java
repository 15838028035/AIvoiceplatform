package com.thinkit.microservicecloud.service.impl;

import com.thinkit.microservicecloud.dao.ServiceMapper;
import com.thinkit.microservicecloud.entities.AuthInfo;
import com.thinkit.microservicecloud.service.AllServiceSelectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AllServiceSelectServiceImpl implements AllServiceSelectService {

    @Autowired
    private ServiceMapper serviceMapper;

    @Override
    public List<AuthInfo> selectFromService() {
        return serviceMapper.selectFromService();
    }
}
