package com.wukong.uid.mapper;

import com.wukong.uid.model.TinyIdInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface TinyIdInfoMapper {
    TinyIdInfo selectByBizType(@Param("bizType") String bizType);
    int updateMaxId(@Param("bizType") String bizType, @Param("newMaxId") Long newMaxId, @Param("oldMaxId") Long oldMaxId, @Param("version") Long version);
    List<String> selectAllBizType();
} 