package com.zhou.blog.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.elasticsearch.search.SearchHits;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhou.blog.cache.MemCache;
import com.zhou.blog.model.ArticleDo;
import com.zhou.blog.utils.ESearch;
import com.zhou.blog.utils.Utils;

@Controller
@RequestMapping("/")
public class IndexController {

	private Logger log=LoggerFactory.getLogger(IndexController.class);
	
	@RequestMapping("index")
	public String index(HttpServletRequest request) {
		MemCache memCache=new MemCache();
		//memCache.init();
		ESearch eSearch = new ESearch();
		List<ArticleDo> articleDos = new ArrayList<ArticleDo>();
		SearchHits searchHits = eSearch.queryAll(null);
		String ip=Utils.getIp(request);
		log.info(ip);
		String loginName="";
		String value=(String) memCache.getMemcache(ip);
		log.info(value);
		if(value==null){
			loginName="游客"+Utils.randomNum();
			memCache.setMemcache(ip, loginName,1000*60*30);
		}else {
			loginName=value;
		}
		articleDos=Utils.getListFromHits(searchHits);
		if(articleDos!=null){
			request.setAttribute("blog", articleDos);
		}else {
			request.setAttribute("bog", null);
		}
		request.setAttribute("loginName", loginName);
		return "index";
	}

}