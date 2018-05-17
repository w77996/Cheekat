package com;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class testRedis {

	 public static void main(String[] args) {  
	        /*JedisPool pool = new JedisPool(new JedisPoolConfig(), "localhost");  
	  
	        Jedis jedis = pool.getResource();  
	        jedis.set("notify1", "新浪微博：小叶子一点也不逗");  
	        jedis.expire("notify1", 10);  */
		 DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
		 try  
		 {   
		     Date d1 = df.parse("2004-04-26 13:32:40");   
		     Date d2 = df.parse("2004-03-26 13:31:45");   
		     
		     long diff = d1.getTime() - d2.getTime();   
		     System.out.println(diff+"");
		     long days = diff / (1000);  
		     System.out.println(days+"");
		 }   
		 catch (Exception e)   
		 {   
		 }  
	  
	    }  
	 
	 
}
