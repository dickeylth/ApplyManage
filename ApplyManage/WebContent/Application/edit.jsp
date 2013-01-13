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
<body>
	<h3>
		${title}
		<s:text name="application" />
	</h3>
	<s:form action="Application_editSubmitAction" method="post"
		id="editForm">
		<s:hidden name="model.id" id="model_id" value="%{model.id}" />
		<s:textfield name="model.start" value="%{model.start}"
			key="application.start" />
		<s:textfield name="model.end" value="%{model.end}"
			key="application.end" />
		<s:textfield name="model.reason" value="%{model.reason}"
			key="application.reason" />
		<s:submit id="submit" key="submit" cssClass="button" />
	</s:form>
</body>
</html>