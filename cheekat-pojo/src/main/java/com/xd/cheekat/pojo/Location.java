package com.xd.cheekat.pojo;

import java.io.Serializable;
import java.util.Date;

public class Location implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long locId;

    private Double lat;

    private Double lng;

    private Date lastTime;

    private Long userId;
    
    private UserInfo user;

    public Long getLocId() {
        return locId;
    }

    public void setLocId(Long locId) {
        this.locId = locId;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    
	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Location [locId=" + locId + ", lat=" + lat + ", lng=" + lng
				+ ", lastTime=" + lastTime + ", userId=" + userId + ", user="
				+ user + "]";
	}
    
    
}