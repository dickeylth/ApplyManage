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
<body style="display:none">
	<h3>
		${title}<s:text name="user" />
	</h3>
	<s:form action="User_editSubmitAction" method="post" id="editForm">
		<s:hidden name="model.id" id="model_id" value="%{model.id}" />
		<s:textfield name="model.username" value="%{model.username}"
			key="user.username" />
		<s:textfield name="model.password" value="%{model.password}"
			key="user.password" />
		<s:checkboxlist name="roles" list="sysRoles" key="user.roles"
			listKey="id" listValue="rolename" cssClass="checkboxlist"/>
		<s:url id="application" action="Application_queryByRefAction" escapeAmp="false">
			<s:param name="refClass">User</s:param>
			<s:param name="refId">${model.id}</s:param>
		</s:url>
		<s:textfield name="application" cssClass="text_url" value="%{application}" key="user.applications"/>
		<s:submit id="submit" key="submit" cssClass="button" />
	</s:form>
	<iframe src="" name="view" id="view"></iframe>
	<a id="close">×</a>
	<script type="text/javascript"
		src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
	<script src="js/edit.js"></script>
</body>
</html>