package com.xd.cheekat.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xd.cheekat.common.Constant;
import com.xd.cheekat.dao.MissionDao;
import com.xd.cheekat.jedis.JedisClient;
import com.xd.cheekat.pojo.Mission;
import com.xd.cheekat.util.DateUtil;
@Service
public class MissionServiceImpl implements MissionService{

	
	@Autowired
	private MissionDao missionDao;
	
	@Autowired
	private JedisClient jedisClient;
	
	@Override
	public Mission getMissionByRecordSN(String recordSn) {
		// TODO Auto-generated method stub
		return missionDao.getMissionByRecordSN(recordSn);
	}

	@Override
	public void editMission(Mission mission) {
		// TODO Auto-generated method stub
		if(Constant.MISSION_TYPE_INVALID == mission.getStatus()||Constant.MISSION_TYPE_ING == mission.getStatus()){
			jedisClient.del(mission.getRecordSn());
		}
		missionDao.updateByPrimaryKeySelective(mission);
	}

	@Override
	public List<Map<String, Object>> getAllMissionLimit(int start, int count) {
		// TODO Auto-generated method stub
		return missionDao.getAllMissionLimit(start,count);
	}

	@Override
	public List<Map<String, Object>> getMyMission(long userId) {
		// TODO Auto-generated method stub
		return missionDao.getMyMission(userId);
	}

	@Override
	public Mission getMissionById(long missionId) {
		// TODO Auto-generated method stub
		return missionDao.selectByPrimaryKey(missionId);
	}

	@Override
	public void addMission(Mission mission) {
		// TODO Auto-generated method stub
		
		missionDao.insertSelective(mission);
		jedisClient.set(mission.getRecordSn(), "mission");
		System.out.println("mission_time"+DateUtil.getSeconds(DateUtil.getNowTime(), mission.getStartTime()));
		jedisClient.expire(mission.getRecordSn(),DateUtil.getSeconds(DateUtil.getNowTime(), mission.getStartTime()));
	}

	@Override
	public Mission getMissionByPubIdAndCreateTime(Long userId,
			String createTime, String content, String startTime) {
		// TODO Auto-generated method stub
		Mission mission = new Mission();
		mission.setPublishId(userId);
		mission.setCreateTime(createTime);
		mission.setContent(content);
		mission.setStartTime(startTime);
		return missionDao.getMissionByPubIdAndCreateTime(mission);
	}

}
