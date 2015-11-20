<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <script type="text/javascript" src="../../js/jquery.min.js"></script>
	<script type="text/javascript" src="../js/editor.js"></script>
	<title>新增</title>
</head>
<body>
	<!-- 加载编辑器的容器 -->
    <script id="container" name="content" type="text/plain">这里写你的初始化内容</script>
    <!-- 配置文件 -->
    <script type="text/javascript" src="../js/ueditor.config.js"></script>
    <!-- 编辑器源码文件 -->
    <script type="text/javascript" src="../js/ueditor.all.js"></script>
    <!-- 实例化编辑器 -->
    <script type="text/javascript">
        var ue = UE.getEditor('container');
    </script>
    <button id="save">保存</button>
</body>
</html>