<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/iframe.css" />
</head>
<body>
	<form id="search" method="post" action="Permission_queryByPropAction.do">
		<s:select list="properties" id="property" name="property" 
				headerKey="" headerValue="--选择搜索字段--" value="property"/>
		<input type="text" name="keyword" id="keyword" value="<s:property value="keyword"/>" />
		<input type="submit" id="search_btn" class="button" value="<s:text name='search'/>" />
		<s:fielderror />
	</form>
	<form id="options" method="post">
		<table>
			<thead>
				<tr>
					<td></td>
					<td><s:text name="permission.id" /></td>
					<td><s:text name="permission.permission" /></td>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="models" id="model">
					<tr>
						<td><input type="checkbox" name="checkItems" class="check"
							value="<s:property value="#model.id"/>" /></td>
						<td><a
							href="<s:url action='Permission_editAction'><s:param name='id' value='#model.id'/></s:url>"><s:property
									value="#model.id" /></a></td>
						<td><s:property value="#model.permission" /></td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
		<ul>
			<li><input type="submit" value="增加" id="add"
				data-action="Permission_addAction.do" class="ctrl button" /></li>
			<li><input type="submit" value="删除" id="delete"
				data-action="Role_deleteAction.do" class="ctrl button" /></li>
		</ul>
	</form>
	<script type="text/javascript"
		src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
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