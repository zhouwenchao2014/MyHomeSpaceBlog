<%@page import="java.util.List"%>
<%@ page language="java" import="com.zhou.blog.model.ArticleDo"%>  
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../../css/index.css">
<script type="text/javascript" src="../../js/jquery.min.js"></script>
<script type="text/javascript" src="../../js/index.js"></script>
<script type="text/javascript" src="../../js/common.js"></script>
<link rel="stylesheet" type="text/css" href="../../css/common.css">
<title>主页</title>
</head>
<body>
	<div id="loadContent"><div id="loadBar" style="z-index:100"></div></div>
	<div class="leftBar"></div>
	<div class="leftShow">
		<div class="declare">
			<div id="bg"></div>
			<span class="declareTitle">公告</span>
			<div class="declareMsg">
				<p>排版某些灵感来至于简书，仅个人使用。如果触犯了版权问题请通知我，立即删除。联系邮箱：953685177@qq.com</p>
			</div>
		</div>
	</div>
	<div class="topBar">
		<div class="time"></div>
		<div class="loginImg"></div>
		<% String loginName=(String)request.getAttribute("loginName"); %>
		<div id="detail"><%=loginName %></div>
	</div>
	<div class="content">
		<div id="contentTop">
			<div class="tips">
				<span style="float: left">标签:</span>
				<div id="JAVA" class="tip">JAVA</div>
				<div id="MYSQL" class="tip">MYSQL</div>
				<div id="Memcached" class="tip">Memcached</div>
				<div id="ElasticSearch" class="tip">ElasticSearch</div>
				<div id="Redis" class="tip">Redis</div>
				<div id="Oracle" class="tip">Oracle</div>
				<div id="Javascript" class="tip">Javascript</div>
			</div>
			<div id="search">
			<span>搜索：</span>
			<input type="text">
			</div>
		</div>
		<div class="contentCenter">
			<ul>
				<% List<ArticleDo> list=(List<ArticleDo>)request.getAttribute("blog"); 
					for(int i=0;i<list.size();i++){
						ArticleDo articleDo=list.get(i);					
				%>
				<li class="bloglist">
					<div class="title">
						<h3><%=articleDo.getTitle() %></h3>
					</div>
					<div class="rightTime"><%=articleDo.getWriteTime() %></div>
					<div class="bar">喜欢:1 评论:10</div>
					<input type="hidden" id="uuid" value="<%=articleDo.getUuid() %>">
				</li>
				<%} %>
				
			</ul>
		</div>
	</div>
</body>
</html>