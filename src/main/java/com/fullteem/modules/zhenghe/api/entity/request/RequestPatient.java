package com.fullteem.modules.zhenghe.api.entity.request;

import java.util.Date;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;


@ApiModel(value="患者",description="患者")
public class RequestPatient {
	@ApiModelProperty(value="患者Id",required=true)
	private String patientId;
	@ApiModelProperty(value="患者姓名",required=true)
	private String patientName;
	@ApiModelProperty(value="性别",required=true)
	private String gender;
	@ApiModelProperty(value="出生日期",required=true)
	private String birthday;
	@ApiModelProperty(value="省份id",required=true)
	private String provincialId;		
	@ApiModelProperty(value="城市id",required=true)
	private String cityId;	
	@ApiModelProperty(value="区县id",required=true)
	private String districtId;
	@ApiModelProperty(value="密码",required=true)
	private String password;
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
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
	public String getProvincialId() {
		return provincialId;
	}
	public void setProvincialId(String provincialId) {
		this.provincialId = provincialId;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getDistrictId() {
		return districtId;
	}
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	

}
