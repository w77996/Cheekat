package com.xd.cheekat.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xd.cheekat.dao.RedPacketDao;
import com.xd.cheekat.pojo.RedPacket;
import com.xd.cheekat.util.DateUtil;
@Service
public class RedPacketServiceImpl implements RedPacketService{

	@Autowired
	private RedPacketDao redPacketDao;
	
	@Override
	public void editRedPacketPayStatus(String out_trade_no, int pay_status) {
		// TODO Auto-generated method stub
		RedPacket redPacket = new RedPacket();
		redPacket.setPayStatus(pay_status);
		redPacket.setRecordSn(out_trade_no);
		redPacketDao.updateRedPacketByRecordSn(redPacket);
	}

	@Override
	public RedPacket getRedPacketByRecordSN(String out_trade_no) {
		// TODO Auto-generated method stub
		return redPacketDao.getRedPacketByRecordSN(out_trade_no);
	}

	@Override
	public boolean editRedPacketFetchStatus(String recordSn, Long userId,
			int fetchStatus) {
		// TODO Auto-generated method stub
		RedPacket redPacket = new RedPacket();
		redPacket.setRecordSn(recordSn);
		redPacket.setAcceptId(userId);
		redPacket.setAcceptTime(DateUtil.getNowTime());
		redPacket.setStatus(fetchStatus);
		return redPacketDao.updateRedPacketByRecordSn(redPacket);
	}
/*
	@Override
	public List<Map<String, Object>> getRedPacketAndUserInfoByRedPacketId(
			String redpacketId) {
		// TODO Auto-generated method stub
		return null;
	}*/

	@Override
	public boolean addRedpacketRecord(String record_sn, String userId,
			String money, String to, String to_id) {
		// TODO Auto-generated method stub
		RedPacket redPacket = new RedPacket();
		redPacket.setPublishId(Long.parseLong(userId));
		redPacket.setRecordSn(record_sn);
		redPacket.setMoney(Double.parseDouble(money));
		redPacket.setCreateTime(DateUtil.getNowTime());
		redPacket.setToType(Integer.parseInt(to));
		redPacket.setToId(to_id);
		return redPacketDao.insertSelective(redPacket) > 0 ?true:false;
	}

	@Override
	public RedPacket getRedPacketById(long redpacketId) {
		// TODO Auto-generated method stub
		return redPacketDao.selectByPrimaryKey(redpacketId);
	}

}
