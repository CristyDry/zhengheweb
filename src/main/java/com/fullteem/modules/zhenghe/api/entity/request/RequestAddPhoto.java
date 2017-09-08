package com.fullteem.modules.zhenghe.api.entity.request;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value="addPhoto",description="上传图片接口")
public class RequestAddPhoto {
	@ApiModelProperty(value = "病历/病程 的id，必传",required = true)
	private String id;
	@ApiModelProperty(value = "1->病历，2->病程",required = true)
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
