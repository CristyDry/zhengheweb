<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>医院管理</title>
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
		
		function provinceChange(value){
			$.ajax( {
				url : "/zhenghe/api/addressApiController/ajaxCityList",
				data : "pid="+value,
				type : "post",
				dataType : "json",
				success : function(data) {
					var str = "";
					var obj = data.resultData;
					for(var i=0;i<obj.length;i++){
						str+="<option value='"+obj[i].id+"'>"+obj[i].cityName+"</option>";
					}
					$("#s2id_cityId >a >.select2-chosen").text("");
					$("#s2id_districtId >a >.select2-chosen").text("");
					$("#cityId").html(str);
				},error : function(XMLHttpRequest, textStatus, errorThrown) {  //#3这个error函数调试时非常有用，如果解析不正确，将会弹出错误框
					alert(XMLHttpRequest.status);
		            alert(textStatus);
		        }
			});
		}
		
		function cityChange(value){
			$.ajax( {
				url : "/zhenghe/api/addressApiController/ajaxDistrictList",
				data : "cid="+value,
				type : "post",
				dataType : "json",
				success : function(data) {
					var str = "";
					var obj = data.resultData;
					for(var i=0;i<obj.length;i++){
						str+="<option value='"+obj[i].id+"'>"+obj[i].districtName+"</option>";
					}
					$("#s2id_districtId >a >.select2-chosen").text("");
					$("#districtId").html(str);
				},error : function(XMLHttpRequest, textStatus, errorThrown) {  //#3这个error函数调试时非常有用，如果解析不正确，将会弹出错误框
					alert(XMLHttpRequest.status);
		            alert(textStatus);
		        }
			});
		}
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/zhenghe/zhengheHospital/">医院列表</a></li>
		<li class="active"><a href="${ctx}/zhenghe/zhengheHospital/form?id=${zhengheHospital.id}">医院<shiro:hasPermission name="zhenghe:zhengheHospital:edit">${not empty zhengheHospital.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="zhenghe:zhengheHospital:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="zhengheHospital" action="${ctx}/zhenghe/zhengheHospital/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">医院名：</label>
			<div class="controls">
				<form:input path="hospitalName" htmlEscape="false" maxlength="32" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">省份：</label>
			<div class="controls">
				<form:select path="provincialId" class="input-xlarge " onchange="provinceChange(this.value)">
					<%-- <form:option value="" label=""/> --%>
					<form:options items="${provinceMap}" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">城市：</label>
			<div class="controls">
				<form:select path="cityId" class="input-xlarge " onchange="cityChange(this.value)">
					<form:option value="" label=""/>
					<form:options items="${cityMap}" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">区县：</label>
			<div class="controls">
				<form:select path="districtId" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${districtMap}" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">具体地址：</label>
			<div class="controls">
				<form:textarea path="address" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remark" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="zhenghe:zhengheHospital:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>