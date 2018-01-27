<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>药师管理</title>
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
		<li class="active"><a href="${ctx}/zhenghe/zhenghePharmacist/">药师列表</a></li>
		<shiro:hasPermission name="zhenghe:zhenghePharmacist:edit"><li><a href="${ctx}/zhenghe/zhenghePharmacist/form">药师添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="zhenghePharmacist" action="${ctx}/zhenghe/zhenghePharmacist/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>电话：</label>
				<form:input path="tel" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>名称</th>
				<th>电话</th>
				<th>资质</th>
				<th>描述</th>
				<shiro:hasPermission name="zhenghe:zhenghePharmacist:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="zhenghePharmacist">
			<tr>
				<td><a href="${ctx}/zhenghe/zhenghePharmacist/form?id=${zhenghePharmacist.id}">
					${zhenghePharmacist.name}
				</a></td>
				<td>
					${zhenghePharmacist.tel}
				</td>
				<td>
					${zhenghePharmacist.img}
				</td>
				<td>
					${zhenghePharmacist.msg}
				</td>
				<shiro:hasPermission name="zhenghe:zhenghePharmacist:edit"><td>
    				<a href="${ctx}/zhenghe/zhenghePharmacist/form?id=${zhenghePharmacist.id}">修改</a>
					<a href="${ctx}/zhenghe/zhenghePharmacist/delete?id=${zhenghePharmacist.id}" onclick="return confirmx('确认要删除该药师吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>