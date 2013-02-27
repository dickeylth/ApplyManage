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
		<div class="adminli"><a href="javascript:;">请假管理</a>
			<ul class="sysadmin">
				<li><a href="<s:url action="Application_queryAction"/>" target="main">我的申请</a></li>
				<li><a href="<s:url action="Application_queryTaskAction"/>" target="main">我的任务</a></li>
				<li><a href="<s:url action="Application_queryHistAction"/>" target="main">历史任务</a></li>
			</ul>
		</div>
		<img src="img/seperator.png" style="float:left;width:13px;height:33px">
		<span class="breaditem">我的申请</span>
	</div>
	<form id="search" method="post"
		action="Application_queryByPropAction.do">
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
					<td><s:text name="application.id" /></td>
					<td><s:text name="applicationType.typeName" /></td>
					<td><s:text name="application.start" /></td>
					<td><s:text name="application.end" /></td>
					<td><s:text name="application.reason" /></td>
					<td>状态</td>
					<td>操作</td>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="models" id="model">
					<tr>
						<td><input type="checkbox" name="checkItems" class="check"
							value="<s:property value="#model.id"/>" /></td>
						<td><a
							href="<s:url action='Application_editAction'><s:param name='id' value='#model.id'/></s:url>"><s:property
									value="#model.id" /></a></td>
						<td><s:property value="#model.applicationType.typeName" /></td>
						<td><s:property value="#model.start" /></td>
						<td><s:property value="#model.end" /></td>
						<td><s:property value="#model.reason" /></td>
						<td><s:property value="#model.bizWorkflow.status" /></td>
						<td>
							<s:if test="%{#model.bizWorkflow.step == 0}">
								<a href="<s:url action='Application_applyAction'><s:param name='id' value='#model.id'/></s:url>">申请</a>
							</s:if>
						</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
		<ul>
			<shiro:hasPermission name="application:add">
				<li><input type="submit" value="新增" id="add"
					data-action="Application_addAction.do?refClass=${refClass}&refId=${refId}"
					class="ctrl button" /></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="application:delete">
				<li><input type="submit" value="删除" id="delete"
					data-action="Application_deleteAction.do?refClass=${refClass}&refId=${refId}"
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
				$(this).closest('form').attr('action',$(this).data('action')).submit();
			});
			
		});
	</script>
</body>
</html>