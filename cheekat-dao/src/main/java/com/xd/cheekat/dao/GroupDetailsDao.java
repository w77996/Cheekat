package com.xd.cheekat.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xd.cheekat.pojo.GroupDetails;

public interface GroupDetailsDao {
    int deleteByPrimaryKey(Long detailsId);

    int insert(GroupDetails record);

    int insertSelective(GroupDetails record);

    GroupDetails selectByPrimaryKey(Long detailsId);

    int updateByPrimaryKeySelective(GroupDetails record);

    int updateByPrimaryKey(GroupDetails record);

	List<Map<String, Object>> getUserGroupByImId(long imgroupId);

	GroupDetails getGroupDetailsByGroupIdAndUserId(@Param("groupId")long groupId, @Param("memberId")long userId);

	int deleteUserFromGroup(@Param("userId")long userId, @Param("groupId")long groupId);

	String deleteUserAdminFromGroup(@Param("userId")long userId, @Param("groupId")long groupId);

	List<Map<String, Object>> getUserGroupDetails(long groupId);

	GroupDetails getUserGroupDetailsIsAdmin(@Param("userId")long userId, @Param("groupId")long groupId, @Param("isAdmin")int isAdmin);

	void removeGroupDetails(long groupId,long userId);

	void deleteDetailsByGroupId(long groupId);
}