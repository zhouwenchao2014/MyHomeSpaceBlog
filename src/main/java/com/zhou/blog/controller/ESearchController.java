package com.zhou.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhou.blog.service.ESearchServer;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhouwenchao on 15/12/23.
 */
@Controller
@RequestMapping("elastic_search")
public class ESearchController {
	
	@Autowired
	private ESearchServer eSearchServer;
	
	@RequestMapping("view")
    public String insertEs(){
    	return "es";
    }
	
    @RequestMapping("add")
    public void insertEs(HttpServletRequest request){
    	eSearchServer.insertElasticSearch(request);
    }
    @RequestMapping("get")
    public void search(HttpServletRequest request){

    }
}
