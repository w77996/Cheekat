package com.xd.cheekat.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xd.cheekat.pojo.GroupDetails;

public interface GroupDetailsService {

	List<Map<String, Object>> getUserGroupByImId(String groupId);

	void addGroupDetails(GroupDetails gd);

	GroupDetails getGroupDetailsByGroupIdAndUserId(long groupId,
			long userId);

	void removeGroupDetails(long groupId,long userId);

	int deleteUserFromGroup(long user_id, long group_id);

	String deleteUserAdminFromGroup(long user_id, long group_id);

	List<Map<String, Object>> getUserGroupDetails(long group_id);

	GroupDetails getUserGroupDetailsIsAdmin(long user_id, long group_id,
			int is_Admin);

}
