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
	<form id="search" method="post" action="Application_queryByPropAction.do">
		<select id="property" name="property">
			<option value="id" <s:if test='%{property=="id"}'>selected="selected"</s:if>>Id</option>
			<option value="start" <s:if test='%{property=="start"}'>selected="selected"</s:if>>开始时间</option>
			<option value="end" <s:if test='%{property=="end"}'>selected="selected"</s:if>>结束时间</option>
			<option value="reason" <s:if test='%{property=="reason"}'>selected="selected"</s:if>>原因</option>
		</select>
		<input type="text" name="keyword" id="keyword" value="<s:property value="keyword"/>"/>
		<input type="submit" value="搜索"/>
		<s:fielderror/>
	</form>
	<form id="options" method="post">
		<table>
			<thead>
				<tr>
					<td></td>
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
							<input type="checkbox" name="checkItems" class="check" value="<s:property value="#application.id"/>"/>
						</td>
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
		<ul>
			<li>
				<input type="submit" value="增加" id="add" data-action="Application_addAction.do" class="ctrl"/>
			</li>
			<li>
				<input type="submit" value="删除" id="delete" data-action="Application_deleteAction.do" class="ctrl"/>
			</li>
		</ul>
	</form>
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
	<script>
		$(document).ready(function(){
			$('.ctrl').click(function(){
				//删除确认
				if($(this).attr('id') == "delete"){
					if(!confirm("您确定要删除选中记录吗？")){
						return;
					}
				}
				$('form').attr('action',$(this).data('action')).submit();
			});
			
		});
	</script>
</body>
</html>