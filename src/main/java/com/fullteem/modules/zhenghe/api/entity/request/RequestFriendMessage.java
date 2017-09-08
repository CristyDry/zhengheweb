package com.fullteem.modules.zhenghe.api.entity.request;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value="验证消息",description="验证消息")
public class RequestFriendMessage {

	@ApiModelProperty(value="医生Id",required=true)
	private String doctorId;

	@ApiModelProperty(value="验证消息",required=false)
	private String verifyContent;
	
	@ApiModelProperty(value="患者Id",required=true)
	private String patientId;
	
	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	public String getVerifyContent() {
		return verifyContent;
	}

	public void setVerifyContent(String verifyContent) {
		this.verifyContent = verifyContent;
	}
	
	
}
