<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户反馈管理</title>
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
		<li class="active"><a href="${ctx}/zhenghe/zhengheIdea/">用户反馈列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="zhengheIdea" action="${ctx}/zhenghe/zhengheIdea/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>类型：</label>
				<form:select path="type" class="input-medium">
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
				<th>姓名</th>
				<th>内容</th>
				<th>手机</th>
				<th>时间</th>
				<th>类型</th>
				<th>版本</th>
				<th>系统</th>
				<th>机型</th>
				<shiro:hasPermission name="zhenghe:zhengheIdea:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="zhengheIdea">
			<tr>
				<td>
					${zhengheIdea.standby1}
				</td>
				<td>
					${zhengheIdea.content}
				</td>
				<td>
					${zhengheIdea.phone}
				</td>
				<td>
					<fmt:formatDate value="${zhengheIdea.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getDictLabel(zhengheIdea.type, 'idea_type', '')}
				</td>
				<td>
					${zhengheIdea.zhengheVersion}
				</td>
				<td>
					${zhengheIdea.zhengheSystem}
				</td>
				<td>
					${zhengheIdea.zhengheType}
				</td>
				<shiro:hasPermission name="zhenghe:zhengheIdea:edit"><td>
					<a href="${ctx}/zhenghe/zhengheIdea/delete?id=${zhengheIdea.id}" onclick="return confirmx('确认要删除该用户反馈吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>