package com.wukong.uid.service;

import com.wukong.uid.mapper.TinyIdInfoMapper;
import com.wukong.uid.model.TinyIdInfo;
import com.wukong.uid.config.UidProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class UidGeneratorService {
    private final TinyIdInfoMapper tinyIdInfoMapper;
    private final UidProperties properties;
    private Map<String, List<Long>> idPool = new HashMap<>();

    @Autowired
    public UidGeneratorService(TinyIdInfoMapper tinyIdInfoMapper, UidProperties properties) {
        this.tinyIdInfoMapper = tinyIdInfoMapper;
        this.properties = properties;
        List<String> bizTypes = tinyIdInfoMapper.selectAllBizType();
        for (String bizType : bizTypes) {
            loadSegment(bizType);
        }
    }

    public synchronized long getId(String bizType) {
        if (idPool.isEmpty()) {
            loadSegment(bizType);
        }
        return idPool.get(bizType).remove(idPool.get(bizType).size() - 1);
    }

    private void loadSegment(String bizType) {
        System.out.println("loadSegment");
        for (;;) {
            TinyIdInfo info = tinyIdInfoMapper.selectByBizType(bizType);
            if (info == null) {
                throw new IllegalStateException("未找到 bizType=" + bizType + " 的号段配置，请先初始化 tiny_id_info 表数据！");
            }
            if (info.getDelta() < 1) {
                throw new IllegalStateException("delta 不能小于 1");
            }
            long oldMaxId = info.getMaxId();
            long newMaxId = oldMaxId + info.getStep();
            
            int updated = tinyIdInfoMapper.updateMaxId(bizType, newMaxId, oldMaxId, info.getVersion());
            if (updated == 1) {
                List<Long> newPool = new ArrayList<>();
                for (long i = oldMaxId; i < newMaxId; ) {
                    newPool.add(i);
                    if (info.getDelta() == 1) {
                        i++;
                    } else {
                        i += ThreadLocalRandom.current().nextInt(1, info.getDelta()); // 包含 1 和 info.getDelta()
                    }
                }
                Collections.shuffle(newPool);
                this.idPool.put(bizType, newPool);
                break;
            }
            // 并发下CAS失败，重试
        }
    }
} 