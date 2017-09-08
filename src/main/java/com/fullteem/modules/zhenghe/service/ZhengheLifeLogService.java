/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.modules.zhenghe.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fullteem.common.persistence.Page;
import com.fullteem.common.service.CrudService;
import com.fullteem.modules.zhenghe.entity.ZhengheLifeLog;
import com.fullteem.modules.zhenghe.dao.ZhengheLifeLogDao;

/**
 * 生活日志Service
 * @author 李启华
 * @version 2015-11-19
 */
@Service
@Transactional(readOnly = true)
public class ZhengheLifeLogService extends CrudService<ZhengheLifeLogDao, ZhengheLifeLog> {

	public ZhengheLifeLog get(String id) {
		return super.get(id);
	}
	
	public List<ZhengheLifeLog> findList(ZhengheLifeLog zhengheLifeLog) {
		return super.findList(zhengheLifeLog);
	}
	
	public Page<ZhengheLifeLog> findPage(Page<ZhengheLifeLog> page, ZhengheLifeLog zhengheLifeLog) {
		return super.findPage(page, zhengheLifeLog);
	}
	
	@Transactional(readOnly = false)
	public void save(ZhengheLifeLog zhengheLifeLog) {
		super.save(zhengheLifeLog);
	}
	
	@Transactional(readOnly = false)
	public void delete(ZhengheLifeLog zhengheLifeLog) {
		super.delete(zhengheLifeLog);
	}
	
}