<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单表管理</title>
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
		<li class="active"><a href="${ctx}/zhenghe/zhengheOrder/">订单表列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="zhengheOrder" action="${ctx}/zhenghe/zhengheOrder/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>收件人姓名：</label>
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>手机号码：</label>
				<form:input path="phone" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>订单号：</label>
				<form:input path="parentOrderNo" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>状态：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('order_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>递快单号：</label>
				<form:input path="expressNo" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>下单时间：</label>
				<input name="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${zhengheOrder.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${zhengheOrder.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>收件人姓名</th>
				<th>手机号码</th>
				<th>订单号</th>
				<th>订单金额</th>
				<th>状态</th>
				<th>快递公司名称</th>
				<th>递快单号</th>
				<th>送货地址</th>
				<th>下单时间</th>
				<shiro:hasPermission name="zhenghe:zhengheOrder:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="zhengheOrder">
			<tr>
				<td><a href="${ctx}/zhenghe/zhengheOrder/form?id=${zhengheOrder.id}">
					${zhengheOrder.name}
				</a></td>
				<td>
					${zhengheOrder.phone}
				</td>
				<td>
					${zhengheOrder.parentOrderNo}
				</td>
				<td>
					${zhengheOrder.totalAmount}
				</td>
				<td>
					${fns:getDictLabel(zhengheOrder.status, 'order_status', '')}
				</td>
				<td>
					${zhengheOrder.expressName}
				</td>
				<td>
					${zhengheOrder.expressNo}
				</td>
				<td>
					${zhengheOrder.address}
				</td>
				<td>
					<fmt:formatDate value="${zhengheOrder.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="zhenghe:zhengheOrder:edit"><td>
    				<a href="${ctx}/zhenghe/zhengheOrderItem/list?orderId=${zhengheOrder.id}">详细</a>
    				<a href="${ctx}/zhenghe/zhengheOrder/form?id=${zhengheOrder.id}">发货</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>