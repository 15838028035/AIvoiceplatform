package com.thinkit.microservicecloud.service.impl;

import com.thinkit.microservicecloud.dao.PersonalCertificateCurd;
import com.thinkit.microservicecloud.entities.console.Personal_Certificate;
import com.thinkit.microservicecloud.service.PersonalCerstificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonalCertificateServiceImpl implements PersonalCerstificateService {

    @Autowired
    private PersonalCertificateCurd service;
    @Override
    public void create(Personal_Certificate info) {

         service.create(info);
    }
}
