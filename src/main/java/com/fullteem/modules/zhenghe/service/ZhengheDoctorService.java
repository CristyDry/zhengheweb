/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.modules.zhenghe.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fullteem.common.persistence.Page;
import com.fullteem.common.service.CrudService;
import com.fullteem.modules.zhenghe.entity.DoctorZhenghe;
import com.fullteem.modules.zhenghe.entity.ZhengheDoctor;
import com.fullteem.modules.zhenghe.dao.ZhengheDoctorDao;

/**
 * 医生管理Service
 * @author 李启华
 * @version 2015-11-11
 */
@Service
@Transactional(readOnly = true)
public class ZhengheDoctorService extends CrudService<ZhengheDoctorDao, ZhengheDoctor> {
	
	public List<ZhengheDoctor> findDoctorByTypeAndTop3(ZhengheDoctor doctor){
		return dao.findDoctorByTypeAndTop3(doctor);
	}
	
	public List<ZhengheDoctor> findAll(){
		return dao.findAllList(null);
	}
	
	public List<DoctorZhenghe> findDoctorByCriteria(Map<String,String> map){
		return dao.findDoctorByCriteria(map);
	}
	
	public List<ZhengheDoctor> findDoctorByType(String type){
		return findDoctorsByKY("type", type);
	}
	
	public List<ZhengheDoctor> findDoctorByDepartmentsId(String departmentsId){
		return findDoctorsByKY("departmentsId",departmentsId);
	}
	
	public List<ZhengheDoctor> findDoctorByHospitalId(String hospitalId){
		return findDoctorsByKY("hospitalId",hospitalId);
	}
	
	public List<ZhengheDoctor> findDoctorByName(String doctorName){
		return findDoctorsByKY("doctorName", doctorName);
	}
	
	public ZhengheDoctor findDoctorByPhone(String phone){
		return findDoctorByKY("phone",phone);
	}
	
	private List<ZhengheDoctor> findDoctorsByKY(String key,String value){
		ZhengheDoctor doctor=new ZhengheDoctor();
		if("doctorName".equals(key)){
			doctor.setDoctorName(value);
		}else if("hospitalId".equals(key)){
			doctor.setHospitalId(value);
		}else if("departmentsId".equals(key)){
			doctor.setDepartmentsId(value);
		}else if("type".equals(key)){
			doctor.setType(value);
		}
		return dao.findList(doctor);
	}
	
	private ZhengheDoctor findDoctorByKY(String key,String value){
		ZhengheDoctor doctor=new ZhengheDoctor();
		if("phone".equals(key)){
			doctor.setPhone(value);
		}
		return dao.findDoctorByKY(doctor);
	}
	
	public ZhengheDoctor get(String id) {
		return super.get(id);
	}
	
	public List<ZhengheDoctor> findList(ZhengheDoctor zhengheDoctor) {
		return super.findList(zhengheDoctor);
	}
	
	public Page<ZhengheDoctor> findPage(Page<ZhengheDoctor> page, ZhengheDoctor zhengheDoctor) {
		return super.findPage(page, zhengheDoctor);
	}
	
	@Transactional(readOnly = false)
	public void save(ZhengheDoctor zhengheDoctor) {
		super.save(zhengheDoctor);
	}
	
	@Transactional(readOnly = false)
	public void delete(ZhengheDoctor zhengheDoctor) {
		super.delete(zhengheDoctor);
	}
	
	
}