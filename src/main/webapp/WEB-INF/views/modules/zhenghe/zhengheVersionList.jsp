<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>版本管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/zhenghe/zhengheVersion/">版本管理列表</a></li>
		<shiro:hasPermission name="zhenghe:zhengheVersion:edit"><li><a href="${ctx}/zhenghe/zhengheVersion/form">版本管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="zhengheVersion" action="${ctx}/zhenghe/zhengheVersion/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>版本名：</label>
				<form:input path="versionName" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>版本编号：</label>
				<form:input path="versionCode" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>渠道：</label>
				<form:input path="channel" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>类型：</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('version_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>版本名</th>
				<th>版本编号</th>
				<th>地址</th>
				<th>渠道</th>
				<th>版本描述</th>
				<th>类型</th>
				<th>备注</th>
				<shiro:hasPermission name="zhenghe:zhengheVersion:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="zhengheVersion">
			<tr>
				<td><a href="${ctx}/zhenghe/zhengheVersion/form?id=${zhengheVersion.id}">
					${zhengheVersion.versionName}
				</a></td>
				<td>
					${zhengheVersion.versionCode}
				</td>
				<td>
					${zhengheVersion.url}
				</td>
				<td>
					${zhengheVersion.channel}
				</td>
				<td>
					${zhengheVersion.description}
				</td>
				<td>
					${fns:getDictLabel(zhengheVersion.type, 'version_type', '')}
				</td>
				<td>
					${zhengheVersion.remark}
				</td>
				<shiro:hasPermission name="zhenghe:zhengheVersion:edit"><td>
    				<a href="${ctx}/zhenghe/zhengheVersion/form?id=${zhengheVersion.id}">修改</a>
					<a href="${ctx}/zhenghe/zhengheVersion/delete?id=${zhengheVersion.id}" onclick="return confirmx('确认要删除该版本管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>