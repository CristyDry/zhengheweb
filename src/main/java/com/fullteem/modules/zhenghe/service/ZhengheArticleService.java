/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.modules.zhenghe.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fullteem.common.persistence.Page;
import com.fullteem.common.service.CrudService;
import com.fullteem.modules.zhenghe.entity.ZhengheArticle;
import com.fullteem.modules.zhenghe.dao.ZhengheArticleDao;

/**
 * 知识文章Service
 * @author 李启华
 * @version 2015-11-12
 */
@Service
@Transactional(readOnly = true)
public class ZhengheArticleService extends CrudService<ZhengheArticleDao, ZhengheArticle> {
	
	public List<ZhengheArticle> findArticleByClassifyId(String classifyId){
		return findArticleByKY("classifyId",classifyId);
	}
	
	private List<ZhengheArticle> findArticleByKY(String key,String value){
		ZhengheArticle article = new ZhengheArticle();
		if("classifyId".equals(key)){
			article.setClassifyId(value);
		}
		return dao.findList(article);
	}

	public ZhengheArticle get(String id) {
		return super.get(id);
	}
	
	public List<ZhengheArticle> findList(ZhengheArticle zhengheArticle) {
		return super.findList(zhengheArticle);
	}
	
	public Page<ZhengheArticle> findPage(Page<ZhengheArticle> page, ZhengheArticle zhengheArticle) {
		return super.findPage(page, zhengheArticle);
	}
	
	@Transactional(readOnly = false)
	public void save(ZhengheArticle zhengheArticle) {
		super.save(zhengheArticle);
	}
	
	@Transactional(readOnly = false)
	public void delete(ZhengheArticle zhengheArticle) {
		super.delete(zhengheArticle);
	}
	
}