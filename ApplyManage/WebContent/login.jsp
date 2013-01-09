<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>欢迎登录系统</title>
</head>
<body>
	<s:form action="login" method="post">
		<s:textfield name="username" label="用户名"/>
		<s:password name="password" label="密码"/>
		<s:checkbox name="rememberMe" label="记住我"/>
		<s:submit value="登录"/>
	</s:form>
	<s:debug></s:debug>
	<%-- <%=request.getAttribute("shiroLoginFailure") %> --%>
	<p class="tip">${sessionScope.tip }</p>
</body>
</html>