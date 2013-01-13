<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>编辑</title>
	<link rel="stylesheet" href="css/iframe.css"/>
</head>
<body>
	<h3>${title}<s:text name="role"/></h3>
	<s:form action="Role_editSubmitAction" method="post" id="editForm">
		<s:hidden name="model.id" id="model_id" value="%{model.id}"/>
		<s:textfield name="model.rolename" value="%{model.rolename}" key="role.rolename"/>
		<s:checkboxlist name="permissions" list="sysPermissions" label="该用户所属权限" listKey="id" listValue="permission" value="permissions"/>
		<s:submit id="submit" key="submit" cssClass="button"/>
	</s:form>
</body>
</html>