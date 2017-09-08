/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.modules.zhenghe.entity;

import org.hibernate.validator.constraints.Length;

import com.fullteem.common.persistence.DataEntity;

/**
 * 医生管理Entity
 * @author 李启华
 * @version 2015-11-11
 */
public class ZhengheDoctor extends DataEntity<ZhengheDoctor> {
	
	private static final long serialVersionUID = 1L;
	private String hospitalId;		// 医院id
	private String doctorName;		// 医生名
	private String gender;			// 性别
	private String departmentsId;	// 科室id
	private String avatar;			// 头像
	private String intro;			// 简介
	private String professionalField;		// 专业领域
	private String jobTitle;		// 职称id
	private String phone;			// 手机
	private String password;		// 密码
	private String status;			// 状态1正常0冻结
	private String type;			// 类型1医生2专家
	private String wmUrl;			// 二维码地址
	private String standby1;		// 备用字段1
	private String standby2;		// 备用字段2
	private String deleteMark;		// 删除标记
	private String remark;			// 备注
	private String mender;			// 修改者
	private String creator;			// 创建者
	
	private String hospitalName;		//所属医院名称
	private String departmentName;		//所属科室名称
	private String jobTitleName;		//职称
	private String rank;				//排序
	
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 6666;
	}

	public ZhengheDoctor() {
		super();
	}

	public ZhengheDoctor(String id){
		super(id);
	}

	@Length(min=1, max=64, message="医院id长度必须介于 1 和 64 之间")
	public String getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}
	
	@Length(min=1, max=32, message="医生名长度必须介于 1 和 32 之间")
	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	
	@Length(min=1, max=2, message="性别长度必须介于 1 和 2 之间")
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@Length(min=1, max=64, message="科室id长度必须介于 1 和 64 之间")
	public String getDepartmentsId() {
		return departmentsId;
	}

	public void setDepartmentsId(String departmentsId) {
		this.departmentsId = departmentsId;
	}
	
	@Length(min=1, max=255, message="头像长度必须介于 1 和 255 之间")
	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	@Length(min=1, max=255, message="简介长度必须介于 1 和 255 之间")
	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}
	
	@Length(min=1, max=255, message="专业领域长度必须介于 1 和 255 之间")
	public String getProfessionalField() {
		return professionalField;
	}

	public void setProfessionalField(String professionalField) {
		this.professionalField = professionalField;
	}
	
	@Length(min=1, max=32, message="职称长度必须介于 1 和 32 之间")
	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	
	@Length(min=1, max=11, message="手机长度必须介于 1 和 11 之间")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Length(min=0, max=32, message="密码长度必须介于 0 和 32 之间")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Length(min=1, max=2, message="状态1正常0冻结长度必须介于 1 和 2 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=1, max=2, message="类型1医生2专家长度必须介于 1 和 2 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=255, message="二维码地址长度必须介于 0 和 255 之间")
	public String getWmUrl() {
		return wmUrl;
	}

	public void setWmUrl(String wmUrl) {
		this.wmUrl = wmUrl;
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

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getJobTitleName() {
		return jobTitleName;
	}

	public void setJobTitleName(String jobTitleName) {
		this.jobTitleName = jobTitleName;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

}