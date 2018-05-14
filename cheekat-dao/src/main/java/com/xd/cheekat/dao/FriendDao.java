package com.xd.cheekat.dao;

import java.util.List;
import java.util.Map;

import com.xd.cheekat.pojo.Friend;

public interface FriendDao {
    int deleteByPrimaryKey(Long friendId);

    int insert(Friend record);

    int insertSelective(Friend record);

    Friend selectByPrimaryKey(Long friendId);

    int updateByPrimaryKeySelective(Friend record);

    int updateByPrimaryKey(Friend record);
    
    List<Map<String, Object>> getUserFriends(long parseLong);
}