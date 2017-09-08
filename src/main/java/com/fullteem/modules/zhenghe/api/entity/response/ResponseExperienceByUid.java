package com.fullteem.modules.zhenghe.api.entity.response;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "展示心得",description = "展示用户所有心得")
public class ResponseExperienceByUid {
	@ApiModelProperty(value = "序列号")
	private String id;
	@ApiModelProperty(value = "用户头像")
	private String avatar;
	@ApiModelProperty(value = "用户昵称")
	private String nickname;
	@ApiModelProperty(value = "标题")
	private String standby1;
	@ApiModelProperty(value = "内容")
	private String content1;
	@ApiModelProperty(value = "浏览量")
	private String readNum;
	@ApiModelProperty(value = "评论数")
	private String commentNum;
	@ApiModelProperty(value = "点赞数")
	private String priaseNum;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public String getStandby1() {
		return standby1;
	}
	public void setStandby1(String standby1) {
		this.standby1 = standby1;
	}
	public String getContent1() {
		return content1;
	}
	public void setContent1(String content1) {
		this.content1 = content1;
	}
	public String getReadNum() {
		return readNum;
	}
	public void setReadNum(String readNum) {
		this.readNum = readNum;
	}
	public String getCommentNum() {
		return commentNum;
	}
	public void setCommentNum(String commentNum) {
		this.commentNum = commentNum;
	}
	public String getPriaseNum() {
		return priaseNum;
	}
	public void setPriaseNum(String priaseNum) {
		this.priaseNum = priaseNum;
	}
	
	

}
