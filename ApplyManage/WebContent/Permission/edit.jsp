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
		${title}<s:text name="permission" />
	</h3>
	<s:form action="Permission_editSubmitAction" method="post"
		id="editForm">
		<s:hidden name="refClass" value="%{refClass}" />
		<s:hidden name="refId" value="%{refId}" />
		<s:hidden name="model.id" id="model_id" value="%{model.id}" />
		<s:textfield name="model.permission" value="%{model.permission}"
			key="permission.permission" />
		
		<!-- 多对多 -->
		<s:bean name="com.dickey.dao.impl.RoleDaoImpl" id="roleDao"/>
		<s:checkboxlist value="model.roles.{id}" list="#roleDao.all" key="permission.roles"
			listKey="id" listValue="rolename" cssClass="checkboxlist" name="model.roles" />
		<!-- 多对多 -->
		
		<s:submit id="submit" key="submit" cssClass="button" />
	</s:form>
	<iframe src="" name="view" id="view"></iframe>
	<a id="close">×</a>
	<script src="js/jquery-1.8.3.min.js"></script>
	<script src="js/edit.js"></script>
</body>
</html>