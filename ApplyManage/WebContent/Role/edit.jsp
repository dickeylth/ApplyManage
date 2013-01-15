<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑</title>
<link rel="stylesheet" href="css/iframe.css" />
</head>
<body style="display: none">
	<h3>
		${title}<s:text name="role" />
	</h3>
	<s:form action="Role_editSubmitAction" method="post" id="editForm">
		<s:hidden name="refClass" value="%{refClass}" />
		<s:hidden name="refId" value="%{refId}" />
		<s:hidden name="model.id" id="model_id" value="%{model.id}" />
		<s:textfield name="model.rolename" value="%{model.rolename}"
			key="role.rolename" />
		<s:checkboxlist name="users" list="sysUsers" key="role.users"
			listKey="id" listValue="username" cssClass="checkboxlist" />
		<s:checkboxlist name="permissions" list="sysPermissions"
			label="role.permissions" listKey="id" listValue="permission"
			value="permissions" />
		<s:submit id="submit" key="submit" cssClass="button" />
	</s:form>
	<iframe src="" name="view" id="view"></iframe>
	<a id="close">×</a>
	<script type="text/javascript"
		src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
	<script src="js/edit.js"></script>
</body>
</html>