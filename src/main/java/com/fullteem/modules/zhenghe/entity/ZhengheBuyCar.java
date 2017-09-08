/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.modules.zhenghe.entity;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

import com.fullteem.common.persistence.DataEntity;

/**
 * 购物车Entity
 * @author 李启华
 * @version 2015-11-17
 */
public class ZhengheBuyCar extends DataEntity<ZhengheBuyCar> {
	
	private static final long serialVersionUID = 1L;
	private String productId;		// 商品id
	private String count;			// 数量
	private Double realityPrice;	// 单价
	private Double sumPrice;		// 总价
	private String patientId;		// 患者id
	private String standby1;		// 备用字段1
	private String deleteMark;		// 删除标记
	private String remark;			// 备注
	private String mender;			// 修改者
	private String creator;			// 创建者
	
	private String productPic;		// 商品图片
	private String productName;		// 商品名称
	private String standard;		// 规格
	
	public ZhengheBuyCar() {
		super();
	}

	public ZhengheBuyCar(String id){
		super(id);
	}

	@Length(min=1, max=64, message="商品id长度必须介于 1 和 64 之间")
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	@Length(min=1, max=32, message="数量长度必须介于 1 和 32 之间")
	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}
	
	@NotNull(message="单价不能为空")
	public Double getRealityPrice() {
		return realityPrice;
	}

	public void setRealityPrice(Double realityPrice) {
		this.realityPrice = realityPrice;
	}
	
	@NotNull(message="总价不能为空")
	public Double getSumPrice() {
		return sumPrice;
	}

	public void setSumPrice(Double sumPrice) {
		this.sumPrice = sumPrice;
	}
	
	@Length(min=1, max=64, message="患者id长度必须介于 1 和 64 之间")
	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
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

	public String getProductPic() {
		return productPic;
	}

	public void setProductPic(String productPic) {
		this.productPic = productPic;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	
	
}