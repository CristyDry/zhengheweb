package com.fullteem.modules.zhenghe.api.entity.request;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value="医生",description="医生")
public class RequestDoctor {

	@ApiModelProperty(value="医生Id",required=true)
	private String id;
	@ApiModelProperty(value="专业领域",required=false)
	private String professionalField;
	@ApiModelProperty(value="个人简介",required=false)
	private String intro;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProfessionalField() {
		return professionalField;
	}
	public void setProfessionalField(String professionalField) {
		this.professionalField = professionalField;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	

}
