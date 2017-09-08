/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.modules.zhenghe.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fullteem.common.persistence.Page;
import com.fullteem.common.service.CrudService;
import com.fullteem.modules.zhenghe.entity.ZhengheMessage;
import com.fullteem.modules.zhenghe.dao.ZhengheMessageDao;

/**
 * 消息管理Service
 * @author 李启华
 * @version 2015-11-11
 */
@Service
@Transactional(readOnly = true)
public class ZhengheMessageService extends CrudService<ZhengheMessageDao, ZhengheMessage> {
	
	public List<ZhengheMessage> findMessagesByPatientId(String patientId){
		return findMessagesByKY("patientId",patientId);
	}
	
	public List<ZhengheMessage> findMessagesByDoctorId(String doctorId){
		return findMessagesByKY("doctorId",doctorId);
	}
	
	private List<ZhengheMessage> findMessagesByKY(String key,String value){
		ZhengheMessage message = new ZhengheMessage();
		if("doctorId".equals(key)){
			message.setDoctorId(value);
		}else if("patientId".equals(key)){
			message.setPatientId(value);
		}
		return dao.findMessagesByKY(message);
	}

	public ZhengheMessage get(String id) {
		return super.get(id);
	}
	
	public List<ZhengheMessage> findList(ZhengheMessage zhengheMessage) {
		return super.findList(zhengheMessage);
	}
	
	public Page<ZhengheMessage> findPage(Page<ZhengheMessage> page, ZhengheMessage zhengheMessage) {
		return super.findPage(page, zhengheMessage);
	}
	
	@Transactional(readOnly = false)
	public void save(ZhengheMessage zhengheMessage) {
		super.save(zhengheMessage);
	}
	
	@Transactional(readOnly = false)
	public void delete(ZhengheMessage zhengheMessage) {
		super.delete(zhengheMessage);
	}
	
}