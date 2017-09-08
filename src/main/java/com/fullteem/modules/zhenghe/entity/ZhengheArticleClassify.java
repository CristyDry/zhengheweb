/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.modules.zhenghe.entity;

import org.hibernate.validator.constraints.Length;

import com.fullteem.common.persistence.DataEntity;

/**
 * 知识频道Entity
 * @author 李启华
 * @version 2015-11-12
 */
public class ZhengheArticleClassify extends DataEntity<ZhengheArticleClassify> {
	
	private static final long serialVersionUID = 1L;
	private String classifyName;		// 分类名
	private String description;		// 描述
	private String rank;		// 排序
	private String status;		// 状态1上架0下架
	private String deleteMark;		// 删除标记
	private String remark;		// 备注
	private String mender;		// 修改者
	private String creator;		// 创建者
	
	public ZhengheArticleClassify() {
		super();
	}

	public ZhengheArticleClassify(String id){
		super(id);
	}

	@Length(min=1, max=32, message="分类名长度必须介于 1 和 32 之间")
	public String getClassifyName() {
		return classifyName;
	}

	public void setClassifyName(String classifyName) {
		this.classifyName = classifyName;
	}
	
	@Length(min=1, max=255, message="描述长度必须介于 1 和 255 之间")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Length(min=1, max=2, message="排序长度必须介于 1 和 2 之间")
	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}
	
	@Length(min=1, max=2, message="状态1上架0下架长度必须介于 1 和 2 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
	
}