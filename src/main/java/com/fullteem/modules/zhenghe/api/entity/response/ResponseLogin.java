/**   
 * 文件名：ResponseCollect.java</br>
 * 描述： </br>
 * 开发人员：洪惜意 </br>
 * 创建时间： 2015年10月30日
 */ 

package com.fullteem.modules.zhenghe.api.entity.response;

import java.util.Date;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value="患者登录接口的返回信息")
public class ResponseLogin {
	@ApiModelProperty(value="患者id")
	private String id;					// 患者id
	@ApiModelProperty(value="患者姓名")
	private String patientName;			// 患者姓名
	@ApiModelProperty(value="账号")
	private String accountNumber;		// 账号
	@ApiModelProperty(value="性别")
	private String gender;				// 性别
	@ApiModelProperty(value="年龄")
	private String age;					// 年龄
	@ApiModelProperty(value="头像")
	private String avatar;				// 头像
	@ApiModelProperty(value="出生年月日")
	private String birthday;				// 出生年月日
	@ApiModelProperty(value="状态1为正常2为锁定")
	private String status;				// 状态1为正常2为锁定
	@ApiModelProperty(value="手机号")
	private String phone;				// 手机号
	@ApiModelProperty(value="省份id")
	private String provincialId;		// 省份id
	@ApiModelProperty(value="城市id")
	private String cityId;				// 城市id
	@ApiModelProperty(value="区县id")
	private String districtId;			// 区县id
	@ApiModelProperty(value="注册渠道1平台注册2第三方")
	private String channel;				// 注册渠道1平台注册2第三方
	@ApiModelProperty(value="备注")
	private String remark;				// 备注
	@ApiModelProperty(value="省份名称")
	private String provinceName;		//省份名称
	@ApiModelProperty(value="城市名称")
	private String cityName;			//城市名称
	@ApiModelProperty(value="地区名称")
	private String districtName;		//地区名称
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
