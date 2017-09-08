package com.fullteem.modules.zhenghe.api.entity.request;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "登录",description = "登录")
public class RequestLoginUser {
	@ApiModelProperty(value = "手机号",required = true)
	private String phone;
	@ApiModelProperty(value = "密码",required = true)
	private String password;
	@ApiModelProperty(value = "用户类型",required = true)
	private String userType;
	@ApiModelProperty(value = "tokenName",required = true)
	private String tokenName;
	
	
	public String getTokenName() {
		return tokenName;
	}
	public void setTokenName(String tokenName) {
		this.tokenName = tokenName;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
