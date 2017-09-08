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
import com.fullteem.modules.zhenghe.entity.PatientZhenghe;
import com.fullteem.modules.zhenghe.entity.ZhenghePatient;
import com.fullteem.modules.zhenghe.dao.ZhenghePatientDao;

/**
 * patientService
 * @author 李启华
 * @version 2015-11-10
 */
@Service
@Transactional(readOnly = true)
public class ZhenghePatientService extends CrudService<ZhenghePatientDao, ZhenghePatient> {
	@Resource
	ZhenghePatientDao patientDao;	
	
	public ZhenghePatient findPatientByAccountNumber(String accountNumber){
		return patientDao.findPatientByAccountNumber(accountNumber);
	}
	
	public List<PatientZhenghe> findPatientByDoctorIdAndKey2(String doctorId,String key){
		return patientDao.findPatientByDoctorIdAndKey2(doctorId,key);
	}
	
	public List<ZhenghePatient> findPatientByDoctorIdAndKey(String doctorId,String key){
		return patientDao.findPatientByDoctorIdAndKey(doctorId,key);
	}
	
	public ZhenghePatient findByPhone(String phone){
		return patientDao.findByPhone(phone);
	}

	public ZhenghePatient get(String id) {
		return super.get(id);
	}
	
	public List<ZhenghePatient> findList(ZhenghePatient zhenghePatient) {
		return super.findList(zhenghePatient);
	}
	
	public Page<ZhenghePatient> findPage(Page<ZhenghePatient> page, ZhenghePatient zhenghePatient) {
		return super.findPage(page, zhenghePatient);
	}
	
	@Transactional(readOnly = false)
	public void save(ZhenghePatient zhenghePatient) {
		super.save(zhenghePatient);
	}
	
	@Transactional(readOnly = false)
	public void delete(ZhenghePatient zhenghePatient) {
		super.delete(zhenghePatient);
	}
	
}