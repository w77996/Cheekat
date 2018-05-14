package com.xd.cheekat.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xd.cheekat.dao.GroupDetailsDao;
import com.xd.cheekat.pojo.GroupDetails;
@Service
public class GroupDetailsServiceImpl implements GroupDetailsService{

	@Autowired
	private GroupDetailsDao groupDetailsDao;
	
	@Override
	public List<Map<String, Object>> getUserGroupByImId(String groupId) {
		// TODO Auto-generated method stub
		return groupDetailsDao.getUserGroupByImId(groupId);
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
	public void removeGroupDetails(Long detailsId) {
		// TODO Auto-generated method stub
		groupDetailsDao.deleteByPrimaryKey(detailsId);
	}

	@Override
	public int deleteUserFromGroup(long userId, long groupId) {
		// TODO Auto-generated method stub
		return groupDetailsDao.deleteUserFromGroup(userId,groupId);
	}

	@Override
	public String deleteUserAdminFromGroup(long userId, long groupId) {
		// TODO Auto-generated method stub
		return groupDetailsDao.deleteUserAdminFromGroup(userId,groupId);
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
