/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.modules.zhenghe.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fullteem.common.persistence.Page;
import com.fullteem.common.service.CrudService;
import com.fullteem.modules.zhenghe.entity.ZhengheIdea;
import com.fullteem.modules.zhenghe.dao.ZhengheIdeaDao;

/**
 * 用户反馈Service
 * @author 李启华
 * @version 2015-11-11
 */
@Service
@Transactional(readOnly = true)
public class ZhengheIdeaService extends CrudService<ZhengheIdeaDao, ZhengheIdea> {

	public ZhengheIdea get(String id) {
		return super.get(id);
	}
	
	public List<ZhengheIdea> findList(ZhengheIdea zhengheIdea) {
		return super.findList(zhengheIdea);
	}
	
	public Page<ZhengheIdea> findPage(Page<ZhengheIdea> page, ZhengheIdea zhengheIdea) {
		return super.findPage(page, zhengheIdea);
	}
	
	@Transactional(readOnly = false)
	public void save(ZhengheIdea zhengheIdea) {
		super.save(zhengheIdea);
	}
	
	@Transactional(readOnly = false)
	public void delete(ZhengheIdea zhengheIdea) {
		super.delete(zhengheIdea);
	}
	
}