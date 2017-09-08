/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.modules.zhenghe.dao;

import java.util.List;

import com.fullteem.common.persistence.CrudDao;
import com.fullteem.common.persistence.annotation.MyBatisDao;
import com.fullteem.modules.zhenghe.entity.ZhengheToken;

/**
 * tokenDAO接口
 * @author 李启华
 * @version 2015-11-10
 */
@MyBatisDao
public interface ZhengheTokenDao extends CrudDao<ZhengheToken> {
	ZhengheToken findByPatientId(String patientId);
	ZhengheToken findByTokenName(String tokenName);
	ZhengheToken findTokenByKY(ZhengheToken token);
}