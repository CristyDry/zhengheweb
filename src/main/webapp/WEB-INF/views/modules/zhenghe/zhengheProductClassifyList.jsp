<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>药品分类管理</title>
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
		.listImg{
			width:150px;
			height:100px;
		}
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/zhenghe/zhengheProductClassify/">药品分类列表</a></li>
		<shiro:hasPermission name="zhenghe:zhengheProductClassify:edit"><li><a href="${ctx}/zhenghe/zhengheProductClassify/form">药品分类添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="zhengheProductClassify" action="${ctx}/zhenghe/zhengheProductClassify/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名称：</label>
				<form:input path="classifyName" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>描述：</label>
				<form:input path="description" htmlEscape="false" maxlength="255" class="input-medium"/>
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
				<th>名称</th>
				<th>图标</th>
				<th>描述</th>
				<th>排序</th>
				<th>状态</th>
				<th>备注</th>
				<shiro:hasPermission name="zhenghe:zhengheProductClassify:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="zhengheProductClassify">
			<tr>
				<td><a href="${ctx}/zhenghe/zhengheProductClassify/form?id=${zhengheProductClassify.id}">
					${zhengheProductClassify.classifyName}
				</a></td>
				<td>
					<img class="listImg" src="${zhengheProductClassify.avatar}"/>
				</td>
				<td>
					${zhengheProductClassify.description}
				</td>
				<td>
					${zhengheProductClassify.rank}
				</td>
				<td>
					${fns:getDictLabel(zhengheProductClassify.status, 'product_classify_status', '')}
				</td>
				<td>
					${zhengheProductClassify.remark}
				</td>
				<shiro:hasPermission name="zhenghe:zhengheProductClassify:edit"><td>
    				<a href="${ctx}/zhenghe/zhengheProductClassify/form?id=${zhengheProductClassify.id}">修改</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>