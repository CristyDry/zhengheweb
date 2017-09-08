/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.modules.zhenghe.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fullteem.common.persistence.Page;
import com.fullteem.common.service.CrudService;
import com.fullteem.modules.zhenghe.entity.ZhengheProductClassify;
import com.fullteem.modules.zhenghe.dao.ZhengheProductClassifyDao;

/**
 * 药品分类Service
 * @author 李启华
 * @version 2015-11-11
 */
@Service
@Transactional(readOnly = true)
public class ZhengheProductClassifyService extends CrudService<ZhengheProductClassifyDao, ZhengheProductClassify> {

	public ZhengheProductClassify get(String id) {
		return super.get(id);
	}
	
	public List<ZhengheProductClassify> findList(ZhengheProductClassify zhengheProductClassify) {
		return super.findList(zhengheProductClassify);
	}
	
	public Page<ZhengheProductClassify> findPage(Page<ZhengheProductClassify> page, ZhengheProductClassify zhengheProductClassify) {
		return super.findPage(page, zhengheProductClassify);
	}
	
	@Transactional(readOnly = false)
	public void save(ZhengheProductClassify zhengheProductClassify) {
		super.save(zhengheProductClassify);
	}
	
	@Transactional(readOnly = false)
	public void delete(ZhengheProductClassify zhengheProductClassify) {
		super.delete(zhengheProductClassify);
	}
	
}