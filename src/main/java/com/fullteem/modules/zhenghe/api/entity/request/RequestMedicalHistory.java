package com.fullteem.modules.zhenghe.api.entity.request;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "病历",description = "类型:String")
public class RequestMedicalHistory {
	@ApiModelProperty(value = "患者id",required = true)
	private String patientId;		// 患者id
	@ApiModelProperty(value = "病历标题",required = true)
	private String mhTitle;			// 病历标题
	@ApiModelProperty(value = "姓名",required = true)
	private String mhName;			// 姓名
	@ApiModelProperty(value = "性别",required = true)
	private String gender;			// 性别
	@ApiModelProperty(value = "出生年月日",required = true)
	private String birthday;		// 出生年月日
	@ApiModelProperty(value = "科室名",required = true)
	private String departmentName;	// 科室名
	@ApiModelProperty(value = "就诊时间",required = true)
	private String seeadoctorDate;		// 就诊时间
	@ApiModelProperty(value = "医生诊断",required = true)
	private String diagnose;		// 医生诊断
	@ApiModelProperty(value = "基本病情",required = true)
	private String description;		// 基本病情
	@ApiModelProperty(value = "病历id",required = false)
	private String medicalId;		//病历id
	
	public String getMedicalId() {
		return medicalId;
	}
	public void setMedicalId(String medicalId) {
		this.medicalId = medicalId;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getMhTitle() {
		return mhTitle;
	}
	public void setMhTitle(String mhTitle) {
		this.mhTitle = mhTitle;
	}
	public String getMhName() {
		return mhName;
	}
	public void setMhName(String mhName) {
		this.mhName = mhName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getSeeadoctorDate() {
		return seeadoctorDate;
	}
	public void setSeeadoctorDate(String seeadoctorDate) {
		this.seeadoctorDate = seeadoctorDate;
	}
	public String getDiagnose() {
		return diagnose;
	}
	public void setDiagnose(String diagnose) {
		this.diagnose = diagnose;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
