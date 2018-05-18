package com.xd.cheekat.pojo;

import java.io.Serializable;
import java.util.Date;

public class RedPacket implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long redpacketId;

    private String recordSn;

    private Integer type;

    private Double money;

    private Long publishId;

    private String createTime;

    private Long acceptId;

    private Integer status;

    private String acceptTime;

    private Integer toType;

    private String toId;

    private Integer payStatus;

    public Long getRedpacketId() {
        return redpacketId;
    }

    public void setRedpacketId(Long redpacketId) {
        this.redpacketId = redpacketId;
    }

    public String getRecordSn() {
        return recordSn;
    }

    public void setRecordSn(String recordSn) {
        this.recordSn = recordSn == null ? null : recordSn.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
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

    public String getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(String acceptTime) {
        this.acceptTime = acceptTime;
    }

    public Integer getToType() {
        return toType;
    }

    public void setToType(Integer toType) {
        this.toType = toType;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId == null ? null : toId.trim();
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

	@Override
	public String toString() {
		return "RedPacket [redpacketId=" + redpacketId + ", recordSn="
				+ recordSn + ", type=" + type + ", money=" + money
				+ ", publishId=" + publishId + ", createTime=" + createTime
				+ ", acceptId=" + acceptId + ", status=" + status
				+ ", acceptTime=" + acceptTime + ", toType=" + toType
				+ ", toId=" + toId + ", payStatus=" + payStatus + "]";
	}
    
    
}