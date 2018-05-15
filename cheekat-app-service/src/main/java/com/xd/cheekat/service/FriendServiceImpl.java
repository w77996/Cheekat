package com.xd.cheekat.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xd.cheekat.dao.FriendDao;
@Service
public class FriendServiceImpl implements FriendService{

	@Autowired
	private FriendDao friendDao;
	
	@Override
	public List<Map<String, Object>> getUserFriends(long parseLong) {
		// TODO Auto-generated method stub
		return friendDao.getUserFriends(parseLong);
	}

	@Override
	public List<Map<String, Object>> getFriendByTwoId(long userId,
			long userId2) {
		// TODO Auto-generated method stub
		return friendDao.getFriendByTwoId(userId,userId2);
	}

}
