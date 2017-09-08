package com.fullteem.modules.zhenghe.api.entity.request;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value="系统消息",description="接受用户id和类型")
public class RequestMessage {
	
	@ApiModelProperty(value="用户id",required=true)
	private String id;
	
	@ApiModelProperty(value="用户类型,1->患者2->医生",required=true)
	private String type;

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
	
	
	
	
}
