package com.xd.cheekat.jedis;

import org.springframework.beans.factory.annotation.Autowired;

import com.xd.cheekat.common.Constant;
import com.xd.cheekat.pojo.Mission;
import com.xd.cheekat.pojo.RedPacket;
import com.xd.cheekat.pojo.Wallet;
import com.xd.cheekat.pojo.WalletRecord;
import com.xd.cheekat.service.MissionService;
import com.xd.cheekat.service.RedPacketService;
import com.xd.cheekat.service.WalletLogService;
import com.xd.cheekat.service.WalletRecordService;
import com.xd.cheekat.service.WalletService;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;

public class RedisMsgPubSubListener extends JedisPubSub {
	
	@Autowired
	private  JedisPool jedisPool; 
	
	@Autowired
	private WalletRecordService walletRecordService;
	
	@Autowired
	private RedPacketService redPacketService;
	
	@Autowired
	private MissionService missionService;
	
	@Autowired
	private WalletService walletService;
	
	@Autowired
	private WalletLogService walletLogService;
	
	@Override
	public void onPSubscribe(String pattern, int subscribedChannels) {
		System.out
				.println("onPSubscribe " + pattern + " " + subscribedChannels);
	}

	@Override
	public void onPMessage(String pattern, String channel, String message) {

		System.out.println("onPMessage pattern " + pattern + " " + channel
				+ " " + message);
	}

	@Override
	public void onSubscribe(String channel, int subscribedChannels) {
		System.out.println("channel:" + channel + "is been subscribed:"
				+ subscribedChannels);
	}

	@Override
	public void onMessage(String channel, String message) {

		System.out.println("channel11111111:" + channel + "receives message :"
				+ message);

		// 对该key进行业务处理。。。。。
		WalletRecord walletRecord = walletRecordService.getWallerOrderByRecordSN(message);
		
		int recordType = walletRecord.getType();
		if(Constant.ORDER_TYPE_TASK == walletRecord.getType()){
			Mission mission = missionService.getMissionByRecordSN(message);
			mission.setStatus(Constant.MISSION_TYPE_INVALID);
			missionService.editMission(mission);
			
			Wallet wallet = walletService.findWalletByUserId(mission.getPublishId()); 
			
			Double total_fee = wallet.getMoney()+mission.getMoney();
			String changemoney = ""+mission.getMoney();
			walletService.refund(mission.getRecordSn(),mission.getPublishId(),Constant.LOG_REFUND_TASK,Double.parseDouble(changemoney),total_fee);
			
		}else if(Constant.ORDER_TYPE_REDPACKET == walletRecord.getType()){
			RedPacket redPacket = redPacketService.getRedPacketByRecordSN(message);
			redPacket.setStatus(Constant.REDPACKET_INVALID);
			redPacketService.editRedPacket(redPacket);
			
			Wallet wallet = walletService.findWalletByUserId(redPacket.getPublishId()); 
			
			Double total_fee = wallet.getMoney()+redPacket.getMoney();
			String changemoney = ""+redPacket.getMoney();
			walletService.refund(redPacket.getRecordSn(),redPacket.getPublishId(),Constant.LOG_REFUND_TASK,Double.parseDouble(changemoney),total_fee);
			
		}

	}
	
	@Override  

    public void onUnsubscribe(String channel, int subscribedChannels) {  

        System.out.println("channel:" + channel + "is been unsubscribed:" + subscribedChannels);  

    } 
	
	
}
