/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.modules.zhenghe.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fullteem.common.persistence.Page;
import com.fullteem.common.service.CrudService;
import com.fullteem.modules.zhenghe.entity.ZhengheBloodPressure;
import com.fullteem.modules.zhenghe.dao.ZhengheBloodPressureDao;

/**
 * 血压Service
 * @author 李启华
 * @version 2015-11-18
 */
@Service
@Transactional(readOnly = true)
public class ZhengheBloodPressureService extends CrudService<ZhengheBloodPressureDao, ZhengheBloodPressure> {

	public ZhengheBloodPressure get(String id) {
		return super.get(id);
	}
	
	public List<ZhengheBloodPressure> findList(ZhengheBloodPressure zhengheBloodPressure) {
		return super.findList(zhengheBloodPressure);
	}
	
	public Page<ZhengheBloodPressure> findPage(Page<ZhengheBloodPressure> page, ZhengheBloodPressure zhengheBloodPressure) {
		return super.findPage(page, zhengheBloodPressure);
	}
	
	@Transactional(readOnly = false)
	public void save(ZhengheBloodPressure zhengheBloodPressure) {
		super.save(zhengheBloodPressure);
	}
	
	@Transactional(readOnly = false)
	public void delete(ZhengheBloodPressure zhengheBloodPressure) {
		super.delete(zhengheBloodPressure);
	}
	
}