/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.modules.zhenghe.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fullteem.common.persistence.CrudDao;
import com.fullteem.common.persistence.annotation.MyBatisDao;
import com.fullteem.modules.zhenghe.entity.ZhengheProduct;

/**
 * 药品DAO接口
 * @author 李启华
 * @version 2015-11-11
 */
@MyBatisDao
public interface ZhengheProductDao extends CrudDao<ZhengheProduct> {
	/**
	 * 搜索商品
	 * @author liqihua
	 * @param keys
	 * @param classifyId
	 * @param priceBetween
	 * @param type
	 * @param orderBy
	 * @return
	 */
	List<ZhengheProduct> searchProduct(@Param("keys")String keys,
			@Param("classifyId")String classifyId,
			@Param("priceBetween")String priceBetween,
			@Param("type")String type,
			@Param("orderBy")String orderBy,
			@Param("pageNo") Integer pageNo,
			@Param("pageSize") Integer pageSize);
	
	/**
	 * 根据分类id找商品
	 * @author liqihua
	 * @param classifyId
	 * @param priceBetween
	 * @param type
	 * @param orderBy
	 * @return
	 */
	List<ZhengheProduct> getProductListByCid(@Param("classifyId")String classifyId,
			@Param("priceBetween")String priceBetween,
			@Param("type")String type,
			@Param("orderBy")String orderBy,
			@Param("pageNo") Integer pageNo,
			@Param("pageSize") Integer pageSize);
}