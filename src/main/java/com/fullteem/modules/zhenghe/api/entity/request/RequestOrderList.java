package com.fullteem.modules.zhenghe.api.entity.request;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "订单列表",description = "类型:String")
public class RequestOrderList {
	@ApiModelProperty(value = "患者id",required = true)
	private String patientId;
	@ApiModelProperty(value = "订单状态：1待支付2支付完成待收货3完成4取消",required = false)
	private String status;
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
