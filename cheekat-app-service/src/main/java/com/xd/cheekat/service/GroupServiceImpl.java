package com.xd.cheekat.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xd.cheekat.dao.GroupDao;
import com.xd.cheekat.pojo.Group;

@Service
public class GroupServiceImpl implements GroupService{

	@Autowired
	private GroupDao groupDao;
	@Override
	public int editGroup(Group group) {
		// TODO Auto-generated method stub
		return groupDao.updateByPrimaryKeySelective(group);
	}
	@Override
	public Group getGroupByNameAndAdminId(String groupName, long parseLong) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void addGroup(Group group) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Group getGroupById(long parseLong) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int deleteGroup(long group_id) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public List<Map<String, Object>> getUserGroup(long user_id) {
		// TODO Auto-generated method stub
		return null;
	}

}
