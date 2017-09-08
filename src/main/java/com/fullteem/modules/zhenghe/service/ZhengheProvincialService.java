/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.modules.zhenghe.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fullteem.common.persistence.Page;
import com.fullteem.common.service.CrudService;
import com.fullteem.modules.zhenghe.entity.ZhengheProvincial;
import com.fullteem.modules.zhenghe.dao.ZhengheProvincialDao;

/**
 * 省份Service
 * @author 李启华
 * @version 2015-11-11
 */
@Service
@Transactional(readOnly = true)
public class ZhengheProvincialService extends CrudService<ZhengheProvincialDao, ZhengheProvincial> {

	public ZhengheProvincial get(String id) {
		return super.get(id);
	}
	
	public List<ZhengheProvincial> findList(ZhengheProvincial zhengheProvincial) {
		return super.findList(zhengheProvincial);
	}
	
	public Page<ZhengheProvincial> findPage(Page<ZhengheProvincial> page, ZhengheProvincial zhengheProvincial) {
		return super.findPage(page, zhengheProvincial);
	}
	
	@Transactional(readOnly = false)
	public void save(ZhengheProvincial zhengheProvincial) {
		super.save(zhengheProvincial);
	}
	
	@Transactional(readOnly = false)
	public void delete(ZhengheProvincial zhengheProvincial) {
		super.delete(zhengheProvincial);
	}
	
}