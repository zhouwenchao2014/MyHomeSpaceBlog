package com.zhou.blog.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhou.blog.model.ArticleDo;
import com.zhou.blog.utils.ESearch;
import com.zhou.blog.utils.Utils;

@Controller
@RequestMapping("/search")
public class SearchController {

	// @Autowired
	// private HttpServletRequest httpServletRequest;

	@RequestMapping("/elasticSearch")
	@ResponseBody
	public void search(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		List<ArticleDo> articleDos = new ArrayList<ArticleDo>();
		ESearch eSearch = new ESearch();
		SearchHits searchHits = eSearch.queryAll(httpServletRequest);
		articleDos=Utils.getListFromHits(searchHits);
		if(articleDos!=null){
			Utils.printSuccess(articleDos, "查询成功", httpServletResponse);
		}else {
			Utils.printFailure(articleDos, "查询失败", httpServletResponse);
		}	
	}
}
