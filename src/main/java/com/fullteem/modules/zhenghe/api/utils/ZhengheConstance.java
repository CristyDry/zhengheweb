package com.fullteem.modules.zhenghe.api.utils;

import com.fullteem.common.utils.BaseConstance;

/**
 * 基础响应的操作编码
 * 
 * @author yuandong lin
 * 
 */
public interface ZhengheConstance extends BaseConstance{

	/** --------------------客户端返回状态码---------------------------- */
	
	
	/**---------------------用户模块----------------------------------*/
	
	/**
	 * 操作成功
	 */
	public final static int BASE_SUCCESS_CODE = 0;
	
	/**
	 * 失败
	 */
	public final static int BASE_FAIL_CODE = -1;
	
	
	
	/**
	 * 密码不能为空
	 */
	public final static int ISNULLPASSWORD = 1002;
	
	/**
	 * 手机号不能为空
	 */
	public final static int ISNULLMOBIE = 1003;
	
	/**
	 * 手机号已经存在
	 */
	public final static int ISTHEREMOBILE = 1004;
	
	/**
	 * 手机号不存在
	 */
	public final static int ISEXISITMOBILE = 1005;
	
	/**
	 * 登录成功
	 */
	public final static int ISLOGINSUCCESS = 1006;
	
	/**
	 * 登录失败
	 */
	public final static int ISLOGINFAIL = 1007;
	
	/**
	 * 修改失败
	 */
	public final static int ISUPDATEFAIL = 1008;
	
	/**
	 * 修改成功
	 */
	public final static int ISUPDATESUCCESS = 1009;
	
	/**
	 * 查询成功
	 */
	public final static int ISSELECTSUCCESS = 1010;
	
	/**
	 * 查询失败
	 */
	public final static int ISSELECTFAIL = 1011;
	/**
	 * 密码错误
	 */
	public final static int ISPASSWORDFAIL = 1012;
	/**
	 * 手机号已被删除
	 */
	public  final static  int ISEELETEMOBILE = 1013;
	/**
	 * 用户id数据库不存在
	 */
	public  final static  int userId_no_exisit = 1014;
	/**
	 * 用户id不存在
	 */
	public  final static  int userId_is_null = 1015;
	/**
	 * 用户已被逻辑删除
	 */
	public static final int userId_delete_exisit = 1016;
	/**
	 * 小秘书不存在
	 */
	public static final int secretary_no_exisit = 1017;
	/**
	 * 上传文件为空
	 */
	public static final int uploadFile_is_null = 1018;
	/**
	 * 上传文件类型错误
	 */
	public static final int uploadFile_type_error = 1019;
	/**
	 * token不能为空
	 */
	public static final int ISTHERETOKEN = 1020;
	/**
	 * 您的号码已经注册过了
	 */
	public static final int PHONEISREGISTER = 1021;
	/**
	 * 您的号码还没注册
	 */
	public static final int PHONEISNOREGISTER = 1022;
	/**
	 * 不存在该用户
	 */
	public static final int ISNULLEXISITUSER = 1023;
	/**
	 * 用户昵称为空
	 */
	public  final static  int is_nickname_null = 1024;
	/**
	 * 用户性别为空
	 */
	public  final static  int is_gender_null = 1025;
	/**
	 * 用户年龄为空
	 */
	public  final static  int is_age_null = 1026;
	/**
	 * 用户被锁定
	 */
	public  final static  int is_lock = 1027;
	/**
	 * 参数格式不正确
	 */
	public  final static  int param_fault = 1028;
	/**
	 * 商品不存在
	 */
	public  final static  int productId_is_null = 1029;
	/**
	 * 该省份id不存在
	 */
	public  final static  int provinceId_not_exist = 1030;
	/**
	 * 该城市id不存在
	 */
	public  final static  int cityId_not_exist = 1031;
	/**
	 * 该地区id不存在
	 */
	public  final static  int districtId_not_exist = 1032;
	/**
	 * 用户地址id不存在
	 */
	public  final static  int address_not_exist = 1033;
	/**
	 * 购物车是空的
	 */
	public  final static  int empty_buy_cart = 1034;
	/**
	 * 订单id不存在
	 */
	public  final static  int orderId_not_exist = 1035;
	/**
	 * 订单状态异常
	 */
	public  final static  int order_status_fault = 1036;
	/**
	 * 日期字符参数格式错误
	 */
	public  final static  int date_param_fault = 1037;
	/**
	 * 性别参数格式错误，参数必须为'男'或'女'
	 */
	public  final static  int gender_param_fault = 1038;
	/**
	 * 科室id不存在
	 */
	public  final static  int departmentId_not_exist = 1039;
	/**
	 * 病历id不存在
	 */
	public  final static  int mhId_not_exist = 1040;
	/**
	 * file空
	 */
	public  final static  int empty_file = 1041;
	/**
	 * file类型错误
	 */
	public  final static  int file_mine_fault = 1042;
	/**
	 * 上传出错
	 */
	public  final static  int upload_fault = 1043;
	/**
	 * 病程id不存在
	 */
	public  final static  int cdId_not_exist = 1044;
	/**
	 * 该科室不是一级科室
	 */
	public  final static  int not_a_top_department = 1045;
	/**
	 * 医生不存在
	 */
	public  final static  int doctor_not_exist = 1046;
	/**
	 * 购物车没有该商品
	 */
	public  final static  int product_not_in_car = 1047;
	/**
	 * 对方已是您的好友
	 */
	public  final static int good_friend = 1048;
	/**
	 * 分组已经存在
	 */
	public  final static int group_already_exist = 1049;
	/**
	 * 对方并不是您的好友
	 */
	public  final static int not_friend = 1050;
	/**
	 * 目标组id不是当前用户的，不能乱来
	 */
	public  final static int not_current_user = 1051;
	
	
	
	
	
	
	
	/**--------------------浪管理------------*/
	
	/**
	 * 暂无数据
	 */
	public final static int ISNOTDATE = 2001;
	
	
	/*--------------------评分------------*/
	public final static int ISEXISITSCORE = 3001;
	
	
	/*---------------举报-----------------*/
	
	/**
	 * 举报者不存在 
	 */
	public final static int ISNOTINFORMANTS=4001;
	
	/**
	 * 被举报者不存在
	 */
	public final static int ISNOTBEINFORMANTS = 4002;
	
	/**
	 * 举报内容不为空
	 */
	public final static int ISNOTNULLREASON = 4003;
	
	/**
	 * 被举报人和举报人不能为同一用户
	 */
	public final static int ISNOTREPORT = 4004;
	/**
	 * 举报者id为空
	 */
	public final static int informants_is_null = 4005;
	/**
	 * 被举报者id为空
	 */
	public final static int beInformants_is_null = 4006;
	
	/*---------------版本-----------------*/
	/**
	 * type为空
	 */
	public final static int type_is_null = 5001;
	
}
