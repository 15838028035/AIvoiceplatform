package com.thinkit.microservicecloud.dao;

import com.thinkit.microservicecloud.entities.AuthInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/*
  查询数据库中 所有服务的授权信息，之后加载到分布式缓存redis中去
 */
@Mapper
public interface ServiceMapper {
    public List<AuthInfo> selectFromService();
}
