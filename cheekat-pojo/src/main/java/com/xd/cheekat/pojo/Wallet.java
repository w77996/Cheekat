package com.xd.cheekat.pojo;

import java.io.Serializable;

public class Wallet implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long walletId;

    private Long userId;

    private Double money;

    public Long getWalletId() {
        return walletId;
    }

    public void setWalletId(Long walletId) {
        this.walletId = walletId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

	@Override
	public String toString() {
		return "Wallet [walletId=" + walletId + ", userId=" + userId
				+ ", money=" + money + "]";
	}
    
    
}