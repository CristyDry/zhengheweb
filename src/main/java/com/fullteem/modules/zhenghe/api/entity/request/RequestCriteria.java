package com.fullteem.modules.zhenghe.api.entity.request;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value="搜索医生",description="根据条件找医生")
public class RequestCriteria {
	
	@ApiModelProperty(value="关键字",required=false)
	private String keyword;
	@ApiModelProperty(value="省Id",required=false)
	private String provincialId;
	@ApiModelProperty(value="市Id",required=false)
	private String cityId;
	@ApiModelProperty(value="一级科室id",required=false)
	private String firstDepartmentsId;
	@ApiModelProperty(value="二级科室id",required=false)
	private String secondDepartmentsId;
	
	
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getProvincialId() {
		return provincialId;
	}
	public void setProvincialId(String provincialId) {
		this.provincialId = provincialId;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getFirstDepartmentsId() {
		return firstDepartmentsId;
	}
	public void setFirstDepartmentsId(String firstDepartmentsId) {
		this.firstDepartmentsId = firstDepartmentsId;
	}
	public String getSecondDepartmentsId() {
		return secondDepartmentsId;
	}
	public void setSecondDepartmentsId(String secondDepartmentsId) {
		this.secondDepartmentsId = secondDepartmentsId;
	}
	
	
	

}
