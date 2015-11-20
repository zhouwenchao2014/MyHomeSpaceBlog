package com.zhou.blog.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhou.blog.model.ArticleDo;
import com.zhou.blog.utils.ESearch;
import com.zhou.blog.utils.Utils;

@Controller
@RequestMapping("/")
public class ShowBlogController {
	
	@RequestMapping("/blogShowPage")
	public String showBlogPage(HttpServletRequest request){
		ESearch eSearch=new ESearch();
		String uuid=request.getParameter("uuid");
		List<ArticleDo> articleDos=Utils.getListFromHits(eSearch.queryByUuid("uuid", uuid));
		request.setAttribute("content", articleDos.get(0).getContent());
		request.setAttribute("title", articleDos.get(0).getTitle());
		return "blogShowPage";
	}
}
