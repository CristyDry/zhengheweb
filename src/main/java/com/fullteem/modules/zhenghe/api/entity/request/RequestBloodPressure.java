package com.fullteem.modules.zhenghe.api.entity.request;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "血压",description = "类型:String")
public class RequestBloodPressure {
	@ApiModelProperty(value = "病人id",required = true)
	private String patientId;
	@ApiModelProperty(value = "收缩压",required = true)
	private String sbp;
	@ApiModelProperty(value = "舒张压",required = true)
	private String dbp;
	@ApiModelProperty(value = "血压记录id",required = false)
	private String bpId;
	
	public String getBpId() {
		return bpId;
	}
	public void setBpId(String bpId) {
		this.bpId = bpId;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getSbp() {
		return sbp;
	}
	public void setSbp(String sbp) {
		this.sbp = sbp;
	}
	public String getDbp() {
		return dbp;
	}
	public void setDbp(String dbp) {
		this.dbp = dbp;
	}
	
	
	
}
