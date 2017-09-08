package com.fullteem.modules.zhenghe.api.entity.request;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value="删除患者",description="分组的id、患者的id")
public class RequestDeletePatient {
	
	@ApiModelProperty(value="分组的id",required=true)
	private String groupId;
	@ApiModelProperty(value="患者的id",required=true)
	private String patientId;
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	
	
	
	
}
