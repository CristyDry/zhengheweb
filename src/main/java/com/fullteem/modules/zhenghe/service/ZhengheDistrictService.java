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
import com.fullteem.modules.zhenghe.entity.ZhengheDistrict;
import com.fullteem.modules.zhenghe.dao.ZhengheDistrictDao;

/**
 * 地区Service
 * @author 李启华
 * @version 2015-11-11
 */
@Service
@Transactional(readOnly = true)
public class ZhengheDistrictService extends CrudService<ZhengheDistrictDao, ZhengheDistrict> {
	@Resource
	private ZhengheDistrictDao districtDao;
	
	public List<ZhengheDistrict> findByCityId(String cityId){
		return districtDao.findByCityId(cityId);
	}

	public ZhengheDistrict get(String id) {
		return super.get(id);
	}
	
	public List<ZhengheDistrict> findList(ZhengheDistrict zhengheDistrict) {
		return super.findList(zhengheDistrict);
	}
	
	public Page<ZhengheDistrict> findPage(Page<ZhengheDistrict> page, ZhengheDistrict zhengheDistrict) {
		return super.findPage(page, zhengheDistrict);
	}
	
	@Transactional(readOnly = false)
	public void save(ZhengheDistrict zhengheDistrict) {
		super.save(zhengheDistrict);
	}
	
	@Transactional(readOnly = false)
	public void delete(ZhengheDistrict zhengheDistrict) {
		super.delete(zhengheDistrict);
	}
	
}