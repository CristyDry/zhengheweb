/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.test.dao;

import com.fullteem.common.persistence.TreeDao;
import com.fullteem.common.persistence.annotation.MyBatisDao;
import com.fullteem.test.entity.TestTree;

/**
 * 树结构生成DAO接口
 * @author Fullteem
 * @version 2015-04-06
 */
@MyBatisDao
public interface TestTreeDao extends TreeDao<TestTree> {
	
}