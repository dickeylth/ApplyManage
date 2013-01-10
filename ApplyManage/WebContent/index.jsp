<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>欢迎来到XXXX系统</title>
	<link rel="stylesheet" href="css/common.css"/>
</head>
<body>
	<h2>尊敬的<shiro:principal/>，恭喜您登录成功！</h2>
	<h4>点击<a href="<s:url value="logout"/>">这里</a>登出</h4>
	<nav>
		<ul>
			<li><a href="<s:url action="Application_queryAction"/>" target="main">申请管理</a></li>
			<li><a href="<s:url action="User_queryAction"/>" target="main">用户管理</a></li>
		</ul>
	</nav>
	<iframe src="" id="main" name="main"></iframe>
	<s:debug></s:debug>
</body>
</html>