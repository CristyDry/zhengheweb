package com.fullteem.modules.zhenghe.api.entity.request;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "添加地址",description = "类型:String")
public class RequestAddAddress {
	@ApiModelProperty(value = "地址id，修改操作必选",required = false)
	private String id;
	@ApiModelProperty(value = "患者id，必选",required = true)
	private String patientId;
	@ApiModelProperty(value = "省份id，必选",required = true)
	private String provinceId;
	@ApiModelProperty(value = "城市id，可选",required = true)
	private String cityId;
	@ApiModelProperty(value = "区id，可选",required = true)
	private String districtId;
	@ApiModelProperty(value = "详细地址，必选",required = true)
	private String address;
	@ApiModelProperty(value = "收件人，必选",required = true)
	private String name;
	@ApiModelProperty(value = "电话号码，必选",required = true)
	private String phone;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	

	
	
	
}
