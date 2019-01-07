package com.thinkit.microservicecloud.service;

import com.thinkit.microservicecloud.entities.TtsInfo;

import java.util.List;

public interface Interface_tts {

    public  boolean insert(TtsInfo tts);

    public List<TtsInfo> findall();

    public  TtsInfo findbyId(Long id);
}
