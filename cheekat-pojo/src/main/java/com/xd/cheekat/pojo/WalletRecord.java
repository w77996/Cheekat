package com.xd.cheekat.pojo;

import java.io.Serializable;
import java.util.Date;

public class WalletRecord implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long walletRecordId;

    private String recordSn;

    private Long toUid;

    private Long fromUid;

    private Integer type;

    private Double money;

    private Integer payType;

    private Integer payStatus;

    private Date payTime;

    private Byte fetchStatus;

    private Date fetchTime;

    public Long getWalletRecordId() {
        return walletRecordId;
    }

    public void setWalletRecordId(Long walletRecordId) {
        this.walletRecordId = walletRecordId;
    }

    public String getRecordSn() {
        return recordSn;
    }

    public void setRecordSn(String recordSn) {
        this.recordSn = recordSn == null ? null : recordSn.trim();
    }

    public Long getToUid() {
        return toUid;
    }

    public void setToUid(Long toUid) {
        this.toUid = toUid;
    }

    public Long getFromUid() {
        return fromUid;
    }

    public void setFromUid(Long fromUid) {
        this.fromUid = fromUid;
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

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Byte getFetchStatus() {
        return fetchStatus;
    }

    public void setFetchStatus(Byte fetchStatus) {
        this.fetchStatus = fetchStatus;
    }

    public Date getFetchTime() {
        return fetchTime;
    }

    public void setFetchTime(Date fetchTime) {
        this.fetchTime = fetchTime;
    }
}