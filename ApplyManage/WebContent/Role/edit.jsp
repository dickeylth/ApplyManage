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
	<div class="breadcrumb">
		<div class="adminli">
			<a href="javascript:history.go(-1);" class="back">返回</a>
		</div>
	</div>
	<h3>
		${title}<s:text name="role" />
	</h3>
	<s:form action="Role_editSubmitAction" method="post" id="editForm">
		<s:hidden name="refClass" value="%{refClass}" />
		<s:hidden name="refId" value="%{refId}" />
		<s:hidden name="model.id" id="model_id" value="%{model.id}" />
		<s:textfield name="model.rolename" value="%{model.rolename}"
			key="role.rolename" />
			
		<!-- 多对多 -->
		<s:bean name="com.dickey.dao.impl.UserDaoImpl" id="userDao"/>
		<s:checkboxlist value="model.users.{id}" list="#userDao.all" key="role.users"
			listKey="id" listValue="username" cssClass="checkboxlist" name="model.users"/>
		<!-- 多对多 -->
			
		<!-- 多对多 -->
		<s:bean name="com.dickey.dao.impl.PermissionDaoImpl" id="permissionDao"/>
		<s:checkboxlist value="model.permissions.{id}" list="#permissionDao.all" key="role.permissions"
			listKey="id" listValue="permission" cssClass="checkboxlist" name="model.permissions"/>
		<!-- 多对多 -->
		
		<s:submit id="submit" key="submit" cssClass="button" />
	</s:form>
	<iframe src="" name="view" id="view"></iframe>
	<a id="close">×</a>
	<script src="js/jquery-1.8.3.min.js"></script>
	<script src="js/edit.js"></script>
</body>
</html>