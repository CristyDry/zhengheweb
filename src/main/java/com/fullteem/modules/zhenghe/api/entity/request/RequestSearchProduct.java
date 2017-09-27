package com.fullteem.modules.zhenghe.api.entity.request;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value="商品",description="搜索商品")
public class RequestSearchProduct {
	
	@ApiModelProperty(value="关键字",required=true)
	private String keys;
	@ApiModelProperty(value="分类id",required=false)
	private String classifyId;
	@ApiModelProperty(value="价格最小值",required=false)
	private String priceMin;
	@ApiModelProperty(value="价格最大值",required=false)
	private String priceMax;
	@ApiModelProperty(value="处方药rx，非处方药otc，默认otc",required=false)
	private String type;
	@ApiModelProperty(value="处方药rx，非处方药otc，默认otc",required=false)
	private Boolean isSimple;
	@ApiModelProperty(value="'update_date desc(1综合排序，默认)'、'sales_num desc(2销量)'、'price asc(3价格低到高)'、'price desc(4价格高到低)'这4项之一",required=false)
	private String orderBy = "update_date desc";
	@ApiModelProperty(value="页码，默认从0起",required=false)
	private String pageNo;
	@ApiModelProperty(value="每页数量，默认4条",required=false)
	private String pageSize;
	
	public String getKeys() {
		return keys;
	}
	public void setKeys(String keys) {
		this.keys = keys;
	}
	public String getPriceMin() {
		return priceMin;
	}
	public void setPriceMin(String priceMin) {
		this.priceMin = priceMin;
	}
	public String getPriceMax() {
		return priceMax;
	}
	public void setPriceMax(String priceMax) {
		this.priceMax = priceMax;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public String getClassifyId() {
		return classifyId;
	}
	public void setClassifyId(String classifyId) {
		this.classifyId = classifyId;
	}
	public String getPageNo() {
		return pageNo;
	}
	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}
	public String getPageSize() {
		return pageSize;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public Boolean getSimple() {
		return isSimple;
	}

	public void setSimple(Boolean simple) {
		isSimple = simple;
	}
}
