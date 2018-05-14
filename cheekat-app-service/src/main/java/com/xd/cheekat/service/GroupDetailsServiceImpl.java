package com.xd.cheekat.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.xd.cheekat.pojo.GroupDetails;
@Service
public class GroupDetailsServiceImpl implements GroupDetailsService{

	@Override
	public List<Map<String, Object>> getUserGroupByImId(String groupId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addGroupDetails(GroupDetails gd) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public GroupDetails getGroupDetailsByGroupIdAndUserId(long parseLong,
			long parseLong2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeGroupDetails(Long detailsId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int deleteUserFromGroup(long user_id, long group_id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String deleteUserAdminFromGroup(long user_id, long group_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> getUserGroupDetails(long group_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GroupDetails getUserGroupDetailsIsAdmin(long user_id, long group_id,
			int is_Admin) {
		// TODO Auto-generated method stub
		return null;
	}

}
