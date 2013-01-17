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
		${title}<s:text name="application" />
	</h3>
	<s:form action="Application_editSubmitAction" method="post"
		id="editForm">
		<s:hidden name="refClass" value="%{refClass}" />
		<s:hidden name="refId" value="%{refId}" />
		<s:hidden name="model.id" id="model_id" value="%{model.id}" />

		<!-- 多对一 -->
		<s:bean name="com.dickey.dao.impl.ApplicationTypeDaoImpl" id="applicationTypeDao"/>
		<s:select list="#applicationTypeDao.all" name="model.applicationType.id"
			listKey="id" listValue="typeName" key="application.applicationType"
			value="%{model.applicationType.id}" headerKey=""
			headerValue="--请选择--" />

		<s:textfield name="model.start" value="%{model.start}"
			key="application.start" />
		<s:textfield name="model.end" value="%{model.end}"
			key="application.end" />
		<s:textfield name="model.reason" value="%{model.reason}"
			key="application.reason" />
		<s:submit key="submit" cssClass="button" />
	</s:form>
	<iframe src="" name="view" id="view"></iframe>
	<a id="close">×</a>
	<script src="js/jquery-1.8.3.min.js"></script>
	<script src="js/edit.js"></script>
</body>
</html>