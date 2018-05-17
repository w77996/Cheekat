package com;

import com.xd.cheekat.jedis.RedisMsgPubSubListener;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisPubSub;

public class Subscriber {
	public static void main(String[] args) {  
        JedisPool pool = new JedisPool(new JedisPoolConfig(), "localhost");  
  
        Jedis jedis = pool.getResource();  
        jedis.psubscribe(new KeyExpiredListener(), "__key*__:*");  
  
    }  
	
}
