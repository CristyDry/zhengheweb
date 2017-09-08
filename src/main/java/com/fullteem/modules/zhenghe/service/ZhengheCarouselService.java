/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.modules.zhenghe.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fullteem.common.persistence.Page;
import com.fullteem.common.service.CrudService;
import com.fullteem.modules.zhenghe.entity.ZhengheCarousel;
import com.fullteem.modules.zhenghe.dao.ZhengheCarouselDao;

/**
 * 轮播图Service
 * @author 李启华
 * @version 2015-11-12
 */
@Service
@Transactional(readOnly = true)
public class ZhengheCarouselService extends CrudService<ZhengheCarouselDao, ZhengheCarousel> {
	
	/*
	 * chenx
	 * 返回前四张文章轮播图
	 */
	public List<ZhengheCarousel> findCarousel(){
		return dao.findCarousel();
	}
	
	/*
	 * liqihua
	 * 查询指定数量的商品类轮播图
	 */
	public List<ZhengheCarousel> findListByNum(Integer num){
		return dao.findListByNum(num);
	}
	
	/*
	 * liqihua
	 * 根据productId找轮播图
	 */
	public List<ZhengheCarousel> findListByProductId(String productId){
		return dao.findListByProductId(productId);
	}

	public ZhengheCarousel get(String id) {
		return super.get(id);
	}
	
	public List<ZhengheCarousel> findList(ZhengheCarousel zhengheCarousel) {
		return super.findList(zhengheCarousel);
	}
	
	public Page<ZhengheCarousel> findPage(Page<ZhengheCarousel> page, ZhengheCarousel zhengheCarousel) {
		return super.findPage(page, zhengheCarousel);
	}
	
	@Transactional(readOnly = false)
	public void save(ZhengheCarousel zhengheCarousel) {
		super.save(zhengheCarousel);
	}
	
	@Transactional(readOnly = false)
	public void delete(ZhengheCarousel zhengheCarousel) {
		super.delete(zhengheCarousel);
	}
	
}