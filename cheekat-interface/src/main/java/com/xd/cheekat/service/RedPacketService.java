package com.xd.cheekat.service;

import java.util.List;
import java.util.Map;

import com.xd.cheekat.pojo.RedPacket;

public interface RedPacketService {

	void editRedPacketPayStatus(String out_trade_no, int pay_status);

	RedPacket getRedPacketByRecordSN(String out_trade_no);

	boolean editRedPacketFetchStatus(String recordSn, Long userId,
			int fetchStatus);
/*
	List<Map<String, Object>> getRedPacketAndUserInfoByRedPacketId(
			String redpacketId);*/

	boolean addRedpacketRecord(String record_sn, String userId, String money,
			String to, String to_id);

	RedPacket getRedPacketById(long redpacketId);

	void editRedPacket(RedPacket redPacket);

}
