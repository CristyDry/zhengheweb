package com.fullteem.modules.zhenghe.api.entity.request;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "心率",description = "类型:String")
public class RequestHeartRate {
	@ApiModelProperty(value = "病人id",required = true)
	private String patientId;
	@ApiModelProperty(value = "心率",required = true)
	private String heartRate;		
	@ApiModelProperty(value = "场景",required = true)
	private String scene;
	@ApiModelProperty(value = "心率记录id",required = false)
	private String hrId;
	
	public String getHrId() {
		return hrId;
	}
	public void setHrId(String hrId) {
		this.hrId = hrId;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getHeartRate() {
		return heartRate;
	}
	public void setHeartRate(String heartRate) {
		this.heartRate = heartRate;
	}
	public String getScene() {
		return scene;
	}
	public void setScene(String scene) {
		this.scene = scene;
	}			
	
	
	
	
}
