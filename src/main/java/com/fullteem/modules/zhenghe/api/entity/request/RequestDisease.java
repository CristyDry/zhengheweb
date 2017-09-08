package com.fullteem.modules.zhenghe.api.entity.request;

import java.util.Date;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "病程",description = "类型:String")
public class RequestDisease {
	@ApiModelProperty(value = "病历id",required = true)
	private String mhId;			// 病历id
	@ApiModelProperty(value = "日期",required = true)
	private String cdDate;			// 日期
	@ApiModelProperty(value = "事件",required = true)
	private String incident;		// 事件
	@ApiModelProperty(value = "病程id",required = false)
	private String diseaseId;
	@ApiModelProperty(value = "备注",required = true)
	private String remark;
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getDiseaseId() {
		return diseaseId;
	}
	public void setDiseaseId(String diseaseId) {
		this.diseaseId = diseaseId;
	}
	public String getMhId() {
		return mhId;
	}
	public void setMhId(String mhId) {
		this.mhId = mhId;
	}
	public String getCdDate() {
		return cdDate;
	}
	public void setCdDate(String cdDate) {
		this.cdDate = cdDate;
	}
	public String getIncident() {
		return incident;
	}
	public void setIncident(String incident) {
		this.incident = incident;
	}
	
}
