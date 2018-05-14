package com.xd.cheekat.pojo;

import java.io.Serializable;
import java.util.Date;

public class Group implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long groupId;

    private String groupName;

    private String createTime;

    private String imGroupId;

    private Long adminId;

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getImGroupId() {
		return imGroupId;
	}

	public void setImGroupId(String imGroupId) {
		this.imGroupId = imGroupId;
	}

	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}