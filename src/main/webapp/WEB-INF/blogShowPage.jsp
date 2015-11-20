<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <script type="text/javascript" src="../js/jquery.min.js"></script>
	<script type="text/javascript" src="../js/blogShowPage.js"></script>
    <link rel="stylesheet" type="text/css" href="../css/blogShowPage.css">
	<title></title>
</head>
<body>
	<% String content=(String)request.getAttribute("content");  
        String title=(String)request.getAttribute("title"); %>
        <input type="hidden" id="title" value="<%=title %>">
        <div id="content">
            <div id="centerContent"><%=content %> </div>           
        </div>
    <script type="text/javascript">
        var  title=$("#title").val();
        window.document.title=title;
    </script>
</body>
</html>