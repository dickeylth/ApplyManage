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
		${title}<s:text name="applicationType" />
	</h3>
	<s:form action="ApplicationType_editSubmitAction" method="post"
		id="editForm">
		<s:hidden name="refClass" value="%{refClass}" />
		<s:hidden name="refId" value="%{refId}" />
		<s:hidden name="model.id" id="model_id" value="%{model.id}" />
		<s:textfield name="model.typeName" value="%{model.typeName}"
			key="applicationType.typeName" />

		<!-- 一对多 -->
		<s:if test="model.id != null">
			<s:url id="application" action="Application_queryByRefAction"
				escapeAmp="false">
				<s:param name="refClass">ApplicationType</s:param>
				<s:param name="refId">${model.id}</s:param>
			</s:url>
			<s:textfield name="application" cssClass="text_url"
				value="%{application}" key="applicationType.applications" />
		</s:if>
		<!-- 一对多 -->

		<s:submit key="submit" cssClass="button" />
	</s:form>
	<iframe src="" name="view" id="view"></iframe>
	<a id="close">×</a>
	<script src="js/jquery-1.8.3.min.js"></script>
	<script src="js/edit.js"></script>
</body>
</html>