package com.xd.cheekat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xd.cheekat.dao.UserInfoDao;
import com.xd.cheekat.jedis.JedisClient;
import com.xd.cheekat.pojo.UserInfo;

@Service
public class UserInfoServiceImpl implements UserInfoService{

	@Autowired
	UserInfoDao userInfoDao;
	
	@Autowired
	private JedisClient jedisClient;
	
	@Override
	public UserInfo selectByPrimaryKey(Long userId) {
		// TODO Auto-generated method stub
		return userInfoDao.selectByPrimaryKey(userId);
	}

	@Override
	public int updateByPrimaryKeySelective(UserInfo record) {
		// TODO Auto-generated method stub
		return userInfoDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<UserInfo> getUserByIds(String[] ids) {
		// TODO Auto-generated method stub
		return userInfoDao.getUserByIds(ids);
	}

	@Override
	public int editUser(UserInfo user) {
		// TODO Auto-generated method stub
		return userInfoDao.updateByPrimaryKeySelective(user);
	}

	@Override
	public UserInfo getUserByUserName(String username) {
		// TODO Auto-generated method stub
		return userInfoDao.getUserByUserName(username);
	}

	@Override
	public void addNewUser(UserInfo user) {
		// TODO Auto-generated method stub
		userInfoDao.insertSelective(user);
	}

	@Override
	public UserInfo getUserByOpenId(String openId) {
		// TODO Auto-generated method stub
		return userInfoDao.getUserByOpenId(openId);
	}

	@Override
	public void test() {
		// TODO Auto-generated method stub
		jedisClient.subscribe("666", "575", 10);
	}

}
