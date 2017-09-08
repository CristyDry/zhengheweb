<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>医生职称管理</title>
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
		<li class="active"><a href="${ctx}/zhenghe/zhengheProfession/">医生职称列表</a></li>
		<shiro:hasPermission name="zhenghe:zhengheProfession:edit"><li><a href="${ctx}/zhenghe/zhengheProfession/form">医生职称添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="zhengheProfession" action="${ctx}/zhenghe/zhengheProfession/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>医生称职：</label>
				<form:input path="profession" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>医生称职</th>
				<shiro:hasPermission name="zhenghe:zhengheProfession:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="zhengheProfession">
			<tr>
				<td><a href="${ctx}/zhenghe/zhengheProfession/form?id=${zhengheProfession.id}">
					${zhengheProfession.profession}
				</a></td>
				<shiro:hasPermission name="zhenghe:zhengheProfession:edit"><td>
    				<a href="${ctx}/zhenghe/zhengheProfession/form?id=${zhengheProfession.id}">修改</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>