package com.xd.cheekat.service;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xd.cheekat.dao.GroupDao;
import com.xd.cheekat.dao.GroupDetailsDao;
import com.xd.cheekat.pojo.GroupDetails;
@Service
public class GroupDetailsServiceImpl implements GroupDetailsService{

	@Autowired
	private GroupDetailsDao groupDetailsDao;
	
	@Autowired
	private GroupDao groupDao;
	
	@Override
	public List<Map<String, Object>> getUserGroupByImId(String groupId) {
		// TODO Auto-generated method stub
		return groupDetailsDao.getUserGroupByImId(Long.parseLong(groupId));
	}

	@Override
	public void addGroupDetails(GroupDetails gd) {
		// TODO Auto-generated method stub
		groupDetailsDao.insertSelective(gd);
		
	}

	@Override
	public GroupDetails getGroupDetailsByGroupIdAndUserId(long groupId,
			long userId) {
		// TODO Auto-generated method stub
		return groupDetailsDao.getGroupDetailsByGroupIdAndUserId(groupId,userId);
	}

	@Override
	public void removeGroupDetails(long groupId,long userId) {
		// TODO Auto-generated method stub
		groupDetailsDao.removeGroupDetails(groupId,userId);
	}

	@Override
	public int deleteUserFromGroup(long userId, long groupId) {
		// TODO Auto-generated method stub
		return groupDetailsDao.deleteUserFromGroup(userId,groupId);
	}
	@Transactional
	@Override
	public String deleteUserAdminFromGroup(long userId, long groupId) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> secondUser = 	groupDao.getGroupSecondsMember(groupId, userId);
		if(null == secondUser){
			return null;
		}
		long secondUserId = (long) secondUser.get(0).get("member_id");
		String sendUserName = (String)secondUser.get(0).get("user_name");
		
		groupDetailsDao.deleteUserFromGroup(userId,groupId);
		
		groupDetailsDao.tranSecondUserToAdmin(secondUserId, groupId);
		return sendUserName;
	}

	@Override
	public List<Map<String, Object>> getUserGroupDetails(long groupId) {
		// TODO Auto-generated method stub
		return groupDetailsDao.getUserGroupDetails(groupId);
	}

	@Override
	public GroupDetails getUserGroupDetailsIsAdmin(long userId, long groupId,
			int isAdmin) {
		// TODO Auto-generated method stub
		return groupDetailsDao.getUserGroupDetailsIsAdmin(userId,groupId,isAdmin);
	}

}
