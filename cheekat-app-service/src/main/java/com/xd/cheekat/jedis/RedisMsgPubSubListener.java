package com.xd.cheekat.jedis;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xd.cheekat.common.Constant;
import com.xd.cheekat.dao.WalletDao;
import com.xd.cheekat.dao.WalletRecordDao;
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

@Component
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
	
	
	@Autowired
	private WalletRecordDao walletRecordDao;
	
	
	
	
	@Override
	public void onPSubscribe(String pattern, int subscribedChannels) {
		System.out
				.println("onPSubscribe " + pattern + " " + subscribedChannels);
	}

	@Override
	public void onPMessage(String pattern, String channel, String message) {

		System.out.println("onPMessage pattern " + pattern + " " + channel
				+ " " + message);
		//Refund refund = new Refund();
			//refund.missionRefund(message);
		/*WalletRecord walletRecord2 = walletRecordDao.getWallerOrderByRecordSN(message);
		System.out.println(walletRecord2.toString());*/
		WalletRecord walletRecord = Refund.getWallerOrderByRecordSN(message);
		if(Constant.ORDER_TYPE_TASK == walletRecord.getType()){
			Refund.refundMission(message);
		}else if(Constant.ORDER_TYPE_REDPACKET == walletRecord.getType()){
			Refund.refundRedPacket(message);
		}
		/*if(null == walletRecordService){
			System.out.println("null");
		}else{
			System.out.println("not null");
		}*/
		// 对该key进行业务处理。。。。。
		/*WalletRecord walletRecord = walletRecordService.getWallerOrderByRecordSN(message);
		
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
			
		}*/
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


	}
	
	@Override  

    public void onUnsubscribe(String channel, int subscribedChannels) {  

        System.out.println("channel:" + channel + "is been unsubscribed:" + subscribedChannels);  

    } 
	
	
}
