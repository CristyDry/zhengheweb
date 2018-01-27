/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.modules.zhenghe.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fullteem.common.persistence.Page;
import com.fullteem.common.service.CrudService;
import com.fullteem.modules.zhenghe.entity.ZhenghePharmacist;
import com.fullteem.modules.zhenghe.dao.ZhenghePharmacistDao;

/**
 * 药师Service
 * @author LeVis
 * @version 2017-11-28
 */
@Service
@Transactional(readOnly = true)
public class ZhenghePharmacistService extends CrudService<ZhenghePharmacistDao, ZhenghePharmacist> {

	public ZhenghePharmacist get(String id) {
		return super.get(id);
	}
	
	public List<ZhenghePharmacist> findList(ZhenghePharmacist zhenghePharmacist) {
		return super.findList(zhenghePharmacist);
	}
	
	public Page<ZhenghePharmacist> findPage(Page<ZhenghePharmacist> page, ZhenghePharmacist zhenghePharmacist) {
		return super.findPage(page, zhenghePharmacist);
	}
	
	@Transactional(readOnly = false)
	public void save(ZhenghePharmacist zhenghePharmacist) {
		super.save(zhenghePharmacist);
	}
	
	@Transactional(readOnly = false)
	public void delete(ZhenghePharmacist zhenghePharmacist) {
		super.delete(zhenghePharmacist);
	}
	
}