package com.fullteem.modules.zhenghe.entity;

/**
 * patientEntity
 * @author 陈协
 * @version 2015-11-12
 */
public class PatientZhenghe {

	private String id;
	private String avatar;		// 头像
	private String patient;		// 患者姓名
	private String gender;		// 性别
	private String age;			// 年龄
	private String address;		// 地区
	private String groupId;		// 组id
	private String groupName;	// 组名
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getPatient() {
		return patient;
	}
	public void setPatient(String patient) {
		this.patient = patient;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	
	
}