/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.modules.zhenghe.service;

import java.util.List;
import java.util.Map;

import com.fullteem.modules.zhenghe.entity.DoctorReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fullteem.common.persistence.Page;
import com.fullteem.common.service.CrudService;
import com.fullteem.common.utils.StringUtils;
import com.fullteem.modules.zhenghe.entity.ZhengheRx;
import com.fullteem.modules.zhenghe.dao.ZhengheRxDao;
import com.fullteem.modules.zhenghe.entity.ZhengheRxDetail;
import com.fullteem.modules.zhenghe.dao.ZhengheRxDetailDao;

/**
 * 处方信息Service
 * @author LeVis
 * @version 2017-09-28
 */
@Service
@Transactional(readOnly = true)
public class ZhengheRxService extends CrudService<ZhengheRxDao, ZhengheRx> {

	@Autowired
	private ZhengheRxDetailDao zhengheRxDetailDao;
	
	public ZhengheRx get(String id) {
		ZhengheRx zhengheRx = super.get(id);
		ZhengheRxDetail rxDetail = new ZhengheRxDetail();
		rxDetail.setRxId(id);
		zhengheRx.setZhengheRxDetailList(zhengheRxDetailDao.findList(rxDetail));
		return zhengheRx;
	}
	
	public List<ZhengheRx> findList(ZhengheRx zhengheRx) {
		return super.findList(zhengheRx);
	}
	
	public Page<ZhengheRx> findPage(Page<ZhengheRx> page, ZhengheRx zhengheRx) {
		return super.findPage(page, zhengheRx);
	}
	
	@Transactional(readOnly = false)
	public void save(ZhengheRx zhengheRx) {
		super.save(zhengheRx);
		for (ZhengheRxDetail zhengheRxDetail : zhengheRx.getZhengheRxDetailList()){

			if (ZhengheRxDetail.DEL_FLAG_NORMAL.equals(zhengheRxDetail.getDelFlag())){
				if (StringUtils.isBlank(zhengheRxDetail.getId())){
					zhengheRxDetail.setRxId(zhengheRx.getId());
					zhengheRxDetail.preInsert();
					zhengheRxDetailDao.insert(zhengheRxDetail);
				}else{
					zhengheRxDetail.preUpdate();
					zhengheRxDetailDao.update(zhengheRxDetail);
				}
			}else{
				zhengheRxDetailDao.delete(zhengheRxDetail);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(ZhengheRx zhengheRx) {
		super.delete(zhengheRx);
		zhengheRxDetailDao.delete(new ZhengheRxDetail(zhengheRx.getId()));
	}

	@Transactional(readOnly = true)
	public List<DoctorReport> reportApiDoctor(Map<String,Object> map) {
		return zhengheRxDetailDao.reportApiDoctor(map);
	}

	@Transactional(readOnly = false)
	public List<ZhengheRxDetail> findDetailList(ZhengheRxDetail detail) {
		return zhengheRxDetailDao.findList(detail);
	}
}