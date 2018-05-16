package com.xd.cheekat.service;

import java.util.List;
import java.util.Map;

import com.xd.cheekat.pojo.Mission;

public interface MissionService {

	Mission getMissionByRecordSN(String out_trade_no);

	void editMission(Mission mission);

	List<Map<String, Object>> getAllMissionLimit(int start, int count);

	List<Map<String, Object>> getMyMission(long userId);

	Mission getMissionById(long mission);

	void addMission(Mission mission);

	Mission getMissionByPubIdAndCreateTime(Long userId, String createTime,
			String content, String startTime);
	
	

}
