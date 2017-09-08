/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.modules.zhenghe.entity;

import org.hibernate.validator.constraints.Length;

import com.fullteem.common.persistence.DataEntity;

/**
 * 科室Entity
 * @author 李启华
 * @version 2015-11-11
 */
public class ZhengheDepartments extends DataEntity<ZhengheDepartments> {
	
	private static final long serialVersionUID = 1L;
	private String avatar;				// 图标
	private String sDepartmentName;		// 上级科室名称
	private String departmentsName;		// 科室名
	private String sDepartmentsId;		// 上级科室id
	private String description;			// 描述
	private String type;				// 类型1为一级科室2为二级
	private String standby1;			// 备用字段1
	private String standby2;			// 备用字段2
	private String deleteMark;			// 删除标记
	private String remark;				// 备注
	private String mender;				// 修改者
	private String creator;				// 创建者
	
	public ZhengheDepartments() {
		super();
	}

	public ZhengheDepartments(String id){
		super(id);
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getsDepartmentName() {
		return sDepartmentName;
	}

	public void setsDepartmentName(String sDepartmentName) {
		this.sDepartmentName = sDepartmentName;
	}

	@Length(min=1, max=32, message="科室名长度必须介于 1 和 32 之间")
	public String getDepartmentsName() {
		return departmentsName;
	}

	public void setDepartmentsName(String departmentsName) {
		this.departmentsName = departmentsName;
	}
	
	@Length(min=0, max=64, message="上级科室id长度必须介于 0 和 64 之间")
	public String getSDepartmentsId() {
		return sDepartmentsId;
	}

	public void setSDepartmentsId(String sDepartmentsId) {
		this.sDepartmentsId = sDepartmentsId;
	}
	
	@Length(min=0, max=255, message="描述长度必须介于 0 和 255 之间")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Length(min=1, max=2, message="类型1为一级科室2为二级长度必须介于 1 和 2 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=255, message="备用字段1长度必须介于 0 和 255 之间")
	public String getStandby1() {
		return standby1;
	}

	public void setStandby1(String standby1) {
		this.standby1 = standby1;
	}
	
	@Length(min=0, max=255, message="备用字段2长度必须介于 0 和 255 之间")
	public String getStandby2() {
		return standby2;
	}

	public void setStandby2(String standby2) {
		this.standby2 = standby2;
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

	@Override
	public String toString() {
		return "ZhengheDepartments [sDepartmentName=" + sDepartmentName
				+ ", departmentsName=" + departmentsName + ", sDepartmentsId="
				+ sDepartmentsId + ", description=" + description + ", type="
				+ type + ", standby1=" + standby1 + ", standby2=" + standby2
				+ ", deleteMark=" + deleteMark + ", remark=" + remark
				+ ", mender=" + mender + ", creator=" + creator + "]";
	}
	
}