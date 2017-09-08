/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.modules.zhenghe.entity;

import org.hibernate.validator.constraints.Length;

import com.fullteem.common.persistence.DataEntity;

/**
 * 地区Entity
 * @author 李启华
 * @version 2015-11-11
 */
public class ZhengheDistrict extends DataEntity<ZhengheDistrict> {
	
	private static final long serialVersionUID = 1L;
	private String cityId;		// 城市id
	private String districtName;		// 名称
	private String zipCode;		// 邮编
	
	public ZhengheDistrict() {
		super();
	}

	public ZhengheDistrict(String id){
		super(id);
	}

	@Length(min=0, max=64, message="城市id长度必须介于 0 和 64 之间")
	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	
	@Length(min=0, max=32, message="名称长度必须介于 0 和 32 之间")
	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	
	@Length(min=0, max=32, message="邮编长度必须介于 0 和 32 之间")
	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@Override
	public String toString() {
		return "ZhengheDistrict [cityId=" + cityId + ", districtName="
				+ districtName + ", zipCode=" + zipCode + "]";
	}
	
}