<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="css/iframe.css"/>
</head>
<body>
	<form id="search" method="post" action="User_queryByPropAction.do">
		<select id="property" name="property">
			<option value="id" <s:if test='%{property=="id"}'>selected="selected"</s:if>><s:text name="user.id"/></option>
			<option value="username" <s:if test='%{property=="username"}'>selected="selected"</s:if>><s:text name="user.username"/></option>
			<option value="password" <s:if test='%{property=="password"}'>selected="selected"</s:if>><s:text name="user.password"/></option>
		</select>
		<input type="text" name="keyword" id="keyword" value="<s:property value="keyword"/>"/>
		<input type="submit" id="search_btn" class="button" value="<s:text name='search'/>"/>
		<s:fielderror/>
	</form>
	<form id="options" method="post">
		<table>
			<thead>
				<tr>
					<td></td>
					<td><s:text name="user.id"/></td>
					<td><s:text name="user.username"/></td>
					<td><s:text name="user.password"/></td>
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
				<input type="submit" value="增加" id="add" data-action="User_addAction.do" class="ctrl button"/>
			</li>
			<li>
				<input type="submit" value="删除" id="delete" data-action="User_deleteAction.do" class="ctrl button"/>
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