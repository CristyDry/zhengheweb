<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>patient管理</title>
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
		<li class="active"><a href="${ctx}/zhenghe/zhenghePatient/">patient列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="zhenghePatient" action="${ctx}/zhenghe/zhenghePatient/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>姓名：</label>
				<form:input path="patientName" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>状态：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('patient_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>患者姓名</th>
				<th>性别</th>
				<th>年龄</th>
				<th>头像</th>
				<th>注册时间</th>
				<th>状态</th>
				<th>手机号</th>
				<th>省份</th>
				<th>城市</th>
				<th>区县</th>
				<shiro:hasPermission name="zhenghe:zhenghePatient:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="zhenghePatient">
			<tr>
				<td><a href="${ctx}/zhenghe/zhenghePatient/form?id=${zhenghePatient.id}">
					${zhenghePatient.patientName}
				</a></td>
				<td>
					${zhenghePatient.gender}
				</td>
				<td>
					${zhenghePatient.age}
				</td>
				<td>
					<img src="${zhenghePatient.avatar}"  class="avatar"/>
				</td>
				<td>
					<fmt:formatDate value="${zhenghePatient.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getDictLabel(zhenghePatient.status, 'patient_status', '')}
				</td>
				<td>
					${zhenghePatient.phone}
				</td>
				<td>
					${zhenghePatient.provinceName}
				</td>
				<td>
					${zhenghePatient.cityName}
				</td>
				<td>
					${zhenghePatient.districtName}
				</td>
				<shiro:hasPermission name="zhenghe:zhenghePatient:edit"><td>
    				<a href="${ctx}/zhenghe/zhenghePatient/form?id=${zhenghePatient.id}">修改</a>
					<%-- <a href="${ctx}/zhenghe/zhenghePatient/delete?id=${zhenghePatient.id}" onclick="return confirmx('确认要删除该patient吗？', this.href)">删除</a>--%>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>