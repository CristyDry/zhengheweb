/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.modules.zhenghe.dao;

import java.util.List;
import java.util.Map;

import com.fullteem.common.persistence.CrudDao;
import com.fullteem.common.persistence.annotation.MyBatisDao;
import com.fullteem.modules.zhenghe.entity.DoctorZhenghe;
import com.fullteem.modules.zhenghe.entity.ZhengheDoctor;

/**
 * 医生管理DAO接口
 * @author 李启华
 * @version 2015-11-11
 */
@MyBatisDao
public interface ZhengheDoctorDao extends CrudDao<ZhengheDoctor> {
	
	List<ZhengheDoctor> findDoctorByTypeAndTop3(ZhengheDoctor doctor);
	
	List<DoctorZhenghe> findDoctorByCriteria(Map<String,String> map);

	ZhengheDoctor findDoctorByKY(ZhengheDoctor doctor);
	
}