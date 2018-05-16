package com.xd.cheekat.service;

import com.xd.cheekat.pojo.RedPacket;

public interface RedPacketService {

	void editRedPacketPayStatus(String out_trade_no, int pay_status);

	RedPacket getRedPacketByRecordSN(String out_trade_no);

}
