package com.fullteem.modules.zhenghe.api.entity.response;

import java.util.Date;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(description="评论列表")
public class ResponseComment {
	@ApiModelProperty("序列")
	private String id;
	@ApiModelProperty("评论内容")
	private String content;
	@ApiModelProperty("评论时间")
	private Date  updateTime;
	@ApiModelProperty("用户头像")
	private String avatar;
	@ApiModelProperty("用户昵称")
	private String nickName;
	@ApiModelProperty("被评论题目")
	private String title;
	@ApiModelProperty("被评论内容")
	private String content1;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent1() {
		return content1;
	}
	public void setContent1(String content1) {
		this.content1 = content1;
	}
	
	

}
