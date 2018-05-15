package com.xd.cheekat.pojo;

import java.io.Serializable;
import java.util.Date;

public class UserIndexImg implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long userImgId;

    private Long userId;

    private String img;

    private String createTime;

    public Long getUserImgId() {
        return userImgId;
    }

    public void setUserImgId(Long userImgId) {
        this.userImgId = userImgId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}