/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.modules.zhenghe.dao;

import java.util.List;

import com.fullteem.common.persistence.CrudDao;
import com.fullteem.common.persistence.annotation.MyBatisDao;
import com.fullteem.modules.zhenghe.entity.ZhengheGroup;

/**
 * 分组管理DAO接口
 * @author 陈协
 * @version 2015-11-12
 */
@MyBatisDao
public interface ZhengheGroupDao extends CrudDao<ZhengheGroup> {
	
	List<ZhengheGroup> findGroupByKY(ZhengheGroup group);
	
}