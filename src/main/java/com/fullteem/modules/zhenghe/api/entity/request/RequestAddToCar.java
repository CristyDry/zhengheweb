package com.fullteem.modules.zhenghe.api.entity.request;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "添加进购物车",description = "类型:String")
public class RequestAddToCar {
	@ApiModelProperty(value = "患者id",required = true)
	private String patientId;
	@ApiModelProperty(value = "商品id",required = true)
	private String productId;
	@ApiModelProperty(value = "购买数量",required = true)
	private String count;
	@ApiModelProperty(value = "推荐医生",required = false)
	private String doctorId;
	
	
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
	
	
	
	
}
