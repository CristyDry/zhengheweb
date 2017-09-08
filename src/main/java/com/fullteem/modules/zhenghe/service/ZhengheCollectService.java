/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.modules.zhenghe.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fullteem.common.persistence.Page;
import com.fullteem.common.service.CrudService;
import com.fullteem.modules.zhenghe.entity.ZhengheCollect;
import com.fullteem.modules.zhenghe.dao.ZhengheCollectDao;

/**
 * 收藏Service
 * @author 李启华
 * @version 2015-11-17
 */
@Service
@Transactional(readOnly = true)
public class ZhengheCollectService extends CrudService<ZhengheCollectDao, ZhengheCollect> {

	@Transactional(readOnly = false)
	public void del(ZhengheCollect zhengheCollect){
		dao.del(zhengheCollect);
	}

	public ZhengheCollect get(String id) {
		return super.get(id);
	}
	
	public List<ZhengheCollect> findList(ZhengheCollect zhengheCollect) {
		return super.findList(zhengheCollect);
	}
	
	public Page<ZhengheCollect> findPage(Page<ZhengheCollect> page, ZhengheCollect zhengheCollect) {
		return super.findPage(page, zhengheCollect);
	}
	
	@Transactional(readOnly = false)
	public void save(ZhengheCollect zhengheCollect) {
		super.save(zhengheCollect);
	}
	
	@Transactional(readOnly = false)
	public void delete(ZhengheCollect zhengheCollect) {
		super.delete(zhengheCollect);
	}
	
}