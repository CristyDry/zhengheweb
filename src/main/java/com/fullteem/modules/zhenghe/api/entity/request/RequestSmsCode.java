package com.fullteem.modules.zhenghe.api.entity.request;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "下发短信",description = "下发短信")
public class RequestSmsCode {
	@ApiModelProperty(value = "手机号",required = true)
	private String phone;
	@ApiModelProperty(value = "类型(1注册，2忘记密码)",required = true)
	private String type;
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
