package com.fullteem.modules.zhenghe.api.entity.request;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value="文章内容",description="传入用户id,用户类型(患者||医生)和文章id")
public class RequestArticle {
	
	@ApiModelProperty(value="用户id",required=false)
	private String userId;
	@ApiModelProperty(value="用户类型",required=false)
	private String userType;
	@ApiModelProperty(value="文章id",required=true)
	private String articleId;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	
	

}
