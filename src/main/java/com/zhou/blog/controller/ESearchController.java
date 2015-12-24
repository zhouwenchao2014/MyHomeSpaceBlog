package com.zhou.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhou.blog.service.ESearchServer;
import com.zhou.blog.service.ElasticSearchMonitorServer;
import com.zhou.blog.utils.Utils;


import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zhouwenchao on 15/12/23.
 */
@Controller
@RequestMapping("/")
public class ESearchController {
	
	@Autowired
	private ESearchServer eSearchServer;
	
	@Autowired
	private ElasticSearchMonitorServer elasticSearchMonitorServer;
	
	@RequestMapping("get_elastic_search_view")
	@ResponseBody
    public String insertEsView(){
    	return "es";
    }
	
    @RequestMapping("add_elastic_search")
    public void insertEs(HttpServletRequest request){
    	eSearchServer.insertElasticSearch(request);
    }
    @RequestMapping("get_elastic_search_url")
    @ResponseBody
    public String searchUrl(HttpServletRequest request){
    	String message=elasticSearchMonitorServer.getMessageByIndexName(request);
    	return message;
    }
    
    @RequestMapping("get_elastic_search_client")
    @ResponseBody
    public Object searchClient(HttpServletRequest request){
    	String message=elasticSearchMonitorServer.getMessageByIndexNameFromClient(request);
    	
//    	message=JSON.toJSONString(message);
//    	JSON json=JSONObject.parseObject(message);
//    	message.replace("\\", "");
//    	message.replace("\"", "");
    	return JSONObject.toJSON(message);
    }
    
    @RequestMapping("get_index_elastic_search")
    @ResponseBody
    public String searchIndex(HttpServletRequest request,HttpServletResponse response){
    	String message=elasticSearchMonitorServer.getIndexName(request);
    	return JSON.toJSONString(message);
    }
}
