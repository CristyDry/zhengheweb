package com.fullteem.modules.zhenghe.api.entity.request;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value="移动分组",description="原来的分组的id、患者的id和新的分组的id")
public class RequestMoveGroup {
	
	@ApiModelProperty(value="原来的分组的id",required=true)
	private String oldGroupId;
	@ApiModelProperty(value="患者的id",required=true)
	private String patientId;
	@ApiModelProperty(value="新的分组的id",required=true)
	private String newGroupId;
	public String getOldGroupId() {
		return oldGroupId;
	}
	public void setOldGroupId(String oldGroupId) {
		this.oldGroupId = oldGroupId;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getNewGroupId() {
		return newGroupId;
	}
	public void setNewGroupId(String newGroupId) {
		this.newGroupId = newGroupId;
	}
	
	

}
