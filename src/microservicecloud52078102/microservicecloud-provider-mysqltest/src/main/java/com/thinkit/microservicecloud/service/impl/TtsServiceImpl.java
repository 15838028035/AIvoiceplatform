package com.thinkit.microservicecloud.service.impl;

import com.thinkit.microservicecloud.dao.TtsDao;
import com.thinkit.microservicecloud.entities.TtsInfo;
import com.thinkit.microservicecloud.service.Interface_tts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TtsServiceImpl implements Interface_tts {


    @Autowired
    private TtsDao dao;

    @Override
    public boolean insert(TtsInfo tts) {
        return dao.insert(tts);
    }

    @Override
    public List<TtsInfo> findall() {

       return dao.findall();

    }

    @Override
    public TtsInfo findbyId(Long id) {
        return dao.findbyId(id);
    }
}
