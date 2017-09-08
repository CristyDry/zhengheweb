<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>知识文章管理</title>
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
		<li class="active"><a href="${ctx}/zhenghe/zhengheArticle/">知识文章列表</a></li>
		<shiro:hasPermission name="zhenghe:zhengheArticle:edit"><li><a href="${ctx}/zhenghe/zhengheArticle/form">知识文章添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="zhengheArticle" action="${ctx}/zhenghe/zhengheArticle/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>频道：</label>
					<form:select path="classifyId" class="input-medium">
						<form:option value="" label=""/>
						<form:options items="${classifyMap}" htmlEscape="false"/>
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
				<th>标题</th>
				<th>分类</th>
				<th>标题图片</th>
				<th>排序</th>
				<th>时间</th>
				<th>状态</th>
				<th>出处</th>
				<shiro:hasPermission name="zhenghe:zhengheArticle:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="zhengheArticle">
			<tr>
				<td><a href="${ctx}/zhenghe/zhengheArticle/form?id=${zhengheArticle.id}">
					${zhengheArticle.title}
				</a></td>
				<td>
					${zhengheArticle.classifyName }
				</td>
				<td>
					<img class="listImg" src="${zhengheArticle.avatar}"/>
				</td>
				<td>
					${zhengheArticle.rank}
				</td>
				<td>
					<fmt:formatDate value="${zhengheArticle.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getDictLabel(zhengheArticle.status, 'article_status', '')}
				</td>
				<td>
					${zhengheArticle.publish}
				</td>
				<shiro:hasPermission name="zhenghe:zhengheArticle:edit"><td>
    				<a href="${ctx}/zhenghe/zhengheArticle/form?id=${zhengheArticle.id}">修改</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>