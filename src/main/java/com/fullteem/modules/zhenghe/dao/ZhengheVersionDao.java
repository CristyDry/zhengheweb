/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.modules.zhenghe.dao;

import com.fullteem.common.persistence.CrudDao;
import com.fullteem.common.persistence.annotation.MyBatisDao;
import com.fullteem.modules.zhenghe.entity.ZhengheVersion;

/**
 * 版本管理DAO接口
 * @author 陈协
 * @version 2016-01-04
 */
@MyBatisDao
public interface ZhengheVersionDao extends CrudDao<ZhengheVersion> {
	
	ZhengheVersion findNewestByOSType(String type);
	
}