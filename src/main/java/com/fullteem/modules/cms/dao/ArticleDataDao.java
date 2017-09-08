/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.modules.cms.dao;

import com.fullteem.common.persistence.CrudDao;
import com.fullteem.common.persistence.annotation.MyBatisDao;
import com.fullteem.modules.cms.entity.ArticleData;

/**
 * 文章DAO接口
 * @author Fullteem
 * @version 2013-8-23
 */
@MyBatisDao
public interface ArticleDataDao extends CrudDao<ArticleData> {
	
}
