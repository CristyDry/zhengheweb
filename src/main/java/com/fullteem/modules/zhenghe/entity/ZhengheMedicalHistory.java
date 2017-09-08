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
 * 病历表Entity
 * @author 李启华
 * @version 2015-11-18
 */
public class ZhengheMedicalHistory extends DataEntity<ZhengheMedicalHistory> {
	
	private static final long serialVersionUID = 1L;
	private String mhTitle;		// 病历标题
	private String mhName;		// 姓名
	private String gender;		// 性别
	private Date birthday;		// 出生年月日
	private String departmentsName;		// 科室名称
	private String patientId;		// 患者id
	private Date seeadoctorDate;		// 就诊时间
	private String diagnose;		// 医生诊断
	private String description;		// 基本病情
	private String standby1;		// 备用字段1
	private String deleteMark;		// 删除标记
	private String remark;		// 备注
	private String mender;		// 修改者
	private String creator;		// 创建者

	private String birthday2;		// 出生年月日(格式化)
	private String seeadoctorDate2;		// 就诊时间(格式化)
	private List<String> image;		//图片
	private String updateDate2;		//更新日期
	
	private List<ZhengheCourseDisease> cdList;	//病程集合
	

	public String getUpdateDate2() {
		return updateDate2;
	}

	public void setUpdateDate2(String updateDate2) {
		this.updateDate2 = updateDate2;
	}

	public List<String> getImage() {
		return image;
	}

	public void setImage(List<String> image) {
		this.image = image;
	}

	public String getBirthday2() {
		return birthday2;
	}

	public void setBirthday2(String birthday2) {
		this.birthday2 = birthday2;
	}

	public String getSeeadoctorDate2() {
		return seeadoctorDate2;
	}

	public void setSeeadoctorDate2(String seeadoctorDate2) {
		this.seeadoctorDate2 = seeadoctorDate2;
	}

	public ZhengheMedicalHistory() {
		super();
	}

	public ZhengheMedicalHistory(String id){
		super(id);
	}

	@Length(min=0, max=32, message="病历标题长度必须介于 0 和 32 之间")
	public String getMhTitle() {
		return mhTitle;
	}

	public void setMhTitle(String mhTitle) {
		this.mhTitle = mhTitle;
	}
	
	@Length(min=0, max=32, message="姓名长度必须介于 0 和 32 之间")
	public String getMhName() {
		return mhName;
	}

	public void setMhName(String mhName) {
		this.mhName = mhName;
	}
	
	@Length(min=0, max=2, message="性别长度必须介于 0 和 2 之间")
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	@Length(min=0, max=32, message="科室名称长度必须介于 0 和 32 之间")
	public String getDepartmentsName() {
		return departmentsName;
	}

	public void setDepartmentsName(String departmentsName) {
		this.departmentsName = departmentsName;
	}
	
	@Length(min=0, max=64, message="患者id长度必须介于 0 和 64 之间")
	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	
	@Length(min=0, max=32, message="就诊时间长度必须介于 0 和 32 之间")
	public Date getSeeadoctorDate() {
		return seeadoctorDate;
	}

	public void setSeeadoctorDate(Date seeadoctorDate) {
		this.seeadoctorDate = seeadoctorDate;
	}
	
	@Length(min=0, max=255, message="医生诊断长度必须介于 0 和 255 之间")
	public String getDiagnose() {
		return diagnose;
	}

	public void setDiagnose(String diagnose) {
		this.diagnose = diagnose;
	}
	
	@Length(min=0, max=255, message="基本病情长度必须介于 0 和 255 之间")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public List<ZhengheCourseDisease> getCdList() {
		return cdList;
	}

	public void setCdList(List<ZhengheCourseDisease> cdList) {
		this.cdList = cdList;
	}
	
}