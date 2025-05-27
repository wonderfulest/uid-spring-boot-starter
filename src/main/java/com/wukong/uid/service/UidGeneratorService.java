package com.wukong.uid.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wukong.uid.config.UidProperties;
import com.wukong.uid.mapper.TinyIdInfoMapper;
import com.wukong.uid.model.TinyIdInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UidGeneratorService {
    private final TinyIdInfoMapper tinyIdInfoMapper;
    private final UidProperties properties;
    private List<Long> idPool = new ArrayList<>();

    @Autowired
    public UidGeneratorService(TinyIdInfoMapper tinyIdInfoMapper, UidProperties properties) {
        this.tinyIdInfoMapper = tinyIdInfoMapper;
        this.properties = properties;
        loadSegment();
    }

    public synchronized long getId() {
        if (idPool.size() < 100) {
            loadSegment();
        }
        return idPool.remove(idPool.size() - 1);
    }

    private void loadSegment() {
        System.out.println("loadSegment");
        String bizType = properties.getBizType();
        for (;;) {

            TinyIdInfo info = tinyIdInfoMapper.selectByBizType(bizType);
            long oldMaxId = info.getMaxId();
            long newMaxId = oldMaxId + info.getStep();
            
            int updated = tinyIdInfoMapper.updateMaxId(bizType, newMaxId, oldMaxId, info.getVersion());
            if (updated == 1) {
                List<Long> newPool = new ArrayList<>();
                for (long i = oldMaxId; i < newMaxId; i++) {
                    newPool.add(i);
                }
                Collections.shuffle(newPool);
                this.idPool = newPool;
                break;
            }
            // 并发下CAS失败，重试
        }
    }
} 