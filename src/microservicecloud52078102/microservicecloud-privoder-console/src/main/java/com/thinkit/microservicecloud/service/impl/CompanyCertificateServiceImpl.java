package com.thinkit.microservicecloud.service.impl;

import com.thinkit.microservicecloud.dao.CompanyCertificateCurd;
import com.thinkit.microservicecloud.entities.console.Company_Certificate;
import com.thinkit.microservicecloud.service.CompanyCerstificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyCertificateServiceImpl implements CompanyCerstificateService {

    @Autowired
    private CompanyCertificateCurd service;

    @Override
        public void create(Company_Certificate info) {

            service.create(info);
    }
}
