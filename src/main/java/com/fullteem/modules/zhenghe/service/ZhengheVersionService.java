/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.modules.zhenghe.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fullteem.common.persistence.Page;
import com.fullteem.common.service.CrudService;
import com.fullteem.modules.zhenghe.entity.ZhengheVersion;
import com.fullteem.modules.zhenghe.dao.ZhengheVersionDao;

/**
 * 版本管理Service
 * @author 陈协
 * @version 2016-01-04
 */
@Service
@Transactional(readOnly = true)
public class ZhengheVersionService extends CrudService<ZhengheVersionDao, ZhengheVersion> {
	
	public ZhengheVersion findNewestByOSType(String type){
		return dao.findNewestByOSType(type);
	}

	public ZhengheVersion get(String id) {
		return super.get(id);
	}
	
	public List<ZhengheVersion> findList(ZhengheVersion zhengheVersion) {
		return super.findList(zhengheVersion);
	}
	
	public Page<ZhengheVersion> findPage(Page<ZhengheVersion> page, ZhengheVersion zhengheVersion) {
		return super.findPage(page, zhengheVersion);
	}
	
	@Transactional(readOnly = false)
	public void save(ZhengheVersion zhengheVersion) {
		super.save(zhengheVersion);
	}
	
	@Transactional(readOnly = false)
	public void delete(ZhengheVersion zhengheVersion) {
		super.delete(zhengheVersion);
	}
	
}