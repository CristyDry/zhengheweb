/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.modules.zhenghe.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fullteem.common.persistence.Page;
import com.fullteem.common.service.CrudService;
import com.fullteem.modules.zhenghe.entity.ZhengheAddress;
import com.fullteem.modules.zhenghe.dao.ZhengheAddressDao;

/**
 * 地址表Service
 * @author 李启华
 * @version 2015-11-18
 */
@Service
@Transactional(readOnly = true)
public class ZhengheAddressService extends CrudService<ZhengheAddressDao, ZhengheAddress> {

	public ZhengheAddress get(String id) {
		return super.get(id);
	}
	
	public List<ZhengheAddress> findList(ZhengheAddress zhengheAddress) {
		return super.findList(zhengheAddress);
	}
	
	public Page<ZhengheAddress> findPage(Page<ZhengheAddress> page, ZhengheAddress zhengheAddress) {
		return super.findPage(page, zhengheAddress);
	}
	
	@Transactional(readOnly = false)
	public void save(ZhengheAddress zhengheAddress) {
		super.save(zhengheAddress);
	}
	
	@Transactional(readOnly = false)
	public void delete(ZhengheAddress zhengheAddress) {
		super.delete(zhengheAddress);
	}
	
}