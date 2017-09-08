/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.modules.zhenghe.entity;

import org.hibernate.validator.constraints.Length;

import com.fullteem.common.persistence.DataEntity;

/**
 * 医院Entity
 * @author 李启华
 * @version 2015-11-12
 */
public class ZhengheHospital extends DataEntity<ZhengheHospital> {
	
	private static final long serialVersionUID = 1L;
	private String hospitalName;		// 医院名
	private String provincialId;		// 省份id
	private String cityId;		// 城市id
	private String districtId;		// 区县id
	private String address;		// 具体地址
	private String standby1;		// 备用字段1
	private String standby2;		// 备用字段2
	private String deleteMark;		// 删除标记
	private String remark;		// 备注
	private String mender;		// 修改者
	private String creator;		// 创建者
	
	private String provinceName;
	private String cityName;
	private String districtName;
	
	public ZhengheHospital() {
		super();
	}

	public ZhengheHospital(String id){
		super(id);
	}

	@Length(min=1, max=32, message="医院名长度必须介于 1 和 32 之间")
	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	
	@Length(min=1, max=64, message="省份id长度必须介于 1 和 64 之间")
	public String getProvincialId() {
		return provincialId;
	}

	public void setProvincialId(String provincialId) {
		this.provincialId = provincialId;
	}
	
	@Length(min=0, max=64, message="城市id长度必须介于 0 和 64 之间")
	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	
	@Length(min=0, max=64, message="区县id长度必须介于 0 和 64 之间")
	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	
	@Length(min=1, max=255, message="具体地址长度必须介于 1 和 255 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	@Override
	public String toString() {
		return "ZhengheHospital [hospitalName=" + hospitalName
				+ ", provincialId=" + provincialId + ", cityId=" + cityId
				+ ", districtId=" + districtId + ", address=" + address
				+ ", standby1=" + standby1 + ", standby2=" + standby2
				+ ", deleteMark=" + deleteMark + ", remark=" + remark
				+ ", mender=" + mender + ", creator=" + creator
				+ ", provinceName=" + provinceName + ", cityName=" + cityName
				+ ", districtName=" + districtName + "]";
	}
	
}