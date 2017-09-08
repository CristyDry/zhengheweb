package com.fullteem.modules.zhenghe.api.entity.request;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "商品详情",description = "传入用户id,用户类型(患者||医生)和商品id")
public class RequestProduct {
	
	@ApiModelProperty(value="用户id",required=false)
	private String userId;
	@ApiModelProperty(value="用户类型",required=false)
	private String userType;
	@ApiModelProperty(value = "商品id",required = true)
	private String productId;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
}
