package com.fullteem.modules.zhenghe.api.entity.response;

import com.fullteem.modules.sys.entity.User;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value="返回已上传的产品、成分")
public class ResponseDetection {
	@ApiModelProperty(value = "检测id")
	private String detectionId;		// 检测id
	private User user;		// 用户id
	private String pName;		// 产品名称
	private String cName;		// 成分中文名
	private String eName;		// 成分英文名
	private String description;		// 描述
	private String type;		// 类型1产品2成分
	private String status;		// 状态1已处理2待处理
	private String standby1;		// 备用字段1	
	
	
	public String getDetectionId() {
		return detectionId;
	}
	public void setDetectionId(String detectionId) {
		this.detectionId = detectionId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public String geteName() {
		return eName;
	}
	public void seteName(String eName) {
		this.eName = eName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStandby1() {
		return standby1;
	}
	public void setStandby1(String standby1) {
		this.standby1 = standby1;
	}
	
	
							
}
