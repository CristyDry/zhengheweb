/**   
 * 文件名：ResponseCosmeticUserData.java</br>
 * 描述： </br>
 * 开发人员：洪惜意 </br>
 * 创建时间： 2015年10月29日
 */ 

package com.fullteem.modules.zhenghe.api.entity.response;

import java.util.Date;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/** 
 * 类名: ResponseCosmeticUserData</br> 
 * 包名：com.fullteem.modules.cosmetic.api.entity.response </br> 
 * 描述: </br>
 * 发布版本号：</br>
 * 开发人员： 洪惜意</br>
 * 创建时间： 2015年10月29日 
 */
@ApiModel(value="用户资料返回")
public class ResponseCosmeticUserData {
	@ApiModelProperty(value = "id必填")
	private String id;	
	@ApiModelProperty(value="用户名")
	private String username;		// 用户名
	@ApiModelProperty(value="昵称")
	private String nickname;		// 昵称
	@ApiModelProperty(value="头像")
	private String avatar;		// 头像
	@ApiModelProperty(value="性别")
	private String gender;		// 性别
	@ApiModelProperty(value="年龄")
	private String age;		// 年龄
	@ApiModelProperty(value="肤质")
	private String skin;		// 肤质
	@ApiModelProperty(value="问题集合")
	private String question;		// 肤质问题集合
	@ApiModelProperty(value="手机号")
	private String phone;
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
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getSkin() {
		return skin;
	}
	public void setSkin(String skin) {
		this.skin = skin;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}	
	
	
	
}
