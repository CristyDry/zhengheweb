package com.fullteem.modules.zhenghe.api.entity.request;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;


@ApiModel(value="user",description="用户")
public class RequestUser {
	
	@ApiModelProperty(value="userId",required=true)
	private String userId;
	@ApiModelProperty(value="userType",required=true)
	private String userType;
	@ApiModelProperty(value="rongType",required=false)
	private String rongType;
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
	public String getRongType() {
		return rongType;
	}
	public void setRongType(String rongType) {
		this.rongType = rongType;
	}
}
