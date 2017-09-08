package com.fullteem.modules.zhenghe.api.entity.response;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;


@ApiModel(value = "省份集合")
public class ResponseProvince  {

	@ApiModelProperty(notes = "省份缩写")
	private String wm;

	public ResponseProvince() {

	}
	
	public ResponseProvince(String wm) {
		super();
		this.wm = wm;
	}

	public String getWm() {
		return wm;
	}

	public void setWm(String wm) {
		this.wm = wm;
	}

	@Override
	public String toString() {
		return "ResponseProvinceList [wm=" + wm + "]";
	}

	
}
