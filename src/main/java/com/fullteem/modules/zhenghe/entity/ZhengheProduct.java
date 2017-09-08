/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.modules.zhenghe.entity;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.fullteem.common.persistence.DataEntity;

/**
 * 药品Entity
 * @author 李启华
 * @version 2015-11-11
 */
public class ZhengheProduct extends DataEntity<ZhengheProduct> {
	
	private static final long serialVersionUID = 1L;
	private String pclassifyId;		// 分类id
	private String classifyName;	// 分类名称
	private String productName;		// 商品名称
	private Double price;		// 价格
	private String type;		// 类型otc和rx
	private String standard;		// 规格
	private String productUnit;		// 单位
	private String pfunction;		// 功能主治
	private String explains;		// 说明
	private String salesNum="0";		// 销量
	private String repertoryNum;		// 库存
	private String status;		// 状态1上架0下架
	private String standby1;		// 备用字段1
	private String standby2;		// 备用字段2
	private String deleteMark;		// 删除标记
	private String remark;		// 备注
	private String mender;		// 修改者
	private String creator;		// 创建者
	
	private String productPic;		//商品图片
	
	private List<String> images;	//商品图片
	
	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public ZhengheProduct() {
		super();
	}

	public ZhengheProduct(String id){
		super(id);
	}

	@Length(min=1, max=64, message="分类id长度必须介于 1 和 64 之间")
	public String getPclassifyId() {
		return pclassifyId;
	}

	public void setPclassifyId(String pclassifyId) {
		this.pclassifyId = pclassifyId;
	}

	public String getClassifyName() {
		return classifyName;
	}

	public void setClassifyName(String classifyName) {
		this.classifyName = classifyName;
	}

	@Length(min=1, max=255, message="商品名称长度必须介于 1 和 255 之间")
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	@Length(min=1, max=32, message="类型otc和rx长度必须介于 1 和 32 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=1, max=255, message="规格长度必须介于 1 和 255 之间")
	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}
	
	@Length(min=1, max=16, message="单位长度必须介于 1 和 16 之间")
	public String getProductUnit() {
		return productUnit;
	}

	public void setProductUnit(String productUnit) {
		this.productUnit = productUnit;
	}
	
	@Length(min=1, max=255, message="功能主治长度必须介于 1 和 255 之间")
	public String getPfunction() {
		return pfunction;
	}

	public void setPfunction(String pfunction) {
		this.pfunction = pfunction;
	}
	
	public String getExplains() {
		return explains;
	}

	public void setExplains(String explains) {
		this.explains = explains;
	}
	
	@Length(min=0, max=10, message="销量长度必须介于 0 和 10 之间")
	public String getSalesNum() {
		return salesNum;
	}

	public void setSalesNum(String salesNum) {
		this.salesNum = salesNum;
	}
	
	@Length(min=1, max=10, message="库存长度必须介于 1 和 10 之间")
	public String getRepertoryNum() {
		return repertoryNum;
	}

	public void setRepertoryNum(String repertoryNum) {
		this.repertoryNum = repertoryNum;
	}
	
	@Length(min=1, max=2, message="状态1上架0下架长度必须介于 1 和 2 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
	
}