/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.modules.zhenghe.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fullteem.common.persistence.Page;
import com.fullteem.common.service.CrudService;
import com.fullteem.modules.zhenghe.entity.ZhengheMedicalHistory;
import com.fullteem.modules.zhenghe.dao.ZhengheMedicalHistoryDao;

/**
 * 病历表Service
 * @author 李启华
 * @version 2015-11-18
 */
@Service
@Transactional(readOnly = true)
public class ZhengheMedicalHistoryService extends CrudService<ZhengheMedicalHistoryDao, ZhengheMedicalHistory> {

	public ZhengheMedicalHistory get(String id) {
		return super.get(id);
	}
	
	public List<ZhengheMedicalHistory> findList(ZhengheMedicalHistory zhengheMedicalHistory) {
		return super.findList(zhengheMedicalHistory);
	}
	
	public Page<ZhengheMedicalHistory> findPage(Page<ZhengheMedicalHistory> page, ZhengheMedicalHistory zhengheMedicalHistory) {
		return super.findPage(page, zhengheMedicalHistory);
	}
	
	@Transactional(readOnly = false)
	public void save(ZhengheMedicalHistory zhengheMedicalHistory) {
		super.save(zhengheMedicalHistory);
	}
	
	@Transactional(readOnly = false)
	public void delete(ZhengheMedicalHistory zhengheMedicalHistory) {
		super.delete(zhengheMedicalHistory);
	}
	
}