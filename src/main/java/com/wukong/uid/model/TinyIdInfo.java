package com.wukong.uid.model;

public class TinyIdInfo {
    private Long id;
    private String bizType;
    private Long maxId;
    private Integer step;
    private Long version;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getBizType() { return bizType; }
    public void setBizType(String bizType) { this.bizType = bizType; }
    public Long getMaxId() { return maxId; }
    public void setMaxId(Long maxId) { this.maxId = maxId; }
    public Integer getStep() { return step; }
    public void setStep(Integer step) { this.step = step; }
    public Long getVersion() { return version; }
    public void setVersion(Long version) { this.version = version; }
} 