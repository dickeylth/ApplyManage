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
	<form id="search" method="post"
		action="ApplicationType_queryByPropAction.do">
		<s:select list="properties" id="property" name="property" headerKey=""
			headerValue="--选择搜索字段--" value="property" />
		<input type="text" name="keyword" id="keyword"
			value="<s:property value="keyword"/>" /> <input type="submit"
			id="search_btn" class="button" value="<s:text name='search'/>" />
	</form>
	<form id="options" method="post">
		<table>
			<thead>
				<tr>
					<td></td>
					<td><s:text name="applicationType.id" /></td>
					<td><s:text name="applicationType.typeName" /></td>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="models" id="model">
					<tr>
						<td><input type="checkbox" name="checkItems" class="check"
							value="<s:property value="#model.id"/>" /></td>
						<td><a
							href="<s:url action='ApplicationType_editAction'><s:param name='id' value='#model.id'/></s:url>"><s:property
									value="#model.id" /></a></td>
						<td><s:property value="#model.typeName" /></td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
		<ul>
			<shiro:hasPermission name="applicationType:add">
				<li><input type="submit" value="新增" id="add"
					data-action="ApplicationType_addAction.do?refClass=${refClass}&refId=${refId}"
					class="ctrl button" /></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="applicationType:delete">
				<li><input type="submit" value="删除" id="delete"
					data-action="ApplicationType_deleteAction.do?refClass=${refClass}&refId=${refId}"
					class="ctrl button" /></li>
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