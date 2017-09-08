package com.fullteem.modules.zhenghe.api.entity.request;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "意见反馈",description = "类型:String")
public class RequestIdea {
	@ApiModelProperty(value = "用户id",required = true)
	private String userId;
	@ApiModelProperty(value = "反馈内容",required = true)
	private String content;
	@ApiModelProperty(value = "手机号码",required = false)
	private String phone;
	@ApiModelProperty(value = "用户类型",required = true)
	private String userType;
	
	
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	
	
}
