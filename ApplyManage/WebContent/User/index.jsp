<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>用户管理</title>
</head>
<body>
	<h2>这里是用户管理页面</h2>
	<form id="search" method="post" action="User_queryByPropAction.do">
		<select id="property" name="property">
			<option value="id" <s:if test='%{property=="id"}'>selected="selected"</s:if>>Id</option>
			<option value="username" <s:if test='%{property=="username"}'>selected="selected"</s:if>>用户名</option>
			<option value="password" <s:if test='%{property=="password"}'>selected="selected"</s:if>>密码</option>
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
					<td>用户名</td>
					<td>密码</td>
					<td>原因</td>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="users" id="user">
					<tr>
						<td>
							<input type="checkbox" name="checkItems" class="check" value="<s:property value="#user.id"/>"/>
						</td>
						<td>
							<a href="<s:url action='User_editAction'><s:param name='id' value='#user.id'/></s:url>"><s:property value="#user.id"/></a>
						</td>
						<td>
							<s:property value="#user.username"/>
						</td>
						<td>
							<s:property value="#user.password"/>
						</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
		<ul>
			<li>
				<input type="submit" value="增加" id="add" data-action="User_addAction.do" class="ctrl"/>
			</li>
			<li>
				<input type="submit" value="删除" id="delete" data-action="User_deleteAction.do" class="ctrl"/>
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