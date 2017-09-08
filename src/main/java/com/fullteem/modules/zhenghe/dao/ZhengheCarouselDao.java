/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.modules.zhenghe.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fullteem.common.persistence.CrudDao;
import com.fullteem.common.persistence.annotation.MyBatisDao;
import com.fullteem.modules.zhenghe.entity.ZhengheCarousel;

/**
 * 轮播图DAO接口
 * @author 李启华
 * @version 2015-11-12
 */
@MyBatisDao
public interface ZhengheCarouselDao extends CrudDao<ZhengheCarousel> {
	/*
	 * liqihua
	 * 查询指定数量的商品类轮播图
	 */
	List<ZhengheCarousel> findListByNum(Integer num);
	
	/*
	 * liqihua
	 * 根据productId查询轮播图
	 */
	List<ZhengheCarousel> findListByProductId(@Param("productId")String productId);
	/*
	 * chenx
	 * 返回前四张文章轮播图
	 */
	List<ZhengheCarousel> findCarousel();
}