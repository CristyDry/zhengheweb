/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.modules.zhenghe.entity;

import org.hibernate.validator.constraints.Length;

import com.fullteem.common.persistence.DataEntity;

/**
 * 图册表Entity
 * @author 李启华
 * @version 2015-11-19
 */
public class ZhengheCommonImage extends DataEntity<ZhengheCommonImage> {
	
	private static final long serialVersionUID = 1L;
	private String image1;		// 图片1
	private String image2;		// 图片2
	private String image3;		// 图片3
	private String image4;		// 图片4
	private String image5;		// 图片5
	private String image6;		// 图片6
	private String image7;		// 图片7
	private String image8;		// 图片8
	private String image9;		// 图片9
	private String relevanceId;		// 关联id
	private String type;		// 类型1病历2病程3生活
	private String standby1;		// 备用字段1
	private String deleteMark;		// 删除标记
	private String remark;		// 备注
	private String mender;		// 修改者
	private String creator;		// 创建者
	
	public ZhengheCommonImage() {
		super();
	}

	public ZhengheCommonImage(String id){
		super(id);
	}

	@Length(min=0, max=255, message="图片1长度必须介于 0 和 255 之间")
	public String getImage1() {
		return image1;
	}

	public void setImage1(String image1) {
		this.image1 = image1;
	}
	
	@Length(min=0, max=255, message="图片2长度必须介于 0 和 255 之间")
	public String getImage2() {
		return image2;
	}

	public void setImage2(String image2) {
		this.image2 = image2;
	}
	
	@Length(min=0, max=255, message="图片3长度必须介于 0 和 255 之间")
	public String getImage3() {
		return image3;
	}

	public void setImage3(String image3) {
		this.image3 = image3;
	}
	
	@Length(min=0, max=255, message="图片4长度必须介于 0 和 255 之间")
	public String getImage4() {
		return image4;
	}

	public void setImage4(String image4) {
		this.image4 = image4;
	}
	
	@Length(min=0, max=255, message="图片5长度必须介于 0 和 255 之间")
	public String getImage5() {
		return image5;
	}

	public void setImage5(String image5) {
		this.image5 = image5;
	}
	
	@Length(min=0, max=255, message="图片6长度必须介于 0 和 255 之间")
	public String getImage6() {
		return image6;
	}

	public void setImage6(String image6) {
		this.image6 = image6;
	}
	
	@Length(min=0, max=255, message="图片7长度必须介于 0 和 255 之间")
	public String getImage7() {
		return image7;
	}

	public void setImage7(String image7) {
		this.image7 = image7;
	}
	
	@Length(min=0, max=255, message="图片8长度必须介于 0 和 255 之间")
	public String getImage8() {
		return image8;
	}

	public void setImage8(String image8) {
		this.image8 = image8;
	}
	
	@Length(min=0, max=255, message="图片9长度必须介于 0 和 255 之间")
	public String getImage9() {
		return image9;
	}

	public void setImage9(String image9) {
		this.image9 = image9;
	}
	
	@Length(min=0, max=64, message="关联id长度必须介于 0 和 64 之间")
	public String getRelevanceId() {
		return relevanceId;
	}

	public void setRelevanceId(String relevanceId) {
		this.relevanceId = relevanceId;
	}
	
	@Length(min=0, max=2, message="类型1病历2病程3生活长度必须介于 0 和 2 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
	
}