package com.xd.cheekat.pojo;

import java.util.Date;

public class Mission {
    private Long missionId;

    private Integer type;

    private Integer sex;

    private String address;

    private Double money;

    private Long publishId;

    private Date createTime;

    private Date startTime;

    private Long acceptId;

    private Integer status;

    private Date acceptTime;

    private Date finishTime;

    private Integer anonymous;

    private String toId;

    private String recordSn;

    private Integer toType;

    private String content;

    public Long getMissionId() {
        return missionId;
    }

    public void setMissionId(Long missionId) {
        this.missionId = missionId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Long getPublishId() {
        return publishId;
    }

    public void setPublishId(Long publishId) {
        this.publishId = publishId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Long getAcceptId() {
        return acceptId;
    }

    public void setAcceptId(Long acceptId) {
        this.acceptId = acceptId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(Date acceptTime) {
        this.acceptTime = acceptTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Integer getAnonymous() {
        return anonymous;
    }

    public void setAnonymous(Integer anonymous) {
        this.anonymous = anonymous;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId == null ? null : toId.trim();
    }

    public String getRecordSn() {
        return recordSn;
    }

    public void setRecordSn(String recordSn) {
        this.recordSn = recordSn == null ? null : recordSn.trim();
    }

    public Integer getToType() {
        return toType;
    }

    public void setToType(Integer toType) {
        this.toType = toType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}