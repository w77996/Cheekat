package com.xd.cheekat.dao;

import java.util.List;
import java.util.Map;

import com.xd.cheekat.pojo.GroupDetails;

public interface GroupDetailsDao {
    int deleteByPrimaryKey(Long detailsId);

    int insert(GroupDetails record);

    int insertSelective(GroupDetails record);

    GroupDetails selectByPrimaryKey(Long detailsId);

    int updateByPrimaryKeySelective(GroupDetails record);

    int updateByPrimaryKey(GroupDetails record);

	List<Map<String, Object>> getUserGroupByImId(String groupId);

	GroupDetails getGroupDetailsByGroupIdAndUserId(long groupId, long userId);

	int deleteUserFromGroup(long userId, long groupId);

	String deleteUserAdminFromGroup(long userId, long groupId);

	List<Map<String, Object>> getUserGroupDetails(long groupId);

	GroupDetails getUserGroupDetailsIsAdmin(long userId, long groupId, int isAdmin);
}