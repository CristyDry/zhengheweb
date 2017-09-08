/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.modules.zhenghe.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fullteem.common.persistence.Page;
import com.fullteem.common.service.CrudService;
import com.fullteem.modules.zhenghe.entity.ZhengheCommonImage;
import com.fullteem.modules.zhenghe.dao.ZhengheCommonImageDao;

/**
 * 图册表Service
 * @author 李启华
 * @version 2015-11-19
 */
@Service
@Transactional(readOnly = true)
public class ZhengheCommonImageService extends CrudService<ZhengheCommonImageDao, ZhengheCommonImage> {
	
	public ZhengheCommonImage findByRelevanceId(String relevanceId){
		return dao.findByRelevanceId(relevanceId);
	}

	@Transactional(readOnly = false)
	public void delByRelevanceId(String relevanceId) {
		dao.delByRelevanceId(relevanceId);
	}
	
	public ZhengheCommonImage get(String id) {
		return super.get(id);
	}
	
	public List<ZhengheCommonImage> findList(ZhengheCommonImage zhengheCommonImage) {
		return super.findList(zhengheCommonImage);
	}
	
	public Page<ZhengheCommonImage> findPage(Page<ZhengheCommonImage> page, ZhengheCommonImage zhengheCommonImage) {
		return super.findPage(page, zhengheCommonImage);
	}
	
	@Transactional(readOnly = false)
	public void save(ZhengheCommonImage zhengheCommonImage) {
		super.save(zhengheCommonImage);
	}
	
	@Transactional(readOnly = false)
	public void delete(ZhengheCommonImage zhengheCommonImage) {
		super.delete(zhengheCommonImage);
	}
	
}