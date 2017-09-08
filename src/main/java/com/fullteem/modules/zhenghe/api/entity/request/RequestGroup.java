package com.fullteem.modules.zhenghe.api.entity.request;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value="分组",description="患者的分组")
public class RequestGroup {

	@ApiModelProperty(value="组Id",required=false)
	private String id;
	@ApiModelProperty(value="组名",required=true)
	private String groupName;
	@ApiModelProperty(value="医生id",required=true)
	private String doctorId;
	
	public String getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	
}
