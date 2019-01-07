package com.thinkit.hystrix;

import com.thinkit.microservicecloud.entities.console.*;
import com.thinkit.microservicecloud.entities.userlogin.ResultInfo;
import com.thinkit.microservicecloud.service.IConsoleService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class HystrixIConsoleService implements IConsoleService {
    @Override
    public ReturnType createUserApp(UserApp info, String session) {
        return null;
    }

    @Override
    public List<MyApps> myapps(String session) {
        return null;
    }

    @Override
    public List<ServiceProduct> selectProducts(String session) {
        return null;
    }

    @Override
    public List<Appdetail> appdetail(String session, String appid) {
        return null;
    }

    @Override
    public ReturnType delapp(String session, int id) {
        return null;
    }

    @Override
    public ResultInfo person(MultipartFile file1, MultipartFile file2, String session) {
        return null;
    }
}
