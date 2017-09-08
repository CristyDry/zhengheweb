/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.modules.zhenghe.entity;

import org.hibernate.validator.constraints.Length;

import com.fullteem.common.persistence.DataEntity;

/**
 * 城市Entity
 * @author 李启华
 * @version 2015-11-11
 */
public class ZhengheCity extends DataEntity<ZhengheCity> {
	
	private static final long serialVersionUID = 1L;
	private String provincialId;		// 省份id
	private String cityName;		// 名称
	private String cityNo;		// 区号
	
	public ZhengheCity() {
		super();
	}

	public ZhengheCity(String id){
		super(id);
	}

	@Length(min=0, max=64, message="省份id长度必须介于 0 和 64 之间")
	public String getProvincialId() {
		return provincialId;
	}

	public void setProvincialId(String provincialId) {
		this.provincialId = provincialId;
	}
	
	@Length(min=0, max=32, message="名称长度必须介于 0 和 32 之间")
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	@Length(min=0, max=32, message="区号长度必须介于 0 和 32 之间")
	public String getCityNo() {
		return cityNo;
	}

	public void setCityNo(String cityNo) {
		this.cityNo = cityNo;
	}

	@Override
	public String toString() {
		return "ZhengheCity [provincialId=" + provincialId + ", cityName="
				+ cityName + ", cityNo=" + cityNo + "]";
	}
	
}