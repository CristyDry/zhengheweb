<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单项管理</title>
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
		<li class="active"><a href="${ctx}/zhenghe/zhengheOrderItem/">订单项列表</a></li>
		<shiro:hasPermission name="zhenghe:zhengheOrderItem:edit"><li><a href="${ctx}/zhenghe/zhengheOrderItem/form">订单项添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="zhengheOrderItem" action="${ctx}/zhenghe/zhengheOrderItem/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>子订单号：</label>
				<form:input path="orderItemNo" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>商品名称：</label>
				<form:input path="productName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>子订单号</th>
				<th>商品名称</th>
				<th>商品原价格</th>
				<th>商品实际购买价格</th>
				<th>合计购买价格</th>
				<th>购买份数</th>
				<th>时间</th>
				<shiro:hasPermission name="zhenghe:zhengheOrderItem:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="zhengheOrderItem">
			<tr>
				<td>
					${zhengheOrderItem.orderItemNo}
				</td>
				<td>
					${zhengheOrderItem.productName}
				</td>
				<td>
					${zhengheOrderItem.originalPrice}
				</td>
				<td>
					${zhengheOrderItem.realityPrice}
				</td>
				<td>
					${zhengheOrderItem.sumPrice}
				</td>
				<td>
					${zhengheOrderItem.count}
				</td>
				<td>
					<fmt:formatDate value="${zhengheOrderItem.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="form-actions">
		<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
	</div>
	<div class="pagination">${page}</div>
</body>
</html>