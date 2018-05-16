package com.xd.cheekat.dao;

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
}