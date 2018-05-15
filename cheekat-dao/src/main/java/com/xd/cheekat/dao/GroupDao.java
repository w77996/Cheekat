package com.xd.cheekat.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xd.cheekat.pojo.Group;

public interface GroupDao {
    int deleteByPrimaryKey(Long groupId);

    int insert(Group record);

    int insertSelective(Group record);

    Group selectByPrimaryKey(Long groupId);

    int updateByPrimaryKeySelective(Group record);

    int updateByPrimaryKey(Group record);

	Group getGroupByNameAndAdminId(@Param("groupName")String groupName, @Param("userId")long userId);

	List<Map<String, Object>> getUserGroup(@Param("userId")long userId);
}