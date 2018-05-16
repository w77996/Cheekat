package com.xd.cheekat.service;

import com.xd.cheekat.pojo.Mission;

public interface MissionService {

	Mission getMissionByRecordSN(String out_trade_no);

	void editMission(Mission mission);

}
