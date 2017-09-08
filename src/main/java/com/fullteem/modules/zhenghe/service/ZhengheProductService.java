/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.modules.zhenghe.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fullteem.common.persistence.Page;
import com.fullteem.common.service.CrudService;
import com.fullteem.modules.zhenghe.entity.ZhengheProduct;
import com.fullteem.modules.zhenghe.dao.ZhengheProductDao;

/**
 * 药品Service
 * @author 李启华
 * @version 2015-11-11
 */
@Service
@Transactional(readOnly = true)
public class ZhengheProductService extends CrudService<ZhengheProductDao, ZhengheProduct> {
	
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
	public List<ZhengheProduct> searchProduct(String keys,String classifyId,String priceBetween,String type,String orderBy,Integer pageNo,Integer pageSize){
		return dao.searchProduct(keys, classifyId, priceBetween, type, orderBy,pageNo,pageSize);
	}
	/**
	 * 根据分类id找商品
	 * @author liqihua
	 * @param classifyId
	 * @param priceBetween
	 * @param type
	 * @param orderBy
	 * @return
	 */
	public List<ZhengheProduct> getProductListByCid(String classifyId,String priceBetween,String type,String orderBy,Integer pageNo,Integer pageSize){
		return dao.getProductListByCid(classifyId, priceBetween, type, orderBy,pageNo,pageSize);
	}

	public ZhengheProduct get(String id) {
		return super.get(id);
	}
	
	public List<ZhengheProduct> findList(ZhengheProduct zhengheProduct) {
		return super.findList(zhengheProduct);
	}
	
	public Page<ZhengheProduct> findPage(Page<ZhengheProduct> page, ZhengheProduct zhengheProduct) {
		return super.findPage(page, zhengheProduct);
	}
	
	@Transactional(readOnly = false)
	public void save(ZhengheProduct zhengheProduct) {
		super.save(zhengheProduct);
	}
	
	@Transactional(readOnly = false)
	public void delete(ZhengheProduct zhengheProduct) {
		super.delete(zhengheProduct);
	}
	
}