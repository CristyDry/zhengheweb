/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.modules.zhenghe.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fullteem.common.persistence.Page;
import com.fullteem.common.service.CrudService;
import com.fullteem.modules.zhenghe.entity.ZhengheBuyCar;
import com.fullteem.modules.zhenghe.dao.ZhengheBuyCarDao;

/**
 * 购物车Service
 * @author 李启华
 * @version 2015-11-17
 */
@Service
@Transactional(readOnly = true)
public class ZhengheBuyCarService extends CrudService<ZhengheBuyCarDao, ZhengheBuyCar> {

	public ZhengheBuyCar get(String id) {
		return super.get(id);
	}
	
	public List<ZhengheBuyCar> findList(ZhengheBuyCar zhengheBuyCar) {
		return super.findList(zhengheBuyCar);
	}
	
	public Page<ZhengheBuyCar> findPage(Page<ZhengheBuyCar> page, ZhengheBuyCar zhengheBuyCar) {
		return super.findPage(page, zhengheBuyCar);
	}
	
	@Transactional(readOnly = false)
	public void save(ZhengheBuyCar zhengheBuyCar) {
		super.save(zhengheBuyCar);
	}
	
	@Transactional(readOnly = false)
	public void delete(ZhengheBuyCar zhengheBuyCar) {
		super.delete(zhengheBuyCar);
	}
	
}