package com.wukong.uid.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.wukong.uid.model.TinyIdInfo;

@Mapper
public interface TinyIdInfoMapper {
    TinyIdInfo selectByBizType(@Param("bizType") String bizType);
    int updateMaxId(@Param("bizType") String bizType, @Param("newMaxId") Long newMaxId, @Param("oldMaxId") Long oldMaxId, @Param("version") Long version);
} 