package com.xd.cheekat.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xd.cheekat.pojo.Friend;

public interface FriendDao {
    int deleteByPrimaryKey(Long friendId);

    int insert(Friend record);

    int insertSelective(Friend record);

    Friend selectByPrimaryKey(Long friendId);

    int updateByPrimaryKeySelective(Friend record);

    int updateByPrimaryKey(Friend record);
    
    List<Map<String, Object>> getUserFriends(long parseLong);

	List<Map<String, Object>> getFriendByTwoId(@Param("userId")long userId, @Param("userId2")long userId2);

	Friend findFriends(long userId, long friendId);

	int addFriends(Friend friend);
}