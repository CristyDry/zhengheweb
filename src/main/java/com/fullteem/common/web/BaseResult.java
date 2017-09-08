package com.fullteem.common.web;

import java.io.Serializable;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * 
 * @项目名称:fullteem_master
 * @文件名:BaseResult.java
 * @包名:com.fullteem.projectname.api.entity
 * @时间:2015年7月20日下午5:14:18
 * @描述:
 * @作者:Yuan.pan
 * @Copyright (c) 2015, www.fullteem.com All Rights Reserved.
 *
 */
@ApiModel(value = "BaseResult", description = "返回集合")
public class BaseResult implements Serializable {

	private static final long serialVersionUID = -7978635757653362784L;

	// 返回码，0表示成功，非0表示失败
	@ApiModelProperty(notes = "返回码，0表示成功，非0表示失败")
	private int resultCode;

	// 返回消息，成功为“success”，失败为具体失败信息
	@ApiModelProperty(notes = "返回消息，成功为“success”，失败为具体失败信息")
	private String resultMessage;

	// 返回数据
	@ApiModelProperty(notes = "返回的具体数据")
	private Object resultData;

	public BaseResult() {

	}

	public BaseResult(String resultMessage, Object resultData) {
		this.resultMessage = resultMessage;
		this.resultData = resultData;
	}

	public BaseResult(int resultCode, String resultMessage) {
		this.resultCode = resultCode;
		this.resultMessage = resultMessage;
	}

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

	public Object getResultData() {
		return resultData;
	}

	public void setResultData(Object resultData) {
		this.resultData = resultData;
	}

	@Override
	public String toString() {
		return "BaseResultVo [resultCode=" + resultCode + ", resultMessage=" + resultMessage + ", resultData=" + resultData + "]";
	}

}
