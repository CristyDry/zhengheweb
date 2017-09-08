/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.modules.zhenghe.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fullteem.common.persistence.Page;
import com.fullteem.common.service.CrudService;
import com.fullteem.modules.zhenghe.entity.ZhengheProfession;
import com.fullteem.modules.zhenghe.dao.ZhengheProfessionDao;

/**
 * 医生职称Service
 * @author 李启华
 * @version 2015-11-11
 */
@Service
@Transactional(readOnly = true)
public class ZhengheProfessionService extends CrudService<ZhengheProfessionDao, ZhengheProfession> {

	public ZhengheProfession get(String id) {
		return super.get(id);
	}
	
	public List<ZhengheProfession> findList(ZhengheProfession zhengheProfession) {
		return super.findList(zhengheProfession);
	}
	
	public Page<ZhengheProfession> findPage(Page<ZhengheProfession> page, ZhengheProfession zhengheProfession) {
		return super.findPage(page, zhengheProfession);
	}
	
	@Transactional(readOnly = false)
	public void save(ZhengheProfession zhengheProfession) {
		super.save(zhengheProfession);
	}
	
	@Transactional(readOnly = false)
	public void delete(ZhengheProfession zhengheProfession) {
		super.delete(zhengheProfession);
	}
	
}