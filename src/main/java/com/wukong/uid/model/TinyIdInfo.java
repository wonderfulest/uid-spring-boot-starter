package com.wukong.uid.model;

import java.sql.Timestamp;

public class TinyIdInfo {
    private Long id;
    private String bizType;
    private Long beginId;
    private Long maxId;
    private Integer step;
    private Integer delta;
    private Integer remainder;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Long version;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getBizType() { return bizType; }
    public void setBizType(String bizType) { this.bizType = bizType; }
    public Long getBeginId() { return beginId; }
    public void setBeginId(Long beginId) { this.beginId = beginId; }
    public Long getMaxId() { return maxId; }
    public void setMaxId(Long maxId) { this.maxId = maxId; }
    public Integer getStep() { return step; }
    public void setStep(Integer step) { this.step = step; }
    public Integer getDelta() { return delta; }
    public void setDelta(Integer delta) { this.delta = delta; }
    public Integer getRemainder() { return remainder; }
    public void setRemainder(Integer remainder) { this.remainder = remainder; }
    public Timestamp getCreateTime() { return createTime; }
    public void setCreateTime(Timestamp createTime) { this.createTime = createTime; }
    public Timestamp getUpdateTime() { return updateTime; }
    public void setUpdateTime(Timestamp updateTime) { this.updateTime = updateTime; }
    public Long getVersion() { return version; }
    public void setVersion(Long version) { this.version = version; }
} 