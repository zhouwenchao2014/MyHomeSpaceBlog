package com.zhou.blog.service.impl;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.zhou.blog.service.ESearchServer;
import com.zhou.blog.utils.ElasticSearch;

/**
 * Created by zhouwenchao on 15/12/23.
 */
@Service
public class ESearchServerImpl implements ESearchServer{
	
	private Logger logger=LoggerFactory.getLogger(ESearchServerImpl.class);


	public void insertElasticSearch(HttpServletRequest request) {
		Map<String, String[]> map=request.getParameterMap();
		ElasticSearch elasticSearch=new ElasticSearch();
		elasticSearch.createIndexArticle(generateJson(map));
//		;
//		
//		String index=request.getParameter("index");
//		String type=request.getParameter("type");
//		String uuid=request.getParameter("uuid");
//		String ip=request.getParameter("ip");
//		String date=request.getParameter("date");
//		String brower=request.getParameter("brower");
//		String mobileType=request.getParameter("mobileType");
//		String netType=request.getParameter("netType");
//		String navigator=request.getParameter("navigator");
//		String country=request.getParameter("country");
//		String province=request.getParameter("province");
//		String city=request.getParameter("city");
//		String fromurl=request.getParameter("fromurl");
//		String userInfo=request.getParameter("userInfo");
//		String cupInfo=request.getParameter("cupInfo");
	}
	
	public String generateJson(Map<String, String[]> map) {
		Set<String> keys=map.keySet();
		String json = "";
		try {
			XContentBuilder contentBuilder = XContentFactory.jsonBuilder().startObject();
			for(String key : keys){
				String value=map.get(key)[0];
				logger.info("Key="+key+",value="+value);
				contentBuilder.field(key, value + "");
			}
			json = contentBuilder.endObject().string();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json;
	}

	public void searchElasticSearch(HttpServletRequest request) {
		
	}
    
}
