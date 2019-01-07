package com.thinkit.microservicecloud.dao;

import com.thinkit.microservicecloud.entities.TtsInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TtsDao {

    public  boolean insert(TtsInfo tts);

    public List<TtsInfo> findall();

    public  TtsInfo findbyId(Long id);
}
