package com.xd.cheekat.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xd.cheekat.dao.MissionDao;
import com.xd.cheekat.pojo.Mission;
@Service
public class MissionServiceImpl implements MissionService{

	
	@Autowired
	private MissionDao missionDao;
	
	@Override
	public Mission getMissionByRecordSN(String recordSn) {
		// TODO Auto-generated method stub
		return missionDao.getMissionByRecordSN(recordSn);
	}

	@Override
	public void editMission(Mission mission) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Map<String, Object>> getAllMissionLimit(int start, int count) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> getMyMission(long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mission getMissionById(long mission) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addMission(Mission mission) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Mission getMissionByPubIdAndCreateTime(Long userId,
			String createTime, String content, String startTime) {
		// TODO Auto-generated method stub
		return null;
	}

}
