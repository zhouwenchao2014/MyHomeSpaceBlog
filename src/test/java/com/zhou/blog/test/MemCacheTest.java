package com.zhou.blog.test;

import org.junit.Before;
import org.junit.Test;

import com.zhou.blog.cache.MemCache;

public class MemCacheTest {
	
	private MemCache memCache;
	@Before
	public void before(){
		memCache=new MemCache();
		memCache.init();
		memCache.setMemcache("elasticsearch.ip", "120.26.102.2",0);
		memCache.setMemcache("elasticsearch.port", "9300",0);
		memCache.setMemcache("memcache.ip", "120.26.102.2",0);
		memCache.setMemcache("memcache.port", "9300",0);
	}
	
	@Test
	public void get(){
		System.out.println(memCache.getMemcache("elasticsearch.ip"));
		System.out.println(memCache.getMemcache("elasticsearch.port"));
		System.out.println(memCache.getMemcache("memcache.ip"));
		System.out.println(memCache.getMemcache("memcache.port"));
	}
	
	@Test
	public void delete(){
		memCache.deleteMemcache("127.0.0.1");
	}
}
