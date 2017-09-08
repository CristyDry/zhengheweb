/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.modules.zhenghe.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fullteem.common.persistence.Page;
import com.fullteem.common.service.CrudService;
import com.fullteem.modules.zhenghe.entity.ZhengheArticleClassify;
import com.fullteem.modules.zhenghe.dao.ZhengheArticleClassifyDao;

/**
 * 知识频道Service
 * @author 李启华
 * @version 2015-11-12
 */
@Service
@Transactional(readOnly = true)
public class ZhengheArticleClassifyService extends CrudService<ZhengheArticleClassifyDao, ZhengheArticleClassify> {
	
	public ZhengheArticleClassify get(String id) {
		return super.get(id);
	}
	
	public List<ZhengheArticleClassify> findList(ZhengheArticleClassify zhengheArticleClassify) {
		return super.findList(zhengheArticleClassify);
	}
	
	public Page<ZhengheArticleClassify> findPage(Page<ZhengheArticleClassify> page, ZhengheArticleClassify zhengheArticleClassify) {
		return super.findPage(page, zhengheArticleClassify);
	}
	
	@Transactional(readOnly = false)
	public void save(ZhengheArticleClassify zhengheArticleClassify) {
		super.save(zhengheArticleClassify);
	}
	
	@Transactional(readOnly = false)
	public void delete(ZhengheArticleClassify zhengheArticleClassify) {
		super.delete(zhengheArticleClassify);
	}
	
}