/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.modules.zhenghe.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fullteem.common.persistence.Page;
import com.fullteem.common.service.CrudService;
import com.fullteem.modules.zhenghe.entity.ZhenghePay;
import com.fullteem.modules.zhenghe.dao.ZhenghePayDao;

/**
 * 支付日志Service
 * @author 李启华
 * @version 2015-11-17
 */
@Service
@Transactional(readOnly = true)
public class ZhenghePayService extends CrudService<ZhenghePayDao, ZhenghePay> {

	public ZhenghePay get(String id) {
		return super.get(id);
	}
	
	public List<ZhenghePay> findList(ZhenghePay zhenghePay) {
		return super.findList(zhenghePay);
	}
	
	public Page<ZhenghePay> findPage(Page<ZhenghePay> page, ZhenghePay zhenghePay) {
		return super.findPage(page, zhenghePay);
	}
	
	@Transactional(readOnly = false)
	public void save(ZhenghePay zhenghePay) {
		super.save(zhenghePay);
	}
	
	@Transactional(readOnly = false)
	public void delete(ZhenghePay zhenghePay) {
		super.delete(zhenghePay);
	}
	
}