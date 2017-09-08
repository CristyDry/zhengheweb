/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.modules.zhenghe.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fullteem.common.persistence.Page;
import com.fullteem.common.service.CrudService;
import com.fullteem.modules.zhenghe.entity.ZhengheHospital;
import com.fullteem.modules.zhenghe.dao.ZhengheHospitalDao;

/**
 * 医院Service
 * @author 李启华
 * @version 2015-11-12
 */
@Service
@Transactional(readOnly = true)
public class ZhengheHospitalService extends CrudService<ZhengheHospitalDao, ZhengheHospital> {
	
	public List<ZhengheHospital> findHospitalByHospitalName(String hospitalName){
		return findHospitalByKY("hospitalName",'%'+hospitalName+'%');
	}
	
	public List<ZhengheHospital> findHospitalByProvincialId(String provincialId){
		return findHospitalByKY("provincialId",provincialId);
	}
	
	public List<ZhengheHospital> findHospitalByCityId(String cityId){
		return findHospitalByKY("cityId",cityId);
	}
	
	private List<ZhengheHospital> findHospitalByKY(String key,String value){
		ZhengheHospital hospital = new ZhengheHospital();
		if("cityId".equals(key)){
			hospital.setCityId(value);
		}else if("provincialId".equals(key)){
			hospital.setProvincialId(value);
		}else if("hospitalName".equals(key)){
			hospital.setHospitalName(value);
		}
		return dao.findHospitalByKY(hospital);
	}

	public ZhengheHospital get(String id) {
		return super.get(id);
	}
	
	public List<ZhengheHospital> findList(ZhengheHospital zhengheHospital) {
		return super.findList(zhengheHospital);
	}
	
	public Page<ZhengheHospital> findPage(Page<ZhengheHospital> page, ZhengheHospital zhengheHospital) {
		return super.findPage(page, zhengheHospital);
	}
	
	@Transactional(readOnly = false)
	public void save(ZhengheHospital zhengheHospital) {
		super.save(zhengheHospital);
	}
	
	@Transactional(readOnly = false)
	public void delete(ZhengheHospital zhengheHospital) {
		super.delete(zhengheHospital);
	}
	
}