package com.xd.cheekat.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xd.cheekat.dao.GroupDao;
import com.xd.cheekat.dao.GroupDetailsDao;
import com.xd.cheekat.pojo.Group;

@Service
public class GroupServiceImpl implements GroupService{

	@Autowired
	private GroupDao groupDao;
	
	@Autowired
	private GroupDetailsDao groupDetailsDao;
	@Override
	public int editGroup(Group group) {
		// TODO Auto-generated method stub
		return groupDao.updateByPrimaryKeySelective(group);
	}
	@Override
	public Group getGroupByNameAndAdminId(String groupName, long userId) {
		// TODO Auto-generated method stub
		return groupDao.getGroupByNameAndAdminId(groupName,userId);
	}
	@Override
	public void addGroup(Group group) {
		// TODO Auto-generated method stub
		groupDao.insertSelective(group);
	}
	@Override
	public Group getGroupById(long groupId) {
		// TODO Auto-generated method stub
		return groupDao.selectByPrimaryKey(groupId);
	}
	@Override
	@Transactional
	public int deleteGroup(long groupId) {
		// TODO Auto-generated method stub
		groupDetailsDao.deleteDetailsByGroupId(groupId);
		groupDao.deleteByPrimaryKey(groupId);
		
		return 1;
	}
	@Override
	public List<Map<String, Object>> getUserGroup(long userId) {
		// TODO Auto-generated method stub
		return groupDao.getUserGroup(userId);
	}
	@Override
	public Group getGroupByImId(long imggroupId) {
		// TODO Auto-generated method stub
		return null;
	}

}
