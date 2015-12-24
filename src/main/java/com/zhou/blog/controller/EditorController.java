package com.zhou.blog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhou.blog.model.ArticleDo;
import com.zhou.blog.utils.ESearch;
import com.zhou.blog.utils.Utils;

@Controller
@RequestMapping("/")
public class EditorController {
	@RequestMapping("editor")
	public String editor(HttpServletRequest request){
		return "editor";
	}
	@RequestMapping("addBlog")
	public void add(HttpServletRequest request,HttpServletResponse response){
		ArticleDo articleDo=Utils.getArticleDo(request);
		ESearch eSearch=new ESearch();
		eSearch.createIndexArticle(articleDo);
		Utils.printSuccess(null, "插入成功", response);
	} 
}
