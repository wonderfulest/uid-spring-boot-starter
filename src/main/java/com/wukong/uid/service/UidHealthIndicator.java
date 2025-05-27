package com.wukong.uid.service;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import com.wukong.uid.mapper.TinyIdInfoMapper;

@Component
public class UidHealthIndicator extends AbstractHealthIndicator {
    private final TinyIdInfoMapper tinyIdInfoMapper;

    public UidHealthIndicator(TinyIdInfoMapper tinyIdInfoMapper) {
        this.tinyIdInfoMapper = tinyIdInfoMapper;
    }

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        try {
            // 检查是否能查到至少一个号段
            if (tinyIdInfoMapper.selectByBizType("order_id") != null) {
                builder.up().withDetail("uid", "tiny_id_info available");
            } else {
                builder.down().withDetail("uid", "no biz_type found");
            }
        } catch (Exception e) {
            builder.down(e);
        }
    }
} 