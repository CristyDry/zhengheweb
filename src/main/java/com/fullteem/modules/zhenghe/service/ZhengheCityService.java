/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.modules.zhenghe.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fullteem.common.persistence.Page;
import com.fullteem.common.service.CrudService;
import com.fullteem.modules.zhenghe.entity.ZhengheCity;
import com.fullteem.modules.zhenghe.dao.ZhengheCityDao;

/**
 * 城市Service
 * @author 李启华
 * @version 2015-11-11
 */
@Service
@Transactional(readOnly = true)
public class ZhengheCityService extends CrudService<ZhengheCityDao, ZhengheCity> {
	@Resource
	private ZhengheCityDao cityDao;
	
	public List<ZhengheCity> findByProvinceId(String provinceId){
		return cityDao.findByProvinceId(provinceId);
	}

	public ZhengheCity get(String id) {
		return super.get(id);
	}
	
	public List<ZhengheCity> findList(ZhengheCity zhengheCity) {
		return super.findList(zhengheCity);
	}
	
	public Page<ZhengheCity> findPage(Page<ZhengheCity> page, ZhengheCity zhengheCity) {
		return super.findPage(page, zhengheCity);
	}
	
	@Transactional(readOnly = false)
	public void save(ZhengheCity zhengheCity) {
		super.save(zhengheCity);
	}
	
	@Transactional(readOnly = false)
	public void delete(ZhengheCity zhengheCity) {
		super.delete(zhengheCity);
	}
	
}