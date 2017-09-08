<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>轮播图管理</title>
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
		<li class="active"><a href="${ctx}/zhenghe/zhengheCarousel/">轮播图列表</a></li>
		<shiro:hasPermission name="zhenghe:zhengheCarousel:edit"><li><a href="${ctx}/zhenghe/zhengheCarousel/form">轮播图添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="zhengheCarousel" action="${ctx}/zhenghe/zhengheCarousel/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>类型：</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('carousel_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>图片</th>
				<th>标题/描叙</th>
				<th>类型</th>
				<th>排序</th>
				<th>所属文章</th>
				<th>所属商品</th>
				<th>修改时间</th>
				<shiro:hasPermission name="zhenghe:zhengheCarousel:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="zhengheCarousel">
			<tr>
				<td><a href="${ctx}/zhenghe/zhengheCarousel/form?id=${zhengheCarousel.id}">
					<img class="listImg" src="${zhengheCarousel.avatar}"/>
				</a></td>
				<td>
					${zhengheCarousel.title}
				</td>
				<td>
					${fns:getDictLabel(zhengheCarousel.type, 'carousel_type', '')}
				</td>
				<td>
					${zhengheCarousel.rank}
				</td>
				<td>
					${zhengheCarousel.articleName}
				</td>
				<td>
					${zhengheCarousel.productName}
				</td>
				<td>
					<fmt:formatDate value="${zhengheCarousel.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="zhenghe:zhengheCarousel:edit"><td>
    				<a href="${ctx}/zhenghe/zhengheCarousel/form?id=${zhengheCarousel.id}">修改</a>
					<a href="${ctx}/zhenghe/zhengheCarousel/delete?id=${zhengheCarousel.id}" onclick="return confirmx('确认要删除该轮播图吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>