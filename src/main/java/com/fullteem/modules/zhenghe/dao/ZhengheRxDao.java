/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.modules.zhenghe.dao;

import com.fullteem.common.persistence.CrudDao;
import com.fullteem.common.persistence.annotation.MyBatisDao;
import com.fullteem.modules.zhenghe.entity.ZhengheRx;

/**
 * 处方信息DAO接口
 * @author LeVis
 * @version 2017-09-28
 */
@MyBatisDao
public interface ZhengheRxDao extends CrudDao<ZhengheRx> {
	
}