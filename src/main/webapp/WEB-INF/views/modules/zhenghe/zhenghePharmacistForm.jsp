<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>药师管理</title>
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
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/zhenghe/zhenghePharmacist/">药师列表</a></li>
		<li class="active"><a href="${ctx}/zhenghe/zhenghePharmacist/form?id=${zhenghePharmacist.id}">药师<shiro:hasPermission name="zhenghe:zhenghePharmacist:edit">${not empty zhenghePharmacist.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="zhenghe:zhenghePharmacist:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="zhenghePharmacist" action="${ctx}/zhenghe/zhenghePharmacist/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">电话：</label>
			<div class="controls">
				<form:input path="tel" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">头像：</label>
			<div class="controls">
				<form:hidden id="icon" path="icon" htmlEscape="false" maxlength="128" class="input-xlarge"/>
				<sys:ckfinder input="icon" type="files" uploadPath="/zhenghe/zhenghePharmacist" selectMultiple="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">资质：</label>
			<div class="controls">
				<form:hidden id="img" path="img" htmlEscape="false" maxlength="128" class="input-xlarge"/>
				<sys:ckfinder input="img" type="files" uploadPath="/zhenghe/zhenghePharmacist" selectMultiple="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">描述：</label>
			<div class="controls">
				<form:textarea path="msg" htmlEscape="false" rows="4" maxlength="128" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="zhenghe:zhenghePharmacist:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>