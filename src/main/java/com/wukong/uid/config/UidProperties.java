package com.wukong.uid.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "tinyid")
public class UidProperties {
    private String token;

    public UidProperties() {}

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
} 