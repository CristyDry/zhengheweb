/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.modules.zhenghe.entity;

import org.hibernate.validator.constraints.Length;

import com.fullteem.common.persistence.DataEntity;

/**
 * 用户反馈Entity
 * @author 李启华
 * @version 2015-11-11
 */
public class ZhengheIdea extends DataEntity<ZhengheIdea> {
	
	private static final long serialVersionUID = 1L;
	private String patientId;		// 患者id
	private String content;		// 内容
	private String doctorId;		// 医生id
	private String phone;		// 手机
	private String type;		// 类型1患者2医生
	private String zhengheVersion;		// 版本
	private String zhengheSystem;		// 系统
	private String zhengheType;		// 机型
	private String standby1;		// 备用字段1
	private String deleteMark;		// 删除标记
	private String remark;		// 备注
	private String mender;		// 修改者
	private String creator;		// 创建者
	
	public ZhengheIdea() {
		super();
	}

	public ZhengheIdea(String id){
		super(id);
	}

	@Length(min=0, max=64, message="患者id长度必须介于 0 和 64 之间")
	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	
	@Length(min=0, max=255, message="内容长度必须介于 0 和 255 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=64, message="医生id长度必须介于 0 和 64 之间")
	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
	
	@Length(min=0, max=11, message="手机长度必须介于 0 和 11 之间")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Length(min=0, max=2, message="类型1患者2医生长度必须介于 0 和 2 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=32, message="版本长度必须介于 0 和 32 之间")
	public String getZhengheVersion() {
		return zhengheVersion;
	}

	public void setZhengheVersion(String zhengheVersion) {
		this.zhengheVersion = zhengheVersion;
	}
	
	@Length(min=0, max=32, message="系统长度必须介于 0 和 32 之间")
	public String getZhengheSystem() {
		return zhengheSystem;
	}

	public void setZhengheSystem(String zhengheSystem) {
		this.zhengheSystem = zhengheSystem;
	}
	
	@Length(min=0, max=32, message="机型长度必须介于 0 和 32 之间")
	public String getZhengheType() {
		return zhengheType;
	}

	public void setZhengheType(String zhengheType) {
		this.zhengheType = zhengheType;
	}
	
	@Length(min=0, max=255, message="备用字段1长度必须介于 0 和 255 之间")
	public String getStandby1() {
		return standby1;
	}

	public void setStandby1(String standby1) {
		this.standby1 = standby1;
	}
	
	@Length(min=0, max=32, message="删除标记长度必须介于 0 和 32 之间")
	public String getDeleteMark() {
		return deleteMark;
	}

	public void setDeleteMark(String deleteMark) {
		this.deleteMark = deleteMark;
	}
	
	@Length(min=0, max=255, message="备注长度必须介于 0 和 255 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Length(min=0, max=32, message="修改者长度必须介于 0 和 32 之间")
	public String getMender() {
		return mender;
	}

	public void setMender(String mender) {
		this.mender = mender;
	}
	
	@Length(min=0, max=32, message="创建者长度必须介于 0 和 32 之间")
	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}
	
}