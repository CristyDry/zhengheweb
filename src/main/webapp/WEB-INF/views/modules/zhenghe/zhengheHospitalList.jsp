<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>医院管理</title>
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
		<li class="active"><a href="${ctx}/zhenghe/zhengheHospital/">医院列表</a></li>
		<shiro:hasPermission name="zhenghe:zhengheHospital:edit"><li><a href="${ctx}/zhenghe/zhengheHospital/form">医院添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="zhengheHospital" action="${ctx}/zhenghe/zhengheHospital/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>医院名：</label>
				<form:input path="hospitalName" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>地址：</label>
				<form:input path="address" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>医院名</th>
				<th>省份</th>
				<th>城市</th>
				<th>区县</th>
				<th>具体地址</th>
				<shiro:hasPermission name="zhenghe:zhengheHospital:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="zhengheHospital">
			<tr>
				<td><a href="${ctx}/zhenghe/zhengheHospital/form?id=${zhengheHospital.id}">
					${zhengheHospital.hospitalName}
				</a></td>
				<td>
					${zhengheHospital.provinceName}
				</td>
				<td>
					${zhengheHospital.cityName}
				</td>
				<td>
					${zhengheHospital.districtName}
				</td>
				<td>
					${zhengheHospital.address}
				</td>
				<shiro:hasPermission name="zhenghe:zhengheHospital:edit"><td>
    				<a href="${ctx}/zhenghe/zhengheHospital/form?id=${zhengheHospital.id}">修改</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>