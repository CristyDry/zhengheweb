package com.fullteem.modules.zhenghe.api.entity.request;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "收藏",description = "类型:String")
public class RequestGetCollectList {
	@ApiModelProperty(value = "用户id",required = true)
	private String id;
	@ApiModelProperty(value = "1文章2商品",required = true)
	private String type;
	@ApiModelProperty(value = "1患者2医生",required = true)
	private String user;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	
	
	
	
	
}
