<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/iframe.css" />
</head>
<body>
	<div class="breadcrumb">
		<shiro:hasPermission name="system:manage">
			<div class="adminli"><a href="javascript:;">系统管理</a>
				<ul class="sysadmin">
					<shiro:hasPermission name="user:manage">
						<li><a href="<s:url action="User_queryAction"/>"
							target="main"><s:text name="user" />管理</a></li>
					</shiro:hasPermission>
					<shiro:hasPermission name="role:manage">
						<li><a href="<s:url action="Role_queryAction"/>"
							target="main"><s:text name="role" />管理</a></li>
					</shiro:hasPermission>
					<shiro:hasPermission name="permission:manage">
						<li><a href="<s:url action="Permission_queryAction"/>"
							target="main"><s:text name="permission" />管理</a></li>
					</shiro:hasPermission>
				</ul>
			</div>
		</shiro:hasPermission>
		<img src="img/seperator.png" style="float:left;width:13px;height:33px">
		<span class="breaditem"><s:text name="role" />管理</span>
	</div>
	<form id="search" method="post" action="Role_queryByPropAction.do">
		<s:select list="properties" id="property" name="property" headerKey=""
			headerValue="--选择搜索字段--" value="property" />
		<input type="text" name="keyword" id="keyword" value="${keyword}" />
		<input type="submit" id="search_btn" class="button" value="<s:text name='search'/>" />
	</form>
	<form id="options" method="post">
		<table>
			<thead>
				<tr>
					<td></td>
					<td><s:text name="role.id" /></td>
					<td><s:text name="role.rolename" /></td>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="models" id="model">
					<tr>
						<td><input type="checkbox" name="checkItems" class="check"
							value="<s:property value="#model.id"/>" /></td>
						<td><a
							href="<s:url action='Role_editAction'><s:param name='id' value='#model.id'/></s:url>"><s:property
									value="#model.id" /></a></td>
						<td><s:property value="#model.rolename" /></td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
		<ul>
			<shiro:hasPermission name="role:add">
				<li><input type="submit" value="新增" id="add"
					data-action="Role_addAction.do" class="ctrl button" /></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="role:delete">
				<li><input type="submit" value="删除" id="delete"
					data-action="Role_deleteAction.do" class="ctrl button" /></li>
			</shiro:hasPermission>
		</ul>
	</form>
	<script type="text/javascript"
		src="js/jquery-1.8.3.min.js"></script>
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