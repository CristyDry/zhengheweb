<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>处方管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
            $("#btnCancel").click(function () {
                $("#inputForm").attr("action","${ctx}/zhenghe/zhengheRx/cancel");
                $("#inputForm").submit();
            });
            $("#btnReceive").click(function () {
				if($("#employeeNumber").val() == ''){
					alert("请输入工号");
					return;
				}

                $("#inputForm").attr("action","${ctx}/zhenghe/zhengheRx/receive");
                $("#inputForm").submit();
            });
		});
		function addRow(list, idx, tpl, row){
			$(list).append(Mustache.render(tpl, {
				idx: idx, delBtn: true, row: row
			}));
			$(list+idx).find("select").each(function(){
				$(this).val($(this).attr("data-value"));
			});
			$(list+idx).find("input[type='checkbox'], input[type='radio']").each(function(){
				var ss = $(this).attr("data-value").split(',');
				for (var i=0; i<ss.length; i++){
					if($(this).val() == ss[i]){
						$(this).attr("checked","checked");
					}
				}
			});
		}
		function delRow(obj, prefix){
			var id = $(prefix+"_id");
			var delFlag = $(prefix+"_delFlag");
			if (id.val() == ""){
				$(obj).parent().parent().remove();
			}else if(delFlag.val() == "0"){
				delFlag.val("1");
				$(obj).html("&divide;").attr("title", "撤销删除");
				$(obj).parent().parent().addClass("error");
			}else if(delFlag.val() == "1"){
				delFlag.val("0");
				$(obj).html("&times;").attr("title", "删除");
				$(obj).parent().parent().removeClass("error");
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/zhenghe/zhengheRx/">处方列表</a></li>
		<li class="active"><a href="${ctx}/zhenghe/zhengheRx/form?id=${zhengheRx.id}">处方<shiro:hasPermission name="zhenghe:zhengheRx:edit">${not empty zhengheRx.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="zhenghe:zhengheRx:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="zhengheRx" action="${ctx}/zhenghe/zhengheRx/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="breadcrumb form-search">
			<ul class="ul-form">
				<li><label>处方名称：</label>
					<form:input path="rxName" htmlEscape="false" readonly="true" maxlength="120" />
				</li>
				<li><label>处方编号：</label>
					<form:input path="rxNo" htmlEscape="false" readonly="true" maxlength="64"/>
				</li>
				<li><label>付款类型：</label>
					<form:input path="payTypeName" htmlEscape="false" readonly="true" maxlength="64"/>
				</li>
				<li><label>患者名称：</label>
					<form:input path="patientName" htmlEscape="false" readonly="true" maxlength="64"/>
				</li>
				<li><label>患者性别：</label>
					<form:input path="patientGender" htmlEscape="false" readonly="true" maxlength="64"/>
				</li>
				<li><label>患者年龄：</label>
					<form:input path="patientAge" htmlEscape="false" readonly="true" maxlength="64"/>
				</li>
				<li><label>患者地址：</label>
					<form:input path="patientAddress" htmlEscape="false" readonly="true" maxlength="64"/>
				</li>
				<li><label>患者电话：</label>
					<form:input path="patientPhone" htmlEscape="false" readonly="true" maxlength="64"/>
				</li>
				<li><label>科别：</label>
					<form:input path="category" htmlEscape="false" readonly="true" maxlength="64"/>
				</li>
				<li><label>临床诊断：</label>
					<form:input path="clinicalDiagnosis" htmlEscape="false" readonly="true" maxlength="64"/>
				</li>
				<li><label>医师：</label>
					<form:input path="doctor" htmlEscape="false" readonly="true" maxlength="64"/>
				</li>
				<li><label>审核药师：</label>
					<form:input path="approvalDoctor" htmlEscape="false" readonly="true" maxlength="64"/>
				</li>
				<li><label>核对药师：</label>
					<form:input path="checkDoctor" htmlEscape="false" readonly="true" maxlength="64"/>
				</li>
				<li><label>药品金额：</label>
					<form:input path="totalAmount" htmlEscape="false" readonly="true" maxlength="64"/>
				</li>
				<li><label>状态：</label>
					<form:input path="statusName" htmlEscape="false" readonly="true" maxlength="64"/>
				</li>
				<li><label>所属药店：</label>
					<form:input path="departmentName" htmlEscape="false" readonly="true" maxlength="64"/>
				</li>
				<li><label>开具时间：</label>
					<input name="rxDate" type="text" readonly="readonly" maxlength="20"
						   value="<fmt:formatDate value="${zhengheRx.rxDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
						   />
				</li>
				<li><label>处理时间：</label>
					<input name="processDate" type="text" readonly="readonly" maxlength="20"
						   value="<fmt:formatDate value="${zhengheRx.processDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
						   />
				</li>
				<li><label>处理人：</label>
					<form:input path="processUser" htmlEscape="false" readonly="true" maxlength="64"/>
				</li>
				<li><label>备注：</label>
					<form:input path="remark" htmlEscape="false" readonly="true" maxlength="64"/>
				</li>
			</ul>
		</div>

			<div class="control-group">
				<label class="control-label">Rp：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<%--<th>所属药店</th>--%>
									<%--<th>商品编号</th>--%>
                                    <th>药名</th>
                                    <th>价格</th>
                                    <th>数量</th>
                                    <th>规格</th>
                                    <th>使用说明</th>
                                    <th>备注</th>
                                    <%--<th>删除标记</th>
                                    <th>修改者</th>
                                    <th>创建者</th>--%>
								<shiro:hasPermission name="zhenghe:zhengheRx:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="zhengheRxDetailList">
						</tbody>
						<shiro:hasPermission name="zhenghe:zhengheRx:edit"><tfoot>
							<tr><td colspan="13"><a href="javascript:" onclick="addRow('#zhengheRxDetailList', zhengheRxDetailRowIdx, zhengheRxDetailTpl);zhengheRxDetailRowIdx = zhengheRxDetailRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="zhengheRxDetailTpl">//<!--
						<tr id="zhengheRxDetailList{{idx}}">
							<td class="hide">
								<input id="zhengheRxDetailList{{idx}}_id" name="zhengheRxDetailList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="zhengheRxDetailList{{idx}}_delFlag" name="zhengheRxDetailList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<%--<td>
								<sys:treeselect id="zhengheRxDetailList{{idx}}_departmentId" name="zhengheRxDetailList[{{idx}}].departmentId" value="{{row.departmentId}}" labelName="zhengheRxDetailList{{idx}}." labelValue="{{row.}}"
									title="部门" url="/sys/office/treeData?type=2" cssClass="" allowClear="true" notAllowSelectParent="true"/>
							</td>--%>
							<%--<td>
								<input id="zhengheRxDetailList{{idx}}_productId" name="zhengheRxDetailList[{{idx}}].productId" type="text" value="{{row.productId}}" maxlength="64" class="input-small "/>
							</td>--%>
							<td>
								<input id="zhengheRxDetailList{{idx}}_productName" name="zhengheRxDetailList[{{idx}}].productName" type="text" value="{{row.productName}}" maxlength="64" class="input-small "/>
							</td>
							<td>
								<input id="zhengheRxDetailList{{idx}}_price" name="zhengheRxDetailList[{{idx}}].price" type="text" value="{{row.price}}" class="input-small "/>
							</td>
							<td>
								<input id="zhengheRxDetailList{{idx}}_num" name="zhengheRxDetailList[{{idx}}].num" type="text" value="{{row.num}}" maxlength="11" class="input-small "/>
							</td>
							<td>
								<input id="zhengheRxDetailList{{idx}}_standard" name="zhengheRxDetailList[{{idx}}].standard" type="text" value="{{row.standard}}" maxlength="64" class="input-small "/>
							</td>
							<td>
								<input id="zhengheRxDetailList{{idx}}_sig" name="zhengheRxDetailList[{{idx}}].sig" type="text" value="{{row.sig}}" maxlength="64" class="input-small "/>
							</td>
							<td>
								<input id="zhengheRxDetailList{{idx}}_remark" name="zhengheRxDetailList[{{idx}}].remark" type="text" value="{{row.remark}}" maxlength="255" class="input-small "/>
							</td>
							<%--<td>
								<input id="zhengheRxDetailList{{idx}}_deleteMark" name="zhengheRxDetailList[{{idx}}].deleteMark" type="text" value="{{row.deleteMark}}" maxlength="32" class="input-small "/>
							</td>
							<td>
								<input id="zhengheRxDetailList{{idx}}_mender" name="zhengheRxDetailList[{{idx}}].mender" type="text" value="{{row.mender}}" maxlength="32" class="input-small "/>
							</td>
							<td>
								<input id="zhengheRxDetailList{{idx}}_creator" name="zhengheRxDetailList[{{idx}}].creator" type="text" value="{{row.creator}}" maxlength="32" class="input-small "/>
							</td>--%>
							<shiro:hasPermission name="zhenghe:zhengheRx:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#zhengheRxDetailList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var zhengheRxDetailRowIdx = 0, zhengheRxDetailTpl = $("#zhengheRxDetailTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(zhengheRx.zhengheRxDetailList)};
							for (var i=0; i<data.length; i++){
								addRow('#zhengheRxDetailList', zhengheRxDetailRowIdx, zhengheRxDetailTpl, data[i]);
								zhengheRxDetailRowIdx = zhengheRxDetailRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
		<div class="form-actions">
			<shiro:hasPermission name="zhenghe:zhengheRx:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<shiro:hasPermission name="zhenghe:zhengheRx:cancel"><input id="btnCancel" class="btn btn-primary" type="button" value="取 消 处 方"/>&nbsp;</shiro:hasPermission>
			<shiro:hasPermission name="zhenghe:zhengheRx:receive">工号：<input id="employeeNumber" name="employeeNumber" class="text-field" type="text" value=""/><input id="btnReceive" class="btn btn-primary" type="button" value="接 收 处 方"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>