/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.modules.zhenghe.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fullteem.common.persistence.CrudDao;
import com.fullteem.common.persistence.annotation.MyBatisDao;
import com.fullteem.modules.zhenghe.entity.PatientZhenghe;
import com.fullteem.modules.zhenghe.entity.ZhenghePatient;

/**
 * patientDAO接口
 * @author 李启华
 * @version 2015-11-16
 */
@MyBatisDao
public interface ZhenghePatientDao extends CrudDao<ZhenghePatient> {
	ZhenghePatient findPatientByAccountNumber(String accountNumber);
	List<PatientZhenghe> findPatientByDoctorIdAndKey2(@Param("doctorId")String doctorId,@Param("key")String key);
	List<ZhenghePatient> findPatientByDoctorIdAndKey(@Param("doctorId")String doctorId,@Param("key")String key);
	ZhenghePatient findByPhone(String phone);
}