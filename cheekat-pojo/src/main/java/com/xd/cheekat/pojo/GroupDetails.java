package com.xd.cheekat.pojo;

import java.io.Serializable;
import java.util.Date;

public class GroupDetails implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long detailsId;

    private Long memberId;

    private Long groupId;

    private Integer isAdmin;

    private String joinTime;

	public Long getDetailsId() {
		return detailsId;
	}

	public void setDetailsId(Long detailsId) {
		this.detailsId = detailsId;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public Integer getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getJoinTime() {
		return joinTime;
	}

	public void setJoinTime(String joinTime) {
		this.joinTime = joinTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

   
}