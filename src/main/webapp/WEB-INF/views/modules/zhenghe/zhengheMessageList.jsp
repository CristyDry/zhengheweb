<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>系统消息管理</title>
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
		<li class="active"><a href="${ctx}/zhenghe/zhengheMessage/">系统消息列表</a></li>
		<shiro:hasPermission name="zhenghe:zhengheMessage:edit"><li><a href="${ctx}/zhenghe/zhengheMessage/form">系统消息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="zhengheMessage" action="${ctx}/zhenghe/zhengheMessage/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>消息标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>类型：</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('message_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>角色：</label>
				<form:select path="differentiateId" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('idea_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>消息标题</th>
				<th>消息内容</th>
				<th>时间</th>
				<th>类型</th>
				<th>角色</th>
				<th>备注</th>
				<shiro:hasPermission name="zhenghe:zhengheMessage:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="zhengheMessage">
			<tr>
				<td><a href="${ctx}/zhenghe/zhengheMessage/form?id=${zhengheMessage.id}">
					${zhengheMessage.title}
				</a></td>
				<td>
					${zhengheMessage.content}
				</td>
				<td>
					<fmt:formatDate value="${zhengheMessage.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getDictLabel(zhengheMessage.type, 'message_type', '')}
				</td>
				<td>
					${fns:getDictLabel(zhengheMessage.differentiateId, 'idea_type', '')}
				</td>
				<td>
					${zhengheMessage.remark}
				</td>
				<shiro:hasPermission name="zhenghe:zhengheMessage:edit"><td>
					<a href="${ctx}/zhenghe/zhengheMessage/delete?id=${zhengheMessage.id}" onclick="return confirmx('确认要删除该系统消息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>