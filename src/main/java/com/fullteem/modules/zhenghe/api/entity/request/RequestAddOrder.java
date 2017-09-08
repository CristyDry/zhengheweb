package com.fullteem.modules.zhenghe.api.entity.request;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "生成订单",description = "类型:String")
public class RequestAddOrder {
	@ApiModelProperty(value = "患者id",required = true)
	private String patientId;
	@ApiModelProperty(value = "地址id",required = true)
	private String addressId;
	@ApiModelProperty(value = "购物车项id",required = true)
	private String cartIds;
	
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getAddressId() {
		return addressId;
	}
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	public String getCartIds() {
		return cartIds;
	}
	public void setCartIds(String cartIds) {
		this.cartIds = cartIds;
	}
	
	
}
