/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.modules.zhenghe.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fullteem.common.persistence.Page;
import com.fullteem.common.service.CrudService;
import com.fullteem.modules.zhenghe.entity.ZhengheHeartRate;
import com.fullteem.modules.zhenghe.dao.ZhengheHeartRateDao;

/**
 * 心率Service
 * @author 李启华
 * @version 2015-11-18
 */
@Service
@Transactional(readOnly = true)
public class ZhengheHeartRateService extends CrudService<ZhengheHeartRateDao, ZhengheHeartRate> {

	public ZhengheHeartRate get(String id) {
		return super.get(id);
	}
	
	public List<ZhengheHeartRate> findList(ZhengheHeartRate zhengheHeartRate) {
		return super.findList(zhengheHeartRate);
	}
	
	public Page<ZhengheHeartRate> findPage(Page<ZhengheHeartRate> page, ZhengheHeartRate zhengheHeartRate) {
		return super.findPage(page, zhengheHeartRate);
	}
	
	@Transactional(readOnly = false)
	public void save(ZhengheHeartRate zhengheHeartRate) {
		super.save(zhengheHeartRate);
	}
	
	@Transactional(readOnly = false)
	public void delete(ZhengheHeartRate zhengheHeartRate) {
		super.delete(zhengheHeartRate);
	}
	
}