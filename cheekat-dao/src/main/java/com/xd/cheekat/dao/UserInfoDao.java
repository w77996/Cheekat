package com.xd.cheekat.dao;

import java.util.List;

import com.xd.cheekat.pojo.UserInfo;

public interface UserInfoDao {
    int deleteByPrimaryKey(Long userId);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

	UserInfo getUserByUserName(String username);

	UserInfo getUserByOpenId(String openId);

	List<UserInfo> getUserByIds(String[] ids);
}