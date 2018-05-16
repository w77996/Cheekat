package com.xd.cheekat.service;

import java.util.List;
import java.util.Map;

import com.xd.cheekat.pojo.Group;

public interface GroupService {

	int editGroup(Group group);

	Group getGroupByNameAndAdminId(String groupName, long parseLong);

	void addGroup(Group group);

	Group getGroupById(long parseLong);

	int deleteGroup(long group_id);

	List<Map<String, Object>> getUserGroup(long user_id);

	Group getGroupByImId(long imggroupId);

}
