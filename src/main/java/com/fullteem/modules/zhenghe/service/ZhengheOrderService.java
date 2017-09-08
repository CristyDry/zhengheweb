/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.modules.zhenghe.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fullteem.common.persistence.Page;
import com.fullteem.common.service.CrudService;
import com.fullteem.modules.zhenghe.entity.ZhengheOrder;
import com.fullteem.modules.zhenghe.dao.ZhengheOrderDao;

/**
 * 订单表Service
 * @author 李启华
 * @version 2015-11-18
 */
@Service
@Transactional(readOnly = true)
public class ZhengheOrderService extends CrudService<ZhengheOrderDao, ZhengheOrder> {

	public ZhengheOrder get(String id) {
		return super.get(id);
	}
	
	public List<ZhengheOrder> findList(ZhengheOrder zhengheOrder) {
		return super.findList(zhengheOrder);
	}
	
	public Page<ZhengheOrder> findPage(Page<ZhengheOrder> page, ZhengheOrder zhengheOrder) {
		return super.findPage(page, zhengheOrder);
	}
	
	@Transactional(readOnly = false)
	public void save(ZhengheOrder zhengheOrder) {
		super.save(zhengheOrder);
	}
	
	@Transactional(readOnly = false)
	public void delete(ZhengheOrder zhengheOrder) {
		super.delete(zhengheOrder);
	}
	
}