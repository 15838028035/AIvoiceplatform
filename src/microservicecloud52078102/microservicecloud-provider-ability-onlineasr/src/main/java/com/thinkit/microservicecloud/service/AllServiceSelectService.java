package com.thinkit.microservicecloud.service;

import com.thinkit.microservicecloud.entities.AuthInfo;
import java.util.List;

public interface AllServiceSelectService {
    public List<AuthInfo> selectFromService();
}
