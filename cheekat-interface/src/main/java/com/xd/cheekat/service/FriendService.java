package com.xd.cheekat.service;

import java.util.List;
import java.util.Map;

public interface FriendService {

	List<Map<String, Object>> getUserFriends(long parseLong);

	List<Map<String, Object>> getFriendByTwoId(long parseLong, long parseLong2);

}
