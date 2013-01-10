<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>编辑</title>
</head>
<body>
	<s:form action="User_editSubmitAction" method="post">
		<s:hidden name="model.id" id="model_id" value="%{model.id}"/>
		<s:textfield name="model.username" value="%{model.username}" label="用户名"/>
		<s:textfield name="model.password" value="%{model.password}" label="密码"/>
		<s:submit id="submit" value="提交"/>
	</s:form>
	<s:debug/>
</body>
</html>