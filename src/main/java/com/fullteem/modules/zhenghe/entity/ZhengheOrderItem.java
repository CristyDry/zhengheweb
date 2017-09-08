/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.modules.zhenghe.entity;

import org.hibernate.validator.constraints.Length;

import com.fullteem.common.persistence.DataEntity;

/**
 * 订单项Entity
 * @author 李启华
 * @version 2015-11-17
 */
public class ZhengheOrderItem extends DataEntity<ZhengheOrderItem> {
	
	private static final long serialVersionUID = 1L;
	private String productId;		// 商品id
	private String orderId;			// 订单id
	private String orderItemNo;		// 子订单号
	private String productName;		// 商品名称
	private String originalPrice;	// 商品原价格
	private String realityPrice;	// 商品实际购买价格
	private String sumPrice;		// 合计购买价格
	private String count;			// 购买份数
	private String description;		// 描述
	private String doctorId;		// 推荐医生id
	private String standby1;		// 备用字段1
	private String standby2;		// 备用字段2
	private String deleteMark;		// 删除标记
	private String remark;			// 备注
	private String mender;			// 修改者
	private String creator;			// 创建者
	
	private String productPic;		//商品图片
	private String standard;		//规格
	
	public ZhengheOrderItem() {
		super();
	}

	public ZhengheOrderItem(String id){
		super(id);
	}

	@Length(min=1, max=64, message="商品id长度必须介于 1 和 64 之间")
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	@Length(min=1, max=64, message="订单id长度必须介于 1 和 64 之间")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	@Length(min=1, max=255, message="子订单号长度必须介于 1 和 255 之间")
	public String getOrderItemNo() {
		return orderItemNo;
	}

	public void setOrderItemNo(String orderItemNo) {
		this.orderItemNo = orderItemNo;
	}
	
	@Length(min=1, max=64, message="商品名称长度必须介于 1 和 64 之间")
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public String getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(String originalPrice) {
		this.originalPrice = originalPrice;
	}
	
	public String getRealityPrice() {
		return realityPrice;
	}

	public void setRealityPrice(String realityPrice) {
		this.realityPrice = realityPrice;
	}
	
	public String getSumPrice() {
		return sumPrice;
	}

	public void setSumPrice(String sumPrice) {
		this.sumPrice = sumPrice;
	}
	
	@Length(min=1, max=5, message="购买份数长度必须介于 1 和 5 之间")
	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}
	
	@Length(min=0, max=255, message="描述长度必须介于 0 和 255 之间")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Length(min=0, max=64, message="推荐医生id长度必须介于 0 和 64 之间")
	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
	
	@Length(min=0, max=255, message="备用字段1长度必须介于 0 和 255 之间")
	public String getStandby1() {
		return standby1;
	}

	public void setStandby1(String standby1) {
		this.standby1 = standby1;
	}
	
	@Length(min=0, max=255, message="备用字段2长度必须介于 0 和 255 之间")
	public String getStandby2() {
		return standby2;
	}

	public void setStandby2(String standby2) {
		this.standby2 = standby2;
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

	public String getProductPic() {
		return productPic;
	}

	public void setProductPic(String productPic) {
		this.productPic = productPic;
	}

	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}
	
}