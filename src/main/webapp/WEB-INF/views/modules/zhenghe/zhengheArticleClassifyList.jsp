<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>知识频道管理</title>
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
		<li class="active"><a href="${ctx}/zhenghe/zhengheArticleClassify/">知识频道列表</a></li>
		<shiro:hasPermission name="zhenghe:zhengheArticleClassify:edit"><li><a href="${ctx}/zhenghe/zhengheArticleClassify/form">知识频道添加</a></li></shiro:hasPermission>
	</ul>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>分类名</th>
				<th>描述</th>
				<th>排序</th>
				<th>状态</th>
				<shiro:hasPermission name="zhenghe:zhengheArticleClassify:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="zhengheArticleClassify">
			<tr>
				<td><a href="${ctx}/zhenghe/zhengheArticleClassify/form?id=${zhengheArticleClassify.id}">
					${zhengheArticleClassify.classifyName}
				</a></td>
				<td>
					${zhengheArticleClassify.description}
				</td>
				<td>
					${zhengheArticleClassify.rank}
				</td>
				<td>
					${fns:getDictLabel(zhengheArticleClassify.status, 'article_classify_status', '')}
				</td>
				<shiro:hasPermission name="zhenghe:zhengheArticleClassify:edit"><td>
    				<a href="${ctx}/zhenghe/zhengheArticleClassify/form?id=${zhengheArticleClassify.id}">修改</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>