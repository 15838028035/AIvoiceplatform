package com.thinkit.microservicecloud.dao;

import com.thinkit.microservicecloud.entities.console.Company_Certificate;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CompanyCertificateCurd {

    public  void create(Company_Certificate info);
}
