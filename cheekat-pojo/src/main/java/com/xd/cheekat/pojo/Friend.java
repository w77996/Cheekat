package com.xd.cheekat.pojo;

import java.io.Serializable;
import java.util.Date;

public class Friend implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long friendId;

    private Long userIdFr1;

    private Long userIdFr2;

    private Integer status;

    private Date createTime;

    public Long getFriendId() {
        return friendId;
    }

    public void setFriendId(Long friendId) {
        this.friendId = friendId;
    }

    public Long getUserIdFr1() {
        return userIdFr1;
    }

    public void setUserIdFr1(Long userIdFr1) {
        this.userIdFr1 = userIdFr1;
    }

    public Long getUserIdFr2() {
        return userIdFr2;
    }

    public void setUserIdFr2(Long userIdFr2) {
        this.userIdFr2 = userIdFr2;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}