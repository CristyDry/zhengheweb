package com.fullteem.modules.zhenghe.api.entity.request;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "生活日志",description = "类型:String")
public class RequestLifeLog {
	@ApiModelProperty(value = "病人id",required = true)
	private String patientId;
	@ApiModelProperty(value = "标题",required = true)
	private String title;
	@ApiModelProperty(value = "内容",required = true)
	private String content;
	@ApiModelProperty(value = "生活日志id",required = false)
	private String logId;
	
	public String getLogId() {
		return logId;
	}
	public void setLogId(String logId) {
		this.logId = logId;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	
	
}
