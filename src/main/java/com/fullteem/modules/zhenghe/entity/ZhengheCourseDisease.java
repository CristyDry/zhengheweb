/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.modules.zhenghe.entity;

import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fullteem.common.persistence.DataEntity;

/**
 * 病程Entity
 * @author 李启华
 * @version 2015-11-18
 */
public class ZhengheCourseDisease extends DataEntity<ZhengheCourseDisease> {
	
	private static final long serialVersionUID = 1L;
	private String mhId;		// 病历id
	private Date cdDate;		// 日期
	private String incident;		// 事件
	private String remark;		// 备注
	private String standby1;		// 备用字段1
	private String deleteMark;		// 删除标记
	private String mender;		// 修改者
	private String creator;		// 创建者
	private List<String> image;		//图片
	
	private String cdDate2;	//日期
	
	public String getCdDate2() {
		return cdDate2;
	}

	public void setCdDate2(String cdDate2) {
		this.cdDate2 = cdDate2;
	}

	public List<String> getImage() {
		return image;
	}

	public void setImage(List<String> image) {
		this.image = image;
	}

	public ZhengheCourseDisease() {
		super();
	}

	public ZhengheCourseDisease(String id){
		super(id);
	}

	@Length(min=0, max=64, message="病历id长度必须介于 0 和 64 之间")
	public String getMhId() {
		return mhId;
	}

	public void setMhId(String mhId) {
		this.mhId = mhId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCdDate() {
		return cdDate;
	}

	public void setCdDate(Date cdDate) {
		this.cdDate = cdDate;
	}
	
	@Length(min=0, max=32, message="事件长度必须介于 0 和 32 之间")
	public String getIncident() {
		return incident;
	}

	public void setIncident(String incident) {
		this.incident = incident;
	}
	
	@Length(min=0, max=255, message="备注长度必须介于 0 和 255 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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