package com.wukong.uid.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.wukong.uid.mapper.TinyIdInfoMapper;
import com.wukong.uid.service.UidGeneratorService;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@Configuration
@EnableConfigurationProperties(UidProperties.class)
@ConditionalOnProperty(prefix = "uid", name = "enabled", havingValue = "true", matchIfMissing = true)
public class UidAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public UidGeneratorService uidGeneratorService(TinyIdInfoMapper mapper, UidProperties properties) {
        return new UidGeneratorService(mapper, properties);
    }

    // @Bean
    // @ConditionalOnMissingBean
    // public UidProperties uidProperties() {
    //     return new UidProperties();
    // }
} 