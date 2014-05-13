package com.dgame.jedis;

import java.util.Iterator;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;

public class JedisClientTest {
	private JedisClient client = JedisClient.getInstance();
	@Before
	public void init(){
		System.out.println("JedisClientTest......");
	}
	@Test
	public void set(){
		System.out.println(client.set("hotname","chen.haixiang"));
	}
	@Test
	public void get(){
		System.out.println(client.get("hotname"));
	}
	@Test
	public void del(){
		System.out.println(client.delByKey("hotname"));
		for (int i = 0; i < 100000; i++) {
			System.out.println(client.delByKey("n"+i));
	    }
	}
	@Test
	public void zadd(){
		String key="hotname";
		long score = 0;
		String member="jack_0";
		System.out.println(client.zadd(key, score, member));
	}
	@Test
	public void zrange(){
		String key="hotname";
		Set<String> set = client.zrange(key, 0,10);
		Iterator<String> it = set.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
	}
	@Test
	public void hset(){
		client.hset("1", "name", "张三");
		client.hset("1", "age", "23");
		client.hset("2", "name", "李四");
		client.hset("2", "age", "24");
	}
	@Test
	public void hget(){
		System.out.println(client.hget("1", "name"));
		System.out.println(client.hget("1", "age"));
		System.out.println(client.hget("2", "name"));
		System.out.println(client.hget("2", "age"));
	}
	@Test
	public void hdel(){
		String key = "1";
		String fields = "age";
		client.hdel(key, fields);
	}
	@Test
	public void hgetall(){
		System.out.println(client.hgetall("1"));
	}
}
