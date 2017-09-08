<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>科室管理</title>
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
	<style>
		.avatar{
			width:100px;
			height:140px;
		}
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/zhenghe/zhengheDepartments/">科室列表</a></li>
		<shiro:hasPermission name="zhenghe:zhengheDepartments:edit"><li><a href="${ctx}/zhenghe/zhengheDepartments/form">科室添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="zhengheDepartments" action="${ctx}/zhenghe/zhengheDepartments/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>科室名：</label>
				<form:input path="departmentsName" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>类型：</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('departments_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>科室名</th>
				<th>图标</th>
				<th>上级科室id</th>
				<th>描述</th>
				<th>类型</th>
				<th>备注</th>
				<shiro:hasPermission name="zhenghe:zhengheDepartments:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="zhengheDepartments">
			<tr>
				<td><a href="${ctx}/zhenghe/zhengheDepartments/form?id=${zhengheDepartments.id}">
					${zhengheDepartments.departmentsName}
				</a></td>
				<td>
					<img src="${zhengheDepartments.avatar}" class="avatar"/>
				</td>
				<td>
					<%-- ${fns:getDictLabel(zhengheDepartments.sDepartmentsId, '', '')} --%>
					${zhengheDepartments.sDepartmentName }
				</td>
				<td>
					${zhengheDepartments.description}
				</td>
				<td>
					${fns:getDictLabel(zhengheDepartments.type, 'departments_type', '')}
				</td>
				<td>
					${zhengheDepartments.remark}
				</td>
				<shiro:hasPermission name="zhenghe:zhengheDepartments:edit"><td>
    				<a href="${ctx}/zhenghe/zhengheDepartments/form?id=${zhengheDepartments.id}">修改</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>