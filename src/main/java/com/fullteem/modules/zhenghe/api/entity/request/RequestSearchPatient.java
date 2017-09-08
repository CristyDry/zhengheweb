package com.fullteem.modules.zhenghe.api.entity.request;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "搜索患者",description = "传入医生id和搜索关键字")
public class RequestSearchPatient {

	@ApiModelProperty(value="医生id",required=true)
	private String doctorId;
	@ApiModelProperty(value="关键字",required=false)
	private String key;
	
	public String getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
	
}
