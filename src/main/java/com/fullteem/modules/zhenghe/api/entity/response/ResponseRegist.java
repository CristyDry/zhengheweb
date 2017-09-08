package com.fullteem.modules.zhenghe.api.entity.response;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;



@ApiModel(value="注册用户返回")
public class ResponseRegist {
	@ApiModelProperty(value = "序列id")
	private String id;
	@ApiModelProperty(value = "手机号")
	private String mobile;
	@ApiModelProperty(value = "用户类型(1，教师，2，学生)")
	private String userType;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	
	
}
