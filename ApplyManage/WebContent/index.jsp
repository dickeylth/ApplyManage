<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>欢迎登录<s:text name="sysname"/></title>
	<link rel="stylesheet" href="css/common.css"/>
	<link rel="stylesheet" href="css/index.css"/>
</head>
<body>
	<div id="top">
		<h1><s:text name="sysname"/></h1>
	</div>
	<nav>
		<ul>
			<li><a href="<s:url action="Application_queryAction"/>" target="main"><s:text name="application"/>管理</a></li>
			<li><a href="<s:url action="User_queryAction"/>" target="main"><s:text name="user"/>管理</a></li>
			<li id="account">你好！<shiro:principal/><a href="<s:url value="logout"/>" id="logout"><s:text name="logout"/></a></li>
		</ul>
	</nav>
	<iframe src="default.jsp" name="main" id="main"></iframe>
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
	<script>
		$(document).ready(function(){
			$('nav a').click(function(){
				$('nav .on').removeClass('on');
				$(this).addClass('on');
			});
			
		});
	</script>
</body>
</html>