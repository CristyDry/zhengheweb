/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.modules.zhenghe.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fullteem.common.persistence.Page;
import com.fullteem.common.service.CrudService;
import com.fullteem.modules.zhenghe.entity.BigDataZhenghe;
import com.fullteem.modules.zhenghe.entity.ZhengheOrderItem;
import com.fullteem.modules.zhenghe.dao.ZhengheOrderItemDao;

/**
 * 订单项Service
 * @author 李启华
 * @version 2015-11-17
 */
@Service
@Transactional(readOnly = true)
public class ZhengheOrderItemService extends CrudService<ZhengheOrderItemDao, ZhengheOrderItem> {
	
	public List<BigDataZhenghe> getSum() throws ParseException{
        
        List<BigDataZhenghe> bigDataList = new ArrayList<BigDataZhenghe>();
        
        List<String> dateList = getMonthDate();
        
        for(String d:dateList){
        	BigDataZhenghe bigData = dao.getSum(d);
        	if(bigData!=null){
        		bigDataList.add(bigData);
        	}else{
        		bigData = new BigDataZhenghe();
        		bigData.setX(d);
        		bigData.setY(0);
        		bigDataList.add(bigData);
        	}
        }
        
        return bigDataList;
	}
	
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述: 获取当前月份的全部日期</br>
	 * 开发人员：chenx</br>
	 * 创建时间：2016年1月15日</br>
	 * @return
	 */
	private List<String> getMonthDate(){

		List<String> dateList = new ArrayList<String>();
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
        
        //获取当前月第一天：
        Calendar c = Calendar.getInstance();    
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
        String first = format.format(c.getTime());

        //获取当前月最后一天
        Calendar ca = Calendar.getInstance();    
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));  
        String last = format.format(ca.getTime());
        
        while(!first.equals(last)){
        	dateList.add(first);
	        try {
				c.setTime(format.parse(first));  //设置当前日期  
			} catch (ParseException e) {
				e.printStackTrace();
			}   
	        c.add(Calendar.DATE, 1); //日期加1天  
	        //c.add(Calendar.DATE, -1); //日期减1天  
	        first = format.format(c.getTime());
        }
        dateList.add(last);
        
        return dateList;
	}
	
	public List<BigDataZhenghe> getHot(){
		return dao.getHot();
	}

	public List<BigDataZhenghe> getSales(){
		return dao.getSales();
	}
	
	public ZhengheOrderItem get(String id) {
		return super.get(id);
	}
	
	public List<ZhengheOrderItem> findList(ZhengheOrderItem zhengheOrderItem) {
		return super.findList(zhengheOrderItem);
	}
	
	public Page<ZhengheOrderItem> findPage(Page<ZhengheOrderItem> page, ZhengheOrderItem zhengheOrderItem) {
		return super.findPage(page, zhengheOrderItem);
	}
	
	@Transactional(readOnly = false)
	public void save(ZhengheOrderItem zhengheOrderItem) {
		super.save(zhengheOrderItem);
	}
	
	@Transactional(readOnly = false)
	public void delete(ZhengheOrderItem zhengheOrderItem) {
		super.delete(zhengheOrderItem);
	}
	
}