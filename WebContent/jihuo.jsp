<%@ page language="java" contentType="text/html;charset=utf-8" %> 
<%@page import="com.ring.front.web.util.WebUtils"%>
<%
	String login_user = WebUtils.getCurrentUser();
   	// 定义绝对路径
	String basePath=WebUtils.getUrl();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>LIGHT</title>
<link href="<%=basePath %>/css/Public_style.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>/css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>/css/Backstage.css" rel="stylesheet" type="text/css" />
<script src="<%=basePath %>/js/jquery.min.js"></script>
<script src="<%=basePath %>/js/table.js"></script>
</head>
<body>
	<h2>激活成功！</h2>
</body>
</html>
