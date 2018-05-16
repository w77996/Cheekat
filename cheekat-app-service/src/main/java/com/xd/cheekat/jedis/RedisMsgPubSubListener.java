package com.xd.cheekat.jedis;

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

		// 对该key进行业务处理。。。。。

	}
	
	@Override  

    public void onUnsubscribe(String channel, int subscribedChannels) {  

        System.out.println("channel:" + channel + "is been unsubscribed:" + subscribedChannels);  

    } 
	
	
}
