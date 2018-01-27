/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.modules.zhenghe.entity;

import org.hibernate.validator.constraints.Length;

import com.fullteem.common.persistence.DataEntity;

/**
 * 药师Entity
 * @author LeVis
 * @version 2017-11-28
 */
public class ZhenghePharmacist extends DataEntity<ZhenghePharmacist> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名称
	private String tel;		// 电话
	private String icon;		// 头像
	private String img;		// 资质
	private String msg;		// 描述
	
	public ZhenghePharmacist() {
		super();
	}

	public ZhenghePharmacist(String id){
		super(id);
	}

	@Length(min=0, max=32, message="名称长度必须介于 0 和 32 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=32, message="电话长度必须介于 0 和 32 之间")
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
	@Length(min=0, max=128, message="头像长度必须介于 0 和 128 之间")
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	@Length(min=0, max=128, message="资质长度必须介于 0 和 128 之间")
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
	@Length(min=0, max=128, message="描述长度必须介于 0 和 128 之间")
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}