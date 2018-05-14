package com.xd.cheekat.dao;

import com.xd.cheekat.pojo.GroupDetails;

public interface GroupDetailsDao {
    int deleteByPrimaryKey(Long detailsId);

    int insert(GroupDetails record);

    int insertSelective(GroupDetails record);

    GroupDetails selectByPrimaryKey(Long detailsId);

    int updateByPrimaryKeySelective(GroupDetails record);

    int updateByPrimaryKey(GroupDetails record);
}