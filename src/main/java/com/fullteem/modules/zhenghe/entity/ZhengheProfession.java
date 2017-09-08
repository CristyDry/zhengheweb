/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.modules.zhenghe.entity;

import org.hibernate.validator.constraints.Length;

import com.fullteem.common.persistence.DataEntity;

/**
 * 医生职称Entity
 * @author 李启华
 * @version 2015-11-11
 */
public class ZhengheProfession extends DataEntity<ZhengheProfession> {
	
	private static final long serialVersionUID = 1L;
	private String profession;		// 医生称职
	
	public ZhengheProfession() {
		super();
	}

	public ZhengheProfession(String id){
		super(id);
	}

	@Length(min=1, max=255, message="医生称职长度必须介于 1 和 255 之间")
	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}
	
}