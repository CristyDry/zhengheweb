<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>支付日志管理</title>
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
		<li class="active"><a href="${ctx}/zhenghe/zhenghePay/">支付日志列表</a></li>
		<shiro:hasPermission name="zhenghe:zhenghePay:edit"><li><a href="${ctx}/zhenghe/zhenghePay/form">支付日志添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="zhengheOrder" action="${ctx}/zhenghe/zhenghePay/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>患者</th>
				<th>类型</th>
				<th>金额</th>
				<th>状态</th>
				<th>时间</th>
				<shiro:hasPermission name="zhenghe:zhenghePay:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="zhenghePay">
			<tr>
				<td>
					${zhenghePay.patientId}
				</td>
				<td>
					${fns:getDictLabel(zhenghePay.type, 'pay_type', '')}
				</td>
				<td>
					${zhenghePay.paySum}
				</td>
				<td>
					${fns:getDictLabel(zhenghePay.status, 'pay_status','')}
				</td>
				<td>
					<fmt:formatDate value="${zhenghePay.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="zhenghe:zhenghePay:edit"><td>
    				<a href="${ctx}/zhenghe/zhenghePay/form?id=${zhenghePay.id}">修改</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>