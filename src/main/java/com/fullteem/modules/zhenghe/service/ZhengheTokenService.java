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
import com.fullteem.modules.zhenghe.entity.ZhengheToken;
import com.fullteem.modules.zhenghe.dao.ZhengheTokenDao;

/**
 * tokenService
 * @author 李启华
 * @version 2015-11-10
 */
@Service
@Transactional(readOnly = true)
public class ZhengheTokenService extends CrudService<ZhengheTokenDao, ZhengheToken> {
	
	public ZhengheToken findByPatientId(String patientId){
		return dao.findByPatientId(patientId);
	}
	
	public ZhengheToken findByTokenName(String tokenName){
		return dao.findByTokenName(tokenName);
	}
	
	public ZhengheToken findTokenByTokenName(String tokenName){
		return findTokenByKY("tokenName",tokenName);
	}

	public ZhengheToken findTokenByDoctorId(String doctorId){
		return findTokenByKY("doctorId",doctorId);
	}
	
	public ZhengheToken findTokenByKY(String key,String value) {
		ZhengheToken token=new ZhengheToken();
		if("doctorId".equals(key)){
			token.setDoctorId(value);
		}else if("tokenName".equals(key)){
			token.setTokenName(value);
		}
		return dao.findTokenByKY(token);
	}

	public ZhengheToken get(String id) {
		return super.get(id);
	}
	
	public List<ZhengheToken> findList(ZhengheToken zhengheToken) {
		return super.findList(zhengheToken);
	}
	
	public Page<ZhengheToken> findPage(Page<ZhengheToken> page, ZhengheToken zhengheToken) {
		return super.findPage(page, zhengheToken);
	}
	
	@Transactional(readOnly = false)
	public void save(ZhengheToken zhengheToken) {
		super.save(zhengheToken);
	}
	
	@Transactional(readOnly = false)
	public void delete(ZhengheToken zhengheToken) {
		super.delete(zhengheToken);
	}
	
}