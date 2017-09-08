/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.modules.zhenghe.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fullteem.common.persistence.DataEntity;

/**
 * 消息管理Entity
 * @author 李启华
 * @version 2015-11-11
 */
public class ZhengheMessage extends DataEntity<ZhengheMessage> {
	
	private static final long serialVersionUID = 1L;
	private String title;		// 消息标题
	private String content;		// 消息内容
	private String status;		// 状态1已读0未读
	private String type;		// 类型1系统消息2咨询消息
	private String doctorId;		// 医生id
	private String patientId;		// 患者id
	private String differentiateId;		// 角色区分1患者2医生
	private String standby1;		// 备用字段1
	private String deleteMark;		// 删除标记
	private String remark;		// 备注
	private String mender;		// 修改者
	private String creator;		// 创建者
	
	private Date createDate;	//创建日期
	private Date updateDate;	//更新日期
	
	
	public ZhengheMessage() {
		super();
	}

	public ZhengheMessage(String id){
		super(id);
	}

	@Length(min=1, max=32, message="消息标题长度必须介于 1 和 32 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=1, max=255, message="消息内容长度必须介于 1 和 255 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=1, max=2, message="状态1已读0未读长度必须介于 1 和 2 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=1, max=2, message="类型1系统消息2咨询消息长度必须介于 1 和 2 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=64, message="医生id长度必须介于 0 和 64 之间")
	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
	
	@Length(min=0, max=64, message="患者id长度必须介于 0 和 64 之间")
	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	
	@Length(min=1, max=2, message="角色区分1患者2医生长度必须介于 1 和 2 之间")
	public String getDifferentiateId() {
		return differentiateId;
	}

	public void setDifferentiateId(String differentiateId) {
		this.differentiateId = differentiateId;
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public String toString() {
		return "ZhengheMessage [title=" + title + ", content=" + content
				+ ", status=" + status + ", type=" + type + ", doctorId="
				+ doctorId + ", patientId=" + patientId + ", differentiateId="
				+ differentiateId + ", standby1=" + standby1 + ", deleteMark="
				+ deleteMark + ", remark=" + remark + ", mender=" + mender
				+ ", creator=" + creator + ", createDate=" + createDate
				+ ", updateDate=" + updateDate + "]";
	}
	
}