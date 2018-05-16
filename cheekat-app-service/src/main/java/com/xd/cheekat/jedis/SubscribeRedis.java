package com.xd.cheekat.jedis;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
@Component
public class SubscribeRedis {
	
	@Autowired
	private  JedisPool jedisPool; 
	
	
	@PostConstruct
	public void initSubscribe(){
		System.out.println("进入线程");
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("开始订阅------------------initSubscribe");
				// TODO Auto-generated method stub
				 Jedis jedis = jedisPool.getResource();
				RedisMsgPubSubListener listener = new RedisMsgPubSubListener();
				jedis.subscribe(listener, "__keyevent@0__:expired");
			}
		}).start();
		
	}

}
