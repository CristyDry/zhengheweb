<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>医生管理管理</title>
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
		<li class="active"><a href="${ctx}/zhenghe/zhengheDoctor/">医生管理列表</a></li>
		<shiro:hasPermission name="zhenghe:zhengheDoctor:edit"><li><a href="${ctx}/zhenghe/zhengheDoctor/form">医生管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="zhengheDoctor" action="${ctx}/zhenghe/zhengheDoctor/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>姓名：</label>
				<form:input path="doctorName" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>类型：</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('doctor_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>状态：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('doctor_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>医院</th>
				<th>医生名</th>
				<th>性别</th>
				<th>科室</th>
				<th>头像</th>
				<th>专业领域</th>
				<th>职称</th>
				<th>注册时间</th>
				<th>状态</th>
				<th>手机号</th>
				<th>类型</th>
				<shiro:hasPermission name="zhenghe:zhengheDoctor:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="zhengheDoctor">
			<tr>
				<td>
					${zhengheDoctor.hospitalName }
				</td>
				<td><a href="${ctx}/zhenghe/zhengheDoctor/form?id=${zhengheDoctor.id}">
					${zhengheDoctor.doctorName}
				</a></td>
				<td>
					${fns:getDictLabel(zhengheDoctor.gender, 'gender', '')}
				</td>
				<td>
					${zhengheDoctor.departmentName }
				</td>
				<td>
					<img src="${zhengheDoctor.avatar}" class="avatar"/>
				</td>
				<td>
					${zhengheDoctor.professionalField}
				</td>
				<td>
					${zhengheDoctor.jobTitleName }
				</td>
				<td>
					<fmt:formatDate value="${zhengheDoctor.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getDictLabel(zhengheDoctor.status, 'doctor_status', '')}
				</td>
				<td>
					${zhengheDoctor.phone}
				</td>
				<td>
					${fns:getDictLabel(zhengheDoctor.type, 'doctor_type', '')}
				</td>
				<shiro:hasPermission name="zhenghe:zhengheDoctor:edit"><td>
    				<a href="${ctx}/zhenghe/zhengheDoctor/form?id=${zhengheDoctor.id}">修改</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>