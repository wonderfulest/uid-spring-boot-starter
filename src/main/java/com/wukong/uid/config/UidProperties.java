package com.wukong.uid.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "tinyid")
public class UidProperties {
    private String token;
    private String bizType;

    public UidProperties() {}

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    public String getBizType() { return bizType; }
    public void setBizType(String bizType) { this.bizType = bizType; }
} 