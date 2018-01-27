<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>处方管理</title>
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
		<li class="active"><a href="${ctx}/zhenghe/zhengheRx/">处方列表</a></li>
		<shiro:hasPermission name="zhenghe:zhengheRx:edit"><li><a href="${ctx}/zhenghe/zhengheRx/form">处方添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="zhengheRx" action="${ctx}/zhenghe/zhengheRx/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>处方编号：</label>
				<form:input path="rxNo" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<%--<li><label>付款类型：</label>
				<form:input path="payType" htmlEscape="false" maxlength="2" class="input-medium"/>
			</li>--%>
			<li><label>患者名称：</label>
				<form:input path="patientName" htmlEscape="false" maxlength="2" class="input-medium"/>
			</li>
			<li><label>患者电话：</label>
				<form:input path="patientPhone" htmlEscape="false" maxlength="21" class="input-medium"/>
			</li>
			<li><label>医师：</label>
				<form:input path="doctor" htmlEscape="false" maxlength="10" class="input-medium"/>
			</li>
			<li><label>状态：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('rx_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>所属药店：</label>
				<sys:treeselect id="departmentId" name="departmentId" value="${zhengheRx.departmentId}" labelName="" labelValue="${zhengheRx.departmentName}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>开具时间：</label>
				<input name="rxDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${zhengheRx.rxDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>处方名称</th>
				<th>处方编号</th>
				<th>电子处方</th>
				<th>付款类型</th>
				<%--<th>患者编号</th>--%>
				<th>患者名称</th>
				<th>患者性别</th>
				<th>患者年龄</th>
				<th>患者地址</th>
				<th>患者电话</th>
				<th>病例号</th>
				<th>科别</th>
				<th>临床诊断</th>
				<th>医师</th>
				<th>审核药师</th>
				<th>调配药师/士</th>
				<th>核对、发药药师</th>
				<th>药品金额</th>
				<th>状态</th>
				<th>所属药店</th>
				<th>接收工号</th>
				<th>开具时间</th>
				<th>处理时间</th>
				<th>处理人</th>
				<th>修改时间</th>
				<shiro:hasPermission name="zhenghe:zhengheRx:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="zhengheRx">
			<tr>
				<td><a href="${ctx}/zhenghe/zhengheRx/form?id=${zhengheRx.id}">
					${zhengheRx.rxName}
				</a></td>
				<td>
					${zhengheRx.rxNo}
				</td>
				<td><a href="#" onclick="window.open('${ctx}/zhenghe/zhengheRx/print?id=${zhengheRx.id}','','width=640,height=720')">
						查看
				</a></td>
				<td>
					${zhengheRx.payTypeName}
				</td>
				<%--<td>
					${zhengheRx.patientId}
				</td>--%>
				<td>
					${zhengheRx.patientName}
				</td>
				<td>
					${zhengheRx.patientGender}
				</td>
				<td>
					${zhengheRx.patientAge}
				</td>
				<td>
					${zhengheRx.patientAddress}
				</td>
				<td>
					${zhengheRx.patientPhone}
				</td>
				<td>
					${zhengheRx.caseNo}
				</td>
				<td>
					${zhengheRx.category}
				</td>
				<td>
					${zhengheRx.clinicalDiagnosis}
				</td>
				<td>
					${zhengheRx.doctor}
				</td>
				<td>
					${zhengheRx.approvalDoctor}
				</td>
				<td>
					${zhengheRx.deployDoctor}
				</td>
				<td>
					${zhengheRx.checkDoctor}
				</td>
				<td>
					${zhengheRx.totalAmount}
				</td>
				<td>
					${zhengheRx.statusName}
				</td>
				<td>
					${zhengheRx.departmentName}
				</td>
				<td>
						${zhengheRx.employeeNumber}
				</td>
				<td>
					<fmt:formatDate value="${zhengheRx.rxDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${zhengheRx.processDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${zhengheRx.processUser}
				</td>
				<td>
					<fmt:formatDate value="${zhengheRx.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="zhenghe:zhengheRx:edit"><td>
    				<a href="${ctx}/zhenghe/zhengheRx/form?id=${zhengheRx.id}">修改</a>
					<a href="${ctx}/zhenghe/zhengheRx/delete?id=${zhengheRx.id}" onclick="return confirmx('确认要删除该处方吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>