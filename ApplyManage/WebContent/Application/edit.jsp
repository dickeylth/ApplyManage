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
	<s:form action="Application_editSubmitAction" method="post">
		<s:hidden name="id" value="%{id}"/>
		<s:textfield name="start" value="%{start}" label="开始时间"/>
		<s:textfield name="end" value="%{end}" label="结束时间"/>
		<s:textfield name="reason" value="%{reason}" label="请假原因"/>
		<s:submit value="提交"/>
	</s:form>
	<s:debug/>
</body>
</html>