/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.modules.zhenghe.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fullteem.common.persistence.Page;
import com.fullteem.common.service.CrudService;
import com.fullteem.modules.zhenghe.entity.ZhengheGroup;
import com.fullteem.modules.zhenghe.dao.ZhengheGroupDao;

/**
 * 分组管理Service
 * @author 陈协
 * @version 2015-11-12
 */
@Service
@Transactional(readOnly = true)
public class ZhengheGroupService extends CrudService<ZhengheGroupDao, ZhengheGroup> {
	
	public List<ZhengheGroup> findGroupByDoctorId(String doctorId){
		return findGroupByKY("doctorId",doctorId);
	}
	
	private List<ZhengheGroup> findGroupByKY(String key,String value){
		ZhengheGroup group = new ZhengheGroup();
		if("doctorId".equals(key)){
			group.setDoctorId(value);
		}
		return dao.findGroupByKY(group);
	}

	public ZhengheGroup get(String id) {
		return super.get(id);
	}
	
	public List<ZhengheGroup> findList(ZhengheGroup zhengheGroup) {
		return super.findList(zhengheGroup);
	}
	
	public Page<ZhengheGroup> findPage(Page<ZhengheGroup> page, ZhengheGroup zhengheGroup) {
		return super.findPage(page, zhengheGroup);
	}
	
	@Transactional(readOnly = false)
	public void save(ZhengheGroup zhengheGroup) {
		super.save(zhengheGroup);
	}
	
	@Transactional(readOnly = false)
	public void delete(ZhengheGroup zhengheGroup) {
		super.delete(zhengheGroup);
	}
	
}