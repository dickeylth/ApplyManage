<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>申请管理</title>
</head>
<body>
	<h2>这里是申请管理页面</h2>
	<table>
		<thead>
			<tr>
				<td>Id</td>
				<td>开始时间</td>
				<td>结束时间</td>
				<td>原因</td>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="applications" id="application">
				<tr>
					<td>
						<a href="<s:url action='Application_editAction'><s:param name='id' value='#application.id'/></s:url>"><s:property value="#application.id"/></a>
					</td>
					<td>
						<s:property value="#application.start"/>
					</td>
					<td>
						<s:property value="#application.end"/>
					</td>
					<td>
						<s:property value="#application.reason"/>
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</body>
</html>