package com.fullteem.modules.zhenghe.api.entity.request;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "体重",description = "类型:String")
public class RequestWeight {
	@ApiModelProperty(value = "患者id",required = true)
	private String patientId;
	@ApiModelProperty(value = "身高",required = true)
	private String stature;
	@ApiModelProperty(value = "体重",required = true)
	private String weight;
	@ApiModelProperty(value = "体重记录id",required = false)
	private String wId;
	
	public String getwId() {
		return wId;
	}
	public void setwId(String wId) {
		this.wId = wId;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getStature() {
		return stature;
	}
	public void setStature(String stature) {
		this.stature = stature;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}		
	
	
		
	
	
	
	
}
