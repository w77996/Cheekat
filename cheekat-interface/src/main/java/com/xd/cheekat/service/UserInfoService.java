package com.xd.cheekat.service;

import java.util.List;

import com.xd.cheekat.pojo.UserInfo;

public interface UserInfoService {
	
	UserInfo selectByPrimaryKey(Long userId);

	int updateByPrimaryKeySelective(UserInfo record);

	List<UserInfo> getUserByIds(String[] ids);

	int editUser(UserInfo user);

	UserInfo getUserByUserName(String phone);

	void addNewUser(UserInfo user);

	UserInfo getUserByOpenId(String openId);
}
