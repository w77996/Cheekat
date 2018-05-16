package com;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
/**
 * @Author： CatalpaFlat
 * @Descrition:
 * @Date: Create in 10:08 2017/11/8
 * @Modified BY：
 */

public class redistest {

private static final Logger LOGGER = LoggerFactory.getLogger(redistest.class);
	
	@Test
	public void testJedisSingle() {
		//创建一个jedis的对象。
		Jedis jedis = new Jedis("119.23.61.10", 6379);
		//调用jedis对象的方法，方法名称和redis的命令一致。
		jedis.set("key1", "jedis test");
		String string = jedis.get("key1");
		System.out.println(string);
		//关闭jedis。
		jedis.close();
	}
	
	/**
	 * 使用连接池
	 */
	@Test
	public void testJedisPool() {
		//创建jedis连接池
		JedisPool pool = new JedisPool("119.23.61.10", 6379);
		//从连接池中获得Jedis对象
		Jedis jedis = pool.getResource();
		String string = jedis.get("key1");
		System.out.println(string);
		//关闭jedis对象
		jedis.close();
		pool.close();
	}
	
	/**
	 * 集群版测试
	 * <p>Title: testJedisCluster</p>
	 * <p>Description: </p>
	 *//*
	@Test
	public void testJedisCluster() {
		LOGGER.debug("调用redisCluster开始");
		HashSet<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("192.168.25.153", 7001));
		nodes.add(new HostAndPort("192.168.25.153", 7002));
		nodes.add(new HostAndPort("192.168.25.153", 7003));
		nodes.add(new HostAndPort("192.168.25.153", 7004));
		nodes.add(new HostAndPort("192.168.25.153", 7005));
		nodes.add(new HostAndPort("192.168.25.153", 7006));
		LOGGER.info("创建一个JedisCluster对象");
		JedisCluster cluster = new JedisCluster(nodes);
		LOGGER.debug("设置key1的值为1000");
		cluster.set("key1", "1000");
		
		LOGGER.debug("从Redis中取key1的值");
		String string = cluster.get("key1");
		System.out.println(string);
		cluster.close();
		try {
			int a = 1/0;
		} catch (Exception e) {
			LOGGER.error("系统发送异常", e);
		}
	}*/
	
	/**
	 * 单机版测试
	 * <p>Title: testSpringJedisSingle</p>
	 * <p>Description: </p>
	 */
	@Test
	public void testSpringJedisSingle() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-context.xml");
		JedisPool pool = (JedisPool) applicationContext.getBean("redisClient");
		Jedis jedis = pool.getResource();
		String string = jedis.get("key1");
		System.out.println(string);
		jedis.close();
		pool.close();
	}
	
	/*@Test
	public void testSpringJedisCluster() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-context.xml");
		JedisCluster jedisCluster =  (JedisCluster) applicationContext.getBean("redisClient");
		String string = jedisCluster.get("key1");
		System.out.println(string);
		jedisCluster.close();
	}*/
}