/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.modules.zhenghe.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fullteem.common.persistence.Page;
import com.fullteem.common.service.CrudService;
import com.fullteem.modules.zhenghe.entity.ZhengheCourseDisease;
import com.fullteem.modules.zhenghe.dao.ZhengheCourseDiseaseDao;

/**
 * 病程Service
 * @author 李启华
 * @version 2015-11-18
 */
@Service
@Transactional(readOnly = true)
public class ZhengheCourseDiseaseService extends CrudService<ZhengheCourseDiseaseDao, ZhengheCourseDisease> {

	public ZhengheCourseDisease get(String id) {
		return super.get(id);
	}
	
	public List<ZhengheCourseDisease> findList(ZhengheCourseDisease zhengheCourseDisease) {
		return super.findList(zhengheCourseDisease);
	}
	
	public Page<ZhengheCourseDisease> findPage(Page<ZhengheCourseDisease> page, ZhengheCourseDisease zhengheCourseDisease) {
		return super.findPage(page, zhengheCourseDisease);
	}
	
	@Transactional(readOnly = false)
	public void save(ZhengheCourseDisease zhengheCourseDisease) {
		super.save(zhengheCourseDisease);
	}
	
	@Transactional(readOnly = false)
	public void delete(ZhengheCourseDisease zhengheCourseDisease) {
		super.delete(zhengheCourseDisease);
	}
	
}