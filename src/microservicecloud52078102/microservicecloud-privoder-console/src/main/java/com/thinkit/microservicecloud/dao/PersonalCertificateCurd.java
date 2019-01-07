package com.thinkit.microservicecloud.dao;

import com.thinkit.microservicecloud.entities.console.Personal_Certificate;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PersonalCertificateCurd {

    public  void create(Personal_Certificate info);
}
