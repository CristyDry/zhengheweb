/**   
 * 文件名：ResponseCollect.java</br>
 * 描述： </br>
 * 开发人员：洪惜意 </br>
 * 创建时间： 2015年10月30日
 */ 

package com.fullteem.modules.zhenghe.api.entity.response;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/** 
 * 类名: ResponseCollect</br> 
 * 包名：com.fullteem.modules.cosmetic.api.entity.response </br> 
 * 描述: </br>
 * 发布版本号：</br>
 * 开发人员： 洪惜意</br>
 * 创建时间： 2015年10月30日 
 */
@ApiModel(value="收藏产品返回")
public class ResponseProductCollect {
	@ApiModelProperty(value="序列号")
	private String id;
	@ApiModelProperty(value="产品名称")
	private String productName;
	@ApiModelProperty(value="图片")
	private String avatar;
	@ApiModelProperty(value="星级")
	private String starRank;
	@ApiModelProperty(value="评论数")
	private String commentNum;
	@ApiModelProperty(value="英文名")
	private String eName;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getStarRank() {
		return starRank;
	}
	public void setStarRank(String starRank) {
		this.starRank = starRank;
	}
	public String getCommentNum() {
		return commentNum;
	}
	public void setCommentNum(String commentNum) {
		this.commentNum = commentNum;
	}
	public String geteName() {
		return eName;
	}
	public void seteName(String eName) {
		this.eName = eName;
	}
	
	

}
