package com.dgame.jedis;


import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisClient {
	private static JedisPool jedisPool;
	private static JedisClient client;
	private static Object lock = new Object();
	
	
	private JedisClient(){
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxIdle(1024);
		config.setMaxIdle(200);
		config.setMaxWaitMillis(1000);
		config.setTestOnBorrow(true);
		jedisPool = new JedisPool(config, "127.0.0.1", 6379);
	}
	public static JedisClient getInstance() {
		if (client == null)
			synchronized (lock) {
				if (client == null) {
					client = new JedisClient();
				}
			}
		return client;
	}
	/*String类型操作*/
	public String set(String key, String value) {
		Jedis jedis = jedisPool.getResource();
		String status = jedis.set(key, value);
		jedisPool.returnResource(jedis);
		return status;
	}
    public String get(String key) {
        Jedis jedis = jedisPool.getResource();
        String ret = jedis.get(key);
        jedisPool.returnResource(jedis);
        return ret;
    }
    public Long delByKey(String key) {
        Jedis jedis = jedisPool.getResource();
        Long ret = jedis.del(key);
        jedisPool.returnResource(jedis);
        return ret;
    }
    public Long zadd(String key, Long score, String member){
    	Jedis jedis = jedisPool.getResource();
    	Long id = jedis.zadd(key, score, member);
    	jedisPool.returnResource(jedis);
    	return id;
    }
    public Set<String> zrange(String key, int start, int end) {
		Jedis jedis = jedisPool.getResource();
		Set<String> ranges = jedis.zrange(key, start, end);
		jedisPool.returnResource(jedis);
		return ranges;
	}
    public Set<String> zrevrange(String key, int start, int end) {
		Jedis jedis = jedisPool.getResource();
		Set<String> ranges = jedis.zrevrange(key, start, end);
		jedisPool.returnResource(jedis);
		return ranges;
	}
    /*Hash类型操作*/
    public Long hset(String key, String field, String value) {
		Jedis jedis = jedisPool.getResource();
		Long status = jedis.hset(key, field, value);
		jedisPool.returnResource(jedis);
		return status;
	}
    public String hget(String key, String field){
    	Jedis jedis = jedisPool.getResource();
    	String value = jedis.hget(key, field);
    	jedisPool.returnResource(jedis);
    	return value;
    }
    public void hdel(String key, String fields){
    	Jedis jedis = jedisPool.getResource();
    	jedis.hdel(key, fields);
    	jedisPool.returnResource(jedis);
    }
    public Map<String, String> hgetall(String key){
    	Jedis jedis = jedisPool.getResource();
    	Map<String, String> map = jedis.hgetAll(key);
    	jedisPool.returnResource(jedis);
    	return map;
    }
}
