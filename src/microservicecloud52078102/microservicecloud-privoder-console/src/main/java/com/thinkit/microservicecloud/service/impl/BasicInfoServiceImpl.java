package com.thinkit.microservicecloud.service.impl;

import com.thinkit.microservicecloud.dao.BasicInfoCurd;
import com.thinkit.microservicecloud.entities.console.BasicInfo;
import com.thinkit.microservicecloud.service.BasicInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasicInfoServiceImpl implements BasicInfoService {

    @Autowired
    private BasicInfoCurd basicInfoCurd;

    @Override
    public void insert_basicinfo(String username, int userid) {
        basicInfoCurd.insert_basicinfo(username,userid);
    }

    @Override
    public void update_basicinfo(BasicInfo info) {

        basicInfoCurd.update_basicinfo(info);
    }
}
