package com.xd.cheekat.dao;

import java.util.List;
import java.util.Map;

import com.xd.cheekat.pojo.Mission;

public interface MissionDao {
    int deleteByPrimaryKey(Long missionId);

    int insert(Mission record);

    int insertSelective(Mission record);

    Mission selectByPrimaryKey(Long missionId);

    int updateByPrimaryKeySelective(Mission record);

    int updateByPrimaryKeyWithBLOBs(Mission record);

    int updateByPrimaryKey(Mission record);

	Mission getMissionByRecordSN(String recordSn);

	List<Map<String, Object>> getAllMissionLimit(int start, int count);

	List<Map<String, Object>> getMyMission(long userId);

	Mission getMissionByPubIdAndCreateTime(Mission mission);
}