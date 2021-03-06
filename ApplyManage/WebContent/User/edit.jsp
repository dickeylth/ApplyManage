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
		${title}
		<s:text name="user" />
	</h3>
	<s:form action="User_editSubmitAction" method="post" id="editForm">
		<s:hidden name="refClass" value="%{refClass}" />
		<s:hidden name="refId" value="%{refId}" />
		<s:hidden name="model.id" id="model_id" value="%{model.id}" />
		<s:textfield name="model.username" value="%{model.username}"
			key="user.username" />
		<s:textfield name="model.password" value="%{model.password}"
			key="user.password" />

		<!-- 多对多 -->
		<s:bean name="com.dickey.dao.impl.RoleDaoImpl" id="roleDao"/>
		<s:checkboxlist value="model.roles.{id}" list="#roleDao.all" key="user.roles"
			listKey="id" listValue="name" cssClass="checkboxlist" name="model.roles"/>
		<!-- 多对多 -->

		<!-- 一对多 -->
		<s:if test="model.id != null">
			<s:url id="application_url" action="Application_queryByRefAction"
				escapeAmp="false">
				<s:param name="refClass">User</s:param>
				<s:param name="refId">${model.id}</s:param>
			</s:url>
			<s:textfield cssClass="text_url" value="%{application_url}"
				key="user.applications" />
		</s:if>
		<!-- 一对多 -->

		<!-- 一对一 -->
		<s:if test="model.id != null">
			<s:url id="address_url" action="Address_editAction" escapeAmp="false">
				<s:param name="refClass">User</s:param>
				<s:param name="refId">${model.id}</s:param>
			</s:url>
			<s:textfield cssClass="text_url" value="%{address_url}"
				key="user.address" />
		</s:if>
		<!-- 一对一 -->
		
		<!-- 集合类型 -->
		<s:hidden name="model.phones" value="%{model.phones}" cssClass="collections"/>
		<s:label key="user.phones"/>
		

		<s:submit id="submit" key="submit" cssClass="button" />
	</s:form>
	<iframe src="" name="view" id="view"></iframe>
	<a id="close">×</a>
	<script src="js/jquery-1.8.3.min.js"></script>
	<script src="js/edit.js"></script>
</body>
</html>