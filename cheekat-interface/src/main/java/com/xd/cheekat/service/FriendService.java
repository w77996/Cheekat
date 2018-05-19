package com.xd.cheekat.service;

import java.util.List;
import java.util.Map;

import com.xd.cheekat.pojo.Friend;

public interface FriendService {

	List<Map<String, Object>> getUserFriends(long parseLong);

	List<Map<String, Object>> getFriendByTwoId(long parseLong, long parseLong2);

	Friend findFriends(long userId, long friendId);

	int addFriends(Friend friend);

}
