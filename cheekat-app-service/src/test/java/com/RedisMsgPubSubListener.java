package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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


	}
	
	@Override  

    public void onUnsubscribe(String channel, int subscribedChannels) {  

        System.out.println("channel:" + channel + "is been unsubscribed:" + subscribedChannels);  

    } 
	
	
}
