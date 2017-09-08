/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.modules.zhenghe.entity;

/**
 * 好友关系Entity
 * @author 陈协
 * @version 2015-11-12
 */
public class FriendZhenghe {
	
	private String id;
	private String avatar;
	private String name;
	private String verifyContent;
	private String status;
	private String gender;
	private String age;
	private String address;
	
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVerifyContent() {
		return verifyContent;
	}
	public void setVerifyContent(String verifyContent) {
		this.verifyContent = verifyContent;
	}
	
	
	
	
}