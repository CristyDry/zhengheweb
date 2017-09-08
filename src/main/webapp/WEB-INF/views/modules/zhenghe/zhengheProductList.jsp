<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>药品管理</title>
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
		<li class="active"><a href="${ctx}/zhenghe/zhengheProduct/">药品列表</a></li>
		<shiro:hasPermission name="zhenghe:zhengheProduct:edit"><li><a href="${ctx}/zhenghe/zhengheProduct/form">药品添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="zhengheProduct" action="${ctx}/zhenghe/zhengheProduct/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>分类：</label>
				<form:select path="pclassifyId" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${cList}"/>
				</form:select>
			</li>
			<li><label>商品名称：</label>
				<form:input path="productName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>类型：</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('product_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>功能主治：</label>
				<form:input path="pfunction" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>状态：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('product_classify_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>分类</th>
				<th>商品名称</th>
				<th>价格</th>
				<th>类型</th>
				<th>规格</th>
				<th>单位</th>
				<th>功能主治</th>
				<th>说明</th>
				<th>销量</th>
				<th>库存</th>
				<th>状态</th>
				<shiro:hasPermission name="zhenghe:zhengheProduct:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="zhengheProduct">
			<tr>
				<td>
					${zhengheProduct.classifyName}
				</td>
				<td><a href="${ctx}/zhenghe/zhengheProduct/form?id=${zhengheProduct.id}">
					${zhengheProduct.productName}
				</a></td>
				<td>
					${zhengheProduct.price}
				</td>
				<td>
					${fns:getDictLabel(zhengheProduct.type, 'product_type', '')}
				</td>
				<td>
					${zhengheProduct.standard}
				</td>
				<td>
					${zhengheProduct.productUnit}
				</td>
				<td>
					${zhengheProduct.pfunction}
				</td>
				<td>
					${zhengheProduct.explains}
				</td>
				<td>
					${zhengheProduct.salesNum}
				</td>
				<td>
					${zhengheProduct.repertoryNum}
				</td>
				<td>
					${fns:getDictLabel(zhengheProduct.status, 'product_classify_status', '')}
				</td>
				<shiro:hasPermission name="zhenghe:zhengheProduct:edit"><td>
    				<a href="${ctx}/zhenghe/zhengheProduct/form?id=${zhengheProduct.id}">修改</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>