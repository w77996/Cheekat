package com.xd.cheekat.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xd.cheekat.dao.FriendDao;
import com.xd.cheekat.pojo.Friend;
import com.xd.cheekat.util.DateUtil;
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

	@Override
	public Friend findFriends(long userId, long friendId) {
		// TODO Auto-generated method stub
		return friendDao.findFriends(userId,friendId);
	}

	@Transactional
	@Override
	public int addFriends(Friend friend) {
		// TODO Auto-generated method stub
		
		friendDao.addFriends(friend);
		Friend friend2 = new Friend();
		friend2.setUserIdFr1(friend.getUserIdFr2());
		friend2.setUserIdFr2(friend.getUserIdFr1());
		friend2.setCreateTime(friend.getCreateTime());
		return friendDao.addFriends(friend2);
	}

}
