/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.modules.zhenghe.entity;

import java.util.List;

/**
 * 科室Entity
 * @author 陈协
 * @version 2015-11-12
 */
public class DepartmentsZhenghe {
	
	private String id;		//科室id
	private String departmentsName;		// 科室名
	private String avatar;		//科室图片
	
	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDepartmentsName() {
		return departmentsName;
	}

	public void setDepartmentsName(String departmentsName) {
		this.departmentsName = departmentsName;
	}
	
}