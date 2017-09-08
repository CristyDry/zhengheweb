/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.modules.zhenghe.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fullteem.common.persistence.Page;
import com.fullteem.common.service.CrudService;
import com.fullteem.modules.zhenghe.entity.ZhengheWeight;
import com.fullteem.modules.zhenghe.dao.ZhengheWeightDao;

/**
 * 体重Service
 * @author 李启华
 * @version 2015-11-18
 */
@Service
@Transactional(readOnly = true)
public class ZhengheWeightService extends CrudService<ZhengheWeightDao, ZhengheWeight> {

	public ZhengheWeight get(String id) {
		return super.get(id);
	}
	
	public List<ZhengheWeight> findList(ZhengheWeight zhengheWeight) {
		return super.findList(zhengheWeight);
	}
	
	public Page<ZhengheWeight> findPage(Page<ZhengheWeight> page, ZhengheWeight zhengheWeight) {
		return super.findPage(page, zhengheWeight);
	}
	
	@Transactional(readOnly = false)
	public void save(ZhengheWeight zhengheWeight) {
		super.save(zhengheWeight);
	}
	
	@Transactional(readOnly = false)
	public void delete(ZhengheWeight zhengheWeight) {
		super.delete(zhengheWeight);
	}
	
}