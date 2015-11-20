package com.zhou.blog.cache;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;
import com.zhou.blog.commom.FinalFields;

public class MemCache {
	
	private Logger                                                 log = LoggerFactory.getLogger(MemCache.class);
	
	private MemCachedClient client;
	
	public MemCache(){
		client = new MemCachedClient();
		String[] addr = {FinalFields.MEM_CACHE_IP+":"+FinalFields.MEM_CACHE_PORT};
		Integer[] weights = { 3 };
		SockIOPool pool = SockIOPool.getInstance();
		pool.setServers(addr);
		pool.setWeights(weights);
		pool.setInitConn(5);
		pool.setMinConn(5);
		pool.setMaxConn(200);
		pool.setMaxIdle(1000 * 30 * 30);
		pool.setMaintSleep(30);
		pool.setNagle(false);
		pool.setSocketTO(30);
		pool.setSocketConnectTO(0);
		pool.initialize();
	}
	
	public void init(){
		client = new MemCachedClient();
		String[] addr = {FinalFields.MEM_CACHE_IP+":"+FinalFields.MEM_CACHE_PORT};
		Integer[] weights = { 3 };
		SockIOPool pool = SockIOPool.getInstance();
		pool.setServers(addr);
		pool.setWeights(weights);
		pool.setInitConn(5);
		pool.setMinConn(5);
		pool.setMaxConn(200);
		pool.setMaxIdle(1000 * 30 * 30);
		pool.setMaintSleep(30);
		pool.setNagle(false);
		pool.setSocketTO(30);
		pool.setSocketConnectTO(0);
		pool.initialize();
	}
	
	public void setMemcache(String key,Object value,int time){
		// 将数据放入缓存
		boolean ok=false;
		long times=System.currentTimeMillis()+time;
		Date expiry=new Date(times);
		if(time!=0){
			ok=client.set(key, value, expiry);
		}else{
			ok=client.set(key, value);
		}
		if (ok) {
			log.info("插入memcache成功！插入数据：key="+key+"  value:"+value.toString());
		} else {
			log.error("插入memcache失败！插入数据：key="+key+"  value:"+value.toString());
		}
		
	}
	
	public Object getMemcache(String key){
		// 从缓存中获取数据
		Object object=client.get(key);
		if(object!=null){
			log.info("获取memcache数据成功！获取的数据是："+object.toString());
		}else {
			log.error("获取memcache数据为空！可能数据为空或者获取失败");
		}
		return object;
	}
	public boolean deleteMemcache(String key){
		// 将数据从缓存中删除
		boolean ok=client.delete(key);
		if(ok){
			log.info("删除memcache数据成功！KEY:"+key);
		}else {
			log.error("删除memcache数据失败！KEY:"+key);
		}
		return ok;
	}
	
}
