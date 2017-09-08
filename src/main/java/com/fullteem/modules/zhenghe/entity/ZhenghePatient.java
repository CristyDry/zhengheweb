/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.modules.zhenghe.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fullteem.common.persistence.DataEntity;

/**
 * patientEntity
 * @author 李启华
 * @version 2015-11-10
 */
public class ZhenghePatient extends DataEntity<ZhenghePatient> {
	
	private static final long serialVersionUID = 1L;
	private String patientName;			// 患者姓名
	private String accountNumber;		// 账号
	private String password;			// 密码
	private String gender;				// 性别
	private String age;					// 年龄
	private String avatar;				// 头像
	private Date birthday;				// 出生年月日
	private String status;				// 状态1为正常2为锁定
	private String phone;				// 手机号
	private String provincialId;		// 省份id
	private String cityId;				// 城市id
	private String districtId;			// 区县id
	private String channel;				// 注册渠道1平台注册2第三方
	private String deleteMark;			// 删除标记
	private String remark;				// 备注
	private String mender;				// 修改者
	private String creator;				// 创建者
	private String standby1;			// 备用字段1
	
	private String provinceName;		//省份名称
	private String cityName;			//城市名称
	private String districtName;		//地区名称
	
	public ZhenghePatient() {
		super();
	}

	public ZhenghePatient(String id){
		super(id);
	}

	@Length(min=0, max=32, message="患者姓名长度必须介于 0 和 32 之间")
	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	
	@Length(min=0, max=32, message="账号长度必须介于 0 和 32 之间")
	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	@Length(min=0, max=32, message="密码长度必须介于 0 和 32 之间")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Length(min=0, max=2, message="性别长度必须介于 0 和 2 之间")
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@Length(min=0, max=2, message="年龄长度必须介于 0 和 2 之间")
	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
	
	@Length(min=0, max=255, message="头像长度必须介于 0 和 255 之间")
	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	@Length(min=0, max=2, message="状态1为正常2为锁定长度必须介于 0 和 2 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=11, message="手机号长度必须介于 0 和 11 之间")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Length(min=0, max=64, message="省份id长度必须介于 0 和 64 之间")
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
	
	@Length(min=0, max=2, message="注册渠道1平台注册2第三方长度必须介于 0 和 2 之间")
	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
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
	
	@Length(min=0, max=255, message="备用字段1长度必须介于 0 和 255 之间")
	public String getStandby1() {
		return standby1;
	}

	public void setStandby1(String standby1) {
		this.standby1 = standby1;
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

}