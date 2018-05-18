package com.xd.cheekat.pojo;

import java.io.Serializable;
import java.util.Date;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion.User;

public class Mission implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long missionId;

    private Integer type;

    private Integer sex;

    private String address;

    private Double money;

    private Long publishId;

    private String createTime;

    private String startTime;

    private Long acceptId;

    private Integer status;

    private String acceptTime;

    private String finishTime;

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
		this.address = address;
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

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
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

	public String getAcceptTime() {
		return acceptTime;
	}

	public void setAcceptTime(String acceptTime) {
		this.acceptTime = acceptTime;
	}

	public String getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(String finishTime) {
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
		this.toId = toId;
	}

	public String getRecordSn() {
		return recordSn;
	}

	public void setRecordSn(String recordSn) {
		this.recordSn = recordSn;
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
		this.content = content;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Mission [missionId=" + missionId + ", type=" + type + ", sex="
				+ sex + ", address=" + address + ", money=" + money
				+ ", publishId=" + publishId + ", createTime=" + createTime
				+ ", startTime=" + startTime + ", acceptId=" + acceptId
				+ ", status=" + status + ", acceptTime=" + acceptTime
				+ ", finishTime=" + finishTime + ", anonymous=" + anonymous
				+ ", toId=" + toId + ", recordSn=" + recordSn + ", toType="
				+ toType + ", content=" + content + "]";
	}

	

	
  
}