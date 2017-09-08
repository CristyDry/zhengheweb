/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.modules.sys.dao;

import com.fullteem.common.persistence.TreeDao;
import com.fullteem.common.persistence.annotation.MyBatisDao;
import com.fullteem.modules.sys.entity.Office;

/**
 * 机构DAO接口
 * @author Fullteem
 * @version 2014-05-16
 */
@MyBatisDao
public interface OfficeDao extends TreeDao<Office> {
	
}
