/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.modules.zhenghe.dao;

import java.util.List;

import com.fullteem.common.persistence.CrudDao;
import com.fullteem.common.persistence.annotation.MyBatisDao;
import com.fullteem.modules.zhenghe.entity.BigDataZhenghe;
import com.fullteem.modules.zhenghe.entity.ZhengheOrderItem;

/**
 * 订单项DAO接口
 * @author 李启华
 * @version 2015-11-17
 */
@MyBatisDao
public interface ZhengheOrderItemDao extends CrudDao<ZhengheOrderItem> {
	
	BigDataZhenghe getSum(String date);
	
	List<BigDataZhenghe> getHot();
	
	List<BigDataZhenghe> getSales();
	
}