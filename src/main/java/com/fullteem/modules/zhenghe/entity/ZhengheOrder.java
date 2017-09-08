/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.modules.zhenghe.entity;

import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.List;

import com.fullteem.common.persistence.DataEntity;

/**
 * 订单表Entity
 * @author 李启华
 * @version 2015-11-18
 */
public class ZhengheOrder extends DataEntity<ZhengheOrder> {
	
	private static final long serialVersionUID = 1L;
	private String name;				// 收件人姓名
	private String phone;				// 收件人手机号码
	private String payId;				// 支付id
	private String parentOrderNo;		// 订单号
	private String totalAmount;			// 订单金额
	private String patientId;			// 患者id
	private String status;				// 1待支付2支付完成待收货3完成4取消
	private String expressName;			// 快递公司名称
	private String expressNo;			// 递快单号
	private String address;				// 送货地址
	private String updateId;			// 修改记录id预留
	private String standby1;			// 备用字段1
	private String deleteMark;			// 删除标记
	private String remark;				// 备注
	private String mender;				// 修改者
	private String creator;				// 创建者
	private Date beginCreateDate;		// 开始 下单时间
	private Date endCreateDate;			// 结束 下单时间
	
	private List<ZhengheOrderItem> items;		// 订单项
	
	public ZhengheOrder() {
		super();
	}

	public ZhengheOrder(String id){
		super(id);
	}

	@Length(min=1, max=255, message="收件人姓名长度必须介于 1 和 255 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=1, max=255, message="收件人手机号码长度必须介于 1 和 255 之间")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Length(min=0, max=64, message="支付id长度必须介于 0 和 64 之间")
	public String getPayId() {
		return payId;
	}

	public void setPayId(String payId) {
		this.payId = payId;
	}
	
	@Length(min=1, max=255, message="订单号长度必须介于 1 和 255 之间")
	public String getParentOrderNo() {
		return parentOrderNo;
	}

	public void setParentOrderNo(String parentOrderNo) {
		this.parentOrderNo = parentOrderNo;
	}
	
	@Length(min=1, max=10, message="订单金额长度必须介于 1 和 10 之间")
	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	@Length(min=1, max=64, message="患者id长度必须介于 1 和 64 之间")
	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	
	@Length(min=1, max=2, message="1待支付2支付完成待收货3完成4取消长度必须介于 1 和 2 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=255, message="快递公司名称长度必须介于 0 和 255 之间")
	public String getExpressName() {
		return expressName;
	}

	public void setExpressName(String expressName) {
		this.expressName = expressName;
	}
	
	@Length(min=0, max=255, message="递快单号长度必须介于 0 和 255 之间")
	public String getExpressNo() {
		return expressNo;
	}

	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}
	
	@Length(min=1, max=255, message="送货地址长度必须介于 1 和 255 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Length(min=0, max=64, message="修改记录id预留长度必须介于 0 和 64 之间")
	public String getUpdateId() {
		return updateId;
	}

	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}
	
	@Length(min=0, max=255, message="备用字段1长度必须介于 0 和 255 之间")
	public String getStandby1() {
		return standby1;
	}

	public void setStandby1(String standby1) {
		this.standby1 = standby1;
	}
	
	@Length(min=0, max=32, message="删除标记长度必须介于 0 和 32 之间")
	public String getDeleteMark() {
		return deleteMark;
	}

	public void setDeleteMark(String deleteMark) {
		this.deleteMark = deleteMark;
	}
	
	@Length(min=0, max=255, message="备注长度必须介于 0 和 255 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Length(min=0, max=32, message="修改者长度必须介于 0 和 32 之间")
	public String getMender() {
		return mender;
	}

	public void setMender(String mender) {
		this.mender = mender;
	}
	
	@Length(min=0, max=32, message="创建者长度必须介于 0 和 32 之间")
	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}
	
	public Date getBeginCreateDate() {
		return beginCreateDate;
	}

	public void setBeginCreateDate(Date beginCreateDate) {
		this.beginCreateDate = beginCreateDate;
	}
	
	public Date getEndCreateDate() {
		return endCreateDate;
	}

	public void setEndCreateDate(Date endCreateDate) {
		this.endCreateDate = endCreateDate;
	}

	public List<ZhengheOrderItem> getItems() {
		return items;
	}

	public void setItems(List<ZhengheOrderItem> items) {
		this.items = items;
	}
		
}