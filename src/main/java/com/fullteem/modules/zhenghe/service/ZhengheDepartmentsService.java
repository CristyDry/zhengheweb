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
import com.fullteem.modules.zhenghe.entity.ZhengheDepartments;
import com.fullteem.modules.zhenghe.dao.ZhengheDepartmentsDao;

/**
 * 科室Service
 * @author 李启华
 * @version 2015-11-11
 */
@Service
@Transactional(readOnly = true)
public class ZhengheDepartmentsService extends CrudService<ZhengheDepartmentsDao, ZhengheDepartments> {
	
	public List<ZhengheDepartments> findBySDepartmentsId(String sDepartmentsId){
		return findByKY("sDepartmentsId",sDepartmentsId);
	}
	
	public List<ZhengheDepartments> findDepartmentsByName(ZhengheDepartments departments){
		return dao.findDepartmentsByName(departments);
	}
	
	public List<ZhengheDepartments> findByType(String type){
		return findByKY("type",type);
	}
	
	public List<ZhengheDepartments> findByKY(String key,String value){
		ZhengheDepartments departments = new ZhengheDepartments();
		if("type".equals(key)){
			departments.setType(value);
		}else if("sDepartmentsId".equals(key)){
			departments.setSDepartmentsId(value);
		}
		return dao.findList(departments);
	}
	
	public ZhengheDepartments get(String id) {
		return super.get(id);
	}
	
	public List<ZhengheDepartments> findList(ZhengheDepartments zhengheDepartments) {
		return super.findList(zhengheDepartments);
	}
	
	public Page<ZhengheDepartments> findPage(Page<ZhengheDepartments> page, ZhengheDepartments zhengheDepartments) {
		return super.findPage(page, zhengheDepartments);
	}
	
	@Transactional(readOnly = false)
	public void save(ZhengheDepartments zhengheDepartments) {
		super.save(zhengheDepartments);
	}
	
	@Transactional(readOnly = false)
	public void delete(ZhengheDepartments zhengheDepartments) {
		super.delete(zhengheDepartments);
	}
	
	
	
}