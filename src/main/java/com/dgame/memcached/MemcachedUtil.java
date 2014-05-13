package com.dgame.memcached;

import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;

public class MemcachedUtil {
	protected static MemCachedClient mcc = new MemCachedClient();
	static{ 
		String[] servers = { "127.0.0.1:11211", "127.0.0.1:11211", "127.0.0.1:11211" };
		Integer[] weights = { 3, 3, 2 };
		SockIOPool pool = SockIOPool.getInstance();
		pool.setServers(servers);
		pool.setWeights(weights);
		pool.setInitConn(5);
		pool.setMinConn(5);
		pool.setMaxConn(250);
		pool.setMaxIdle(1000 * 60 * 60 * 6);
		pool.setMaintSleep(30);
		pool.setNagle(false);
		pool.setSocketTO(3000);
		pool.setSocketConnectTO(0);
		pool.initialize();
		mcc.setCompressEnable(true);
		mcc.setCompressThreshold(64 * 1024);
	}
	
	public void add(String key, Object value){
		mcc.add(key, value);
	}
}
