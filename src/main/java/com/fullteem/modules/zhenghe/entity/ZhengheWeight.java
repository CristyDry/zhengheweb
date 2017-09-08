/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.modules.zhenghe.entity;

import org.hibernate.validator.constraints.Length;

import com.fullteem.common.persistence.DataEntity;

/**
 * 体重Entity
 * @author 李启华
 * @version 2015-11-18
 */
public class ZhengheWeight extends DataEntity<ZhengheWeight> {
	
	private static final long serialVersionUID = 1L;
	private String stature;		// 身高
	private String patientId;		// 患者id
	private String weight;		// 体重
	private String exponent;		// 指数
	private String deleteMark;		// 删除标记
	private String remark;		// 备注
	private String mender;		// 修改者
	private String creator;		// 创建者
	
	public ZhengheWeight() {
		super();
	}

	public ZhengheWeight(String id){
		super(id);
	}

	@Length(min=0, max=32, message="身高长度必须介于 0 和 32 之间")
	public String getStature() {
		return stature;
	}

	public void setStature(String stature) {
		this.stature = stature;
	}
	
	@Length(min=0, max=64, message="患者id长度必须介于 0 和 64 之间")
	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	
	@Length(min=0, max=32, message="体重长度必须介于 0 和 32 之间")
	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}
	
	@Length(min=0, max=32, message="指数长度必须介于 0 和 32 之间")
	public String getExponent() {
		return exponent;
	}

	public void setExponent(String exponent) {
		this.exponent = exponent;
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