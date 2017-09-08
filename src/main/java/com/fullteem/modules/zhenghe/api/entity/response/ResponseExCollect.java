/**   
 * 文件名：ResponseExCollect.java</br>
 * 描述： </br>
 * 开发人员：洪惜意 </br>
 * 创建时间： 2015年10月30日
 */ 

package com.fullteem.modules.zhenghe.api.entity.response;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/** 
 * 类名: ResponseExCollect</br> 
 * 包名：com.fullteem.modules.cosmetic.api.entity.response </br> 
 * 描述: </br>
 * 发布版本号：</br>
 * 开发人员： 洪惜意</br>
 * 创建时间： 2015年10月30日 
 */

@ApiModel(value="收藏心得返回")
public class ResponseExCollect {
	@ApiModelProperty(value="序列号")
	private String id;
	@ApiModelProperty(value="用户名")
	private String username;
	@ApiModelProperty(value="头像")
	private String avatar;
	@ApiModelProperty(value="内容")
	private String content;
	@ApiModelProperty(value="浏览量")
	private String readNum;
	@ApiModelProperty(value="评论数")
	private String commentNum;
	@ApiModelProperty(value="点赞数")
	private String pariseNum;
	@ApiModelProperty(value="标题")
	private String title;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public String getPariseNum() {
		return pariseNum;
	}
	public void setPariseNum(String pariseNum) {
		this.pariseNum = pariseNum;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	

}

