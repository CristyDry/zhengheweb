package com.fullteem.modules.zhenghe.api.entity.request;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value="收藏",description="传入用户id,用户类型(患者||医生)和要收藏的东西id,东西的类型(文章||药品)")
public class RequestCollect {
	
	@ApiModelProperty(value="用户id",required=true)
	private String userId;
	@ApiModelProperty(value="用户类型",required=true)
	private String userType;
	@ApiModelProperty(value="要收藏的东西id",required=true)
	private String thingId;
	@ApiModelProperty(value="要收藏的东西的类型",required=true)
	private String thingType;
	@ApiModelProperty(value="收藏的状态")
	private String status;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
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
	public String getThingId() {
		return thingId;
	}
	public void setThingId(String thingId) {
		this.thingId = thingId;
	}
	public String getThingType() {
		return thingType;
	}
	public void setThingType(String thingType) {
		this.thingType = thingType;
	}
	
	
	

}
