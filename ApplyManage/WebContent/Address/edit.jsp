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
		${title}<s:text name="address" />
	</h3>
	<s:form action="Address_editSubmitAction" method="post" id="editForm">
		<s:hidden name="refClass" value="%{refClass}" />
		<s:hidden name="refId" value="%{refId}" />
		<s:hidden name="model.id" id="model_id" value="%{model.id}" />

		<s:textfield name="model.country" value="%{model.country}"
			key="address.country" />
		<s:textfield name="model.city" value="%{model.city}"
			key="address.city" />

		<!-- 一对一 -->
		<s:if test="model.id != null">
			<s:url id="user_url" action="User_editAction" escapeAmp="false">
				<s:param name="refClass">Address</s:param>
				<s:param name="refId">${model.id}</s:param>
			</s:url>
			<s:textfield cssClass="text_url" value="%{user_url}"
				key="address.user" />
		</s:if>
		<!-- 一对一 -->

		<s:submit key="submit" cssClass="button" />
	</s:form>
	<iframe src="" name="view" id="view"></iframe>
	<a id="close">×</a>
	<script type="text/javascript"
		src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
	<script src="js/edit.js"></script>
</body>
</html>