/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.modules.zhenghe.dao;

import com.fullteem.common.persistence.CrudDao;
import com.fullteem.common.persistence.annotation.MyBatisDao;
import com.fullteem.modules.zhenghe.entity.ZhengheIdea;

/**
 * 用户反馈DAO接口
 * @author 李启华
 * @version 2015-11-11
 */
@MyBatisDao
public interface ZhengheIdeaDao extends CrudDao<ZhengheIdea> {
	
}