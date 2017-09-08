/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.modules.zhenghe.entity;

import org.hibernate.validator.constraints.Length;

import com.fullteem.common.persistence.DataEntity;

/**
 * 省份Entity
 * @author 李启华
 * @version 2015-11-11
 */
public class ZhengheProvincial extends DataEntity<ZhengheProvincial> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名称
	private String wm;		// 缩写
	
	public ZhengheProvincial() {
		super();
	}

	public ZhengheProvincial(String id){
		super(id);
	}

	@Length(min=0, max=32, message="名称长度必须介于 0 和 32 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=32, message="缩写长度必须介于 0 和 32 之间")
	public String getWm() {
		return wm;
	}

	public void setWm(String wm) {
		this.wm = wm;
	}

	@Override
	public String toString() {
		return "ZhengheProvincial [name=" + name + ", wm=" + wm + "]";
	}
	
}