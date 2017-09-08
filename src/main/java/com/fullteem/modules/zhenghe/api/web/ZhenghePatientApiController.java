package com.fullteem.modules.zhenghe.api.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.impl.cookie.DateUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fullteem.common.web.BaseController;
import com.fullteem.common.web.BaseResult;
import com.fullteem.modules.zhenghe.api.entity.request.RequestId;
import com.fullteem.modules.zhenghe.api.entity.request.RequestLoginUser;
import com.fullteem.modules.zhenghe.api.entity.request.RequestNumber;
import com.fullteem.modules.zhenghe.api.entity.request.RequestPatient;
import com.fullteem.modules.zhenghe.api.entity.request.RequestSmsCode;
import com.fullteem.modules.zhenghe.api.entity.request.RequestTest;
import com.fullteem.modules.zhenghe.api.entity.request.RequestToken;
import com.fullteem.modules.zhenghe.api.entity.response.ResponseLogin;
import com.fullteem.modules.zhenghe.api.utils.CosmeticUpload;
import com.fullteem.modules.zhenghe.api.utils.MiPushUtil;
import com.fullteem.modules.zhenghe.api.utils.Tool;
import com.fullteem.modules.zhenghe.api.utils.ZhengheConstance;
import com.fullteem.modules.zhenghe.entity.ZhengheCarousel;
import com.fullteem.modules.zhenghe.entity.ZhengheDoctor;
import com.fullteem.modules.zhenghe.entity.ZhenghePatient;
import com.fullteem.modules.zhenghe.entity.ZhengheToken;
import com.fullteem.modules.zhenghe.service.ZhengheCityService;
import com.fullteem.modules.zhenghe.service.ZhengheDistrictService;
import com.fullteem.modules.zhenghe.service.ZhengheDoctorService;
import com.fullteem.modules.zhenghe.service.ZhenghePatientService;
import com.fullteem.modules.zhenghe.service.ZhengheProvincialService;
import com.fullteem.modules.zhenghe.service.ZhengheTokenService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@Api(value="ZhenghePatient" ,description = "患者")
@Controller
@RequestMapping(value = "/api/ZhenghePatient")
public class ZhenghePatientApiController extends BaseController{
	@Resource
	ZhenghePatientService patientService;
	@Resource
	ZhengheTokenService tokenService;
	@Resource
	ZhengheProvincialService provinceService;
	@Resource
	ZhengheCityService cityService;
	@Resource
	ZhengheDistrictService districtService;
	@Resource
	ZhengheDoctorService doctorService;
	
	
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述:修改出生日期 </br>
	 * 开发人员：李启华</br>
	 * 创建时间：2015年11月18日</br>
	 * @param jsonValue
	 * @return
	 * @throws Exception 
	 */
	@ApiOperation(value = "修改出生日期", notes = "患者Id(patientId)、出生日期(birthday,必须为“yyyy-MM-dd”字符串)必填，其他不用传")
	@RequestMapping(value = "/updateBirthday", method = RequestMethod.POST)
	@ApiResponses({
		@ApiResponse(code = ZhengheConstance.param_fault, message = "参数格式不正确", response = String.class),
		@ApiResponse(code = ZhengheConstance.userId_is_null, message = "用户id不存在", response = String.class),
		@ApiResponse(code = ZhengheConstance.date_param_fault, message = "日期字符参数格式错误", response = String.class),
	})
	public ResponseEntity<BaseResult> updateBirthday(@ApiParam(required = true, value = "患者Id(patientId)、出生日期(birthday)必填，其他不用传")
		@RequestBody RequestPatient param) throws Exception {
		String patientId = param.getPatientId();
		String birthday = param.getBirthday();
		if(StringUtils.isBlank(patientId) || StringUtils.isBlank(birthday)){return buildFailedResultInfo(ZhengheConstance.param_fault);}
		SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd");
		Date d;
		try {d = sdf.parse(birthday);} catch (ParseException e) {return buildFailedResultInfo(ZhengheConstance.date_param_fault);}
		ZhenghePatient p = patientService.get(patientId);
		if(p == null){return buildFailedResultInfo(ZhengheConstance.userId_is_null);}
		p.setBirthday(d);
		p.setAge(String.valueOf(getAge(d)));
		patientService.save(p);
		return buildSuccessResultInfo(ZhengheConstance.BASE_SUCCESS_CODE);
	}
	
	
	/**
	 * 根据出生日期计算年龄
	 * @param birthDay
	 * @return
	 * @throws Exception
	 */
	private static int getAge(Date birthDay) throws Exception {
        Calendar cal = Calendar.getInstance();

        if (cal.before(birthDay)) {
            throw new IllegalArgumentException("出生时间大于当前时间!");
        }

        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH) + 1;//注意此处，如果不加1的话计算结果是错误的
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthDay);

        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                //monthNow==monthBirth
                if (dayOfMonthNow < dayOfMonthBirth) {
                    age--;
                } else {
                    //do nothing
                }
            } else {
                //monthNow>monthBirth
                age--;
            }
        } else {
            //monthNow<monthBirth
            //donothing
        }

        return age;
    }
	
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述:修改省市区 </br>
	 * 开发人员：李启华</br>
	 * 创建时间：2015年11月18日</br>
	 * @param jsonValue
	 * @return
	 */
	@ApiOperation(value = "修改省市区", notes = "患者id(patientId)、省份(provincialId)必填，城市id(cityId)、区id(districtId)选填，其他不用传")
	@RequestMapping(value = "/updateAddress", method = RequestMethod.POST)
	@ApiResponses({
		@ApiResponse(code = ZhengheConstance.param_fault, message = "参数格式不正确", response = String.class),
		@ApiResponse(code = ZhengheConstance.userId_is_null, message = "用户id不存在", response = String.class),
		@ApiResponse(code = ZhengheConstance.provinceId_not_exist, message = "该省份id不存在", response = String.class),
		@ApiResponse(code = ZhengheConstance.cityId_not_exist, message = "该城市id不存在", response = String.class),
		@ApiResponse(code = ZhengheConstance.districtId_not_exist, message = "该地区id不存在", response = String.class),
	})
	public ResponseEntity<BaseResult> updateAddress(@ApiParam(required = true, value = "患者id(patientId)、省份(provincialId)必填，城市id(cityId)、区id(districtId)选填，其他不用传")
		@RequestBody RequestPatient param) {
		String patientId = param.getPatientId();
		String provincialId = param.getProvincialId();
		String cityId = param.getCityId();
		String districtId = param.getDistrictId();
		if(StringUtils.isBlank(patientId) || StringUtils.isBlank(provincialId)){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		ZhenghePatient p = patientService.get(patientId);
		if(p == null){
			return buildFailedResultInfo(ZhengheConstance.userId_is_null);
		}
		if(provinceService.get(provincialId) == null){
			return buildFailedResultInfo(ZhengheConstance.provinceId_not_exist);
		}
		p.setProvincialId(provincialId);
		if(StringUtils.isNotBlank(cityId) && cityService.get(cityId) == null){
			return buildFailedResultInfo(ZhengheConstance.cityId_not_exist);
		}else{
			p.setCityId(cityId);
		}
		if(StringUtils.isNotBlank(districtId) && districtService.get(districtId) == null){
			return buildFailedResultInfo(ZhengheConstance.districtId_not_exist);
		}else{
			p.setDistrictId(districtId);
		}
		patientService.save(p);
		return buildSuccessResultInfo(ZhengheConstance.BASE_SUCCESS_CODE);
	}
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述:修改性别 </br>
	 * 开发人员：李启华</br>
	 * 创建时间：2015年11月18日</br>
	 * @param jsonValue
	 * @return
	 */
	@ApiOperation(value = "修改性别", notes = "患者Id(patientId)、性别(gender->男/女)必填，其他不用传")
	@RequestMapping(value = "/updateGender", method = RequestMethod.POST)
	@ApiResponses({
		@ApiResponse(code = ZhengheConstance.param_fault, message = "参数格式不正确", response = String.class),
		@ApiResponse(code = ZhengheConstance.userId_is_null, message = "用户id不存在", response = String.class),
		@ApiResponse(code = ZhengheConstance.gender_param_fault, message = "性别参数格式错误，参数必须为'男'或'女'", response = String.class),
	})
	public ResponseEntity<BaseResult> updateGender(@ApiParam(required = true, value = "患者Id(patientId)、性别(gender)必填，其他不用传")
		@RequestBody RequestPatient param) {
		String patientId = param.getPatientId();
		String gender = param.getGender();
		if(StringUtils.isBlank(patientId) || StringUtils.isBlank(gender)){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		if(Tool.strNoIn(gender, "男","女")){
			return buildFailedResultInfo(ZhengheConstance.gender_param_fault);
		}
		ZhenghePatient p = patientService.get(patientId);
		if(p == null){return buildFailedResultInfo(ZhengheConstance.userId_is_null);}
		p.setGender(gender);
		patientService.save(p);
		return buildSuccessResultInfo(ZhengheConstance.BASE_SUCCESS_CODE);
	}
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述:修改名字 </br>
	 * 开发人员：李启华</br>
	 * 创建时间：2015年11月18日</br>
	 * @param jsonValue
	 * @return
	 */
	@ApiOperation(value = "修改名字", notes = "患者Id(patientId)、患者名称(patientName)必填，其他不用传")
	@RequestMapping(value = "/updatePatientName", method = RequestMethod.POST)
	@ApiResponses({
		@ApiResponse(code = ZhengheConstance.param_fault, message = "参数格式不正确", response = String.class),
		@ApiResponse(code = ZhengheConstance.userId_is_null, message = "用户id不存在", response = String.class),
	})
	public ResponseEntity<BaseResult> updatePatientName(@ApiParam(required = true, value = "患者Id(patientId)、患者名称(patientName)必填，其他不用传")
		@RequestBody RequestPatient param) {
		String patientId = param.getPatientId();
		String patientName = param.getPatientName();
		if(StringUtils.isBlank(patientId) || StringUtils.isBlank(patientName)){return buildFailedResultInfo(ZhengheConstance.param_fault);}
		ZhenghePatient p = patientService.get(patientId);
		if(p == null){return buildFailedResultInfo(ZhengheConstance.userId_is_null);}
		p.setPatientName(patientName);
		patientService.save(p);
		return buildSuccessResultInfo(ZhengheConstance.BASE_SUCCESS_CODE);
	}
	
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述:上传头像 </br>
	 * 开发人员：李启华</br>
	 * 创建时间：2015年11月18日</br>
	 * @param jsonValue
	 * @return
	 */
	@ApiOperation(value = "上传头像", notes = "上传患者头像(医生端上传头像不要调用这个)")
	@RequestMapping(value = "/uploadAvatar", method = RequestMethod.POST)
	@ApiResponses({
		@ApiResponse(code = ZhengheConstance.param_fault, message = "参数格式不正确", response = String.class),
		@ApiResponse(code = ZhengheConstance.userId_is_null, message = "用户id不存在", response = String.class),
		@ApiResponse(code = ZhengheConstance.empty_file, message = "file空", response = String.class),
		@ApiResponse(code = ZhengheConstance.file_mine_fault, message = "file类型错误", response = String.class),
		@ApiResponse(code = ZhengheConstance.upload_fault, message = "上传出错", response = String.class),
	})
	public ResponseEntity<BaseResult> uploadAvatar(HttpServletRequest request,@ModelAttribute RequestId param,@RequestParam MultipartFile file) {
		String patientId = param.getId();
		if(StringUtils.isBlank(patientId)){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		ZhenghePatient p = patientService.get(patientId);
		if(p == null){
			return buildFailedResultInfo(ZhengheConstance.userId_is_null);
		}
		String result = CosmeticUpload.uploadFile(request, "1", file);
		if(result.equals("1")){
			return buildFailedResultInfo(ZhengheConstance.empty_file);
		}
		if(result.equals("2")){
			return buildFailedResultInfo(ZhengheConstance.file_mine_fault);
		}
		if(result.equals("3")){
			return buildFailedResultInfo(ZhengheConstance.upload_fault);
		}
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
		p.setAvatar(request.getContextPath()+result);
		patientService.save(p);
		System.out.println("---------------------------- result : "+result);
		System.out.println("---------------------------- basePath : "+basePath);
		Map<String,String> avatarMap = new HashMap<String,String>();
		avatarMap.put("avatar", basePath+result);
		return buildSuccessResultInfo(avatarMap);
	}
	
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述:患者注册 </br>
	 * 开发人员：李启华</br>
	 * 创建时间：2015年11月10日</br>
	 * @param phone
	 * @param password
	 * @param tokenName
	 * @param code
	 * @return
	 */
	@ApiOperation(value = "注册", notes = "手机号、密码、token必填，昵称选填")
	@RequestMapping(value = "/patientRegister", method = RequestMethod.POST)
	@ApiResponses({
		@ApiResponse(code=ZhengheConstance.BASE_SUCCESS_CODE,message="success",response=String.class),
		@ApiResponse(code = ZhengheConstance.BASE_FAIL_CODE, message = "失败", response = String.class),
		@ApiResponse(code = ZhengheConstance.ISNULLMOBIE, message = "手机号不能为空", response = String.class),
		@ApiResponse(code = ZhengheConstance.ISNULLPASSWORD, message = "密码不能为空", response = String.class),
		@ApiResponse(code = ZhengheConstance.ISTHERETOKEN, message = "tokenName不能为空", response = String.class),
		@ApiResponse(code = ZhengheConstance.ISTHEREMOBILE, message = "手机号已存在", response = String.class)
	})
	public ResponseEntity<BaseResult> patientRegister(@ApiParam(required = true, name = "RequestRegister", value = "除用户类型外，入参全部必填")
		@RequestBody RequestLoginUser register) {
		String phone = register.getPhone();
		String password = register.getPassword();
		String tokenName = register.getTokenName();
		if (StringUtils.isBlank(phone)) {
			return buildFailedResultInfo(ZhengheConstance.ISNULLMOBIE);
		}
		if (StringUtils.isBlank(password)) {
			return buildFailedResultInfo(ZhengheConstance.ISNULLPASSWORD);
		}
		if (StringUtils.isBlank(tokenName)) {
			return buildFailedResultInfo(ZhengheConstance.ISTHERETOKEN);
		}
		// 验证手机号是否存在
		ZhenghePatient patient = patientService.findByPhone(phone);
		if(patient!=null){
			return buildFailedResultInfo(ZhengheConstance.ISTHEREMOBILE);
		}
		patient = new ZhenghePatient();
		patient.setPhone(phone);
		patient.setPassword(password.toLowerCase());
		patient.setCreateDate(new Date());
		patient.setStatus("1");
		patient.setChannel("1");
		patient.setCreator(phone);
		patientService.save(patient);
		
		ZhengheToken token = new ZhengheToken();
		token.setTokenName(tokenName);
		token.setPatientId(patient.getId());
		token.setCreateDate(new Date());
		token.setType("1");
		token.setCreator(phone);
		tokenService.save(token);
		
		return buildSuccessResultInfo(ZhengheConstance.BASE_SUCCESS_CODE);
	}
	
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述:患者登录 </br>
	 * 开发人员：李启华</br>
	 * 创建时间：2015年11月10日</br>
	 * @param jsonValue
	 * @return
	 * @throws Exception 
	 */
	@ApiOperation(value = "登录", notes = "除用户类型外，其他手机号、密码、token必填")
	@RequestMapping(value = "/patientLogin", method = RequestMethod.POST)
	@ApiResponses({
		@ApiResponse(code=ZhengheConstance.BASE_SUCCESS_CODE,message="success",response=ResponseLogin.class),
		@ApiResponse(code = ZhengheConstance.BASE_FAIL_CODE, message = "失败", response = String.class),
		@ApiResponse(code = ZhengheConstance.ISNULLMOBIE, message = "手机号不能为空", response = String.class),
		@ApiResponse(code = ZhengheConstance.ISNULLPASSWORD, message = "密码不能为空", response = String.class),
		@ApiResponse(code = ZhengheConstance.ISEXISITMOBILE, message = "手机号不存在", response = String.class),
		@ApiResponse(code = ZhengheConstance.ISPASSWORDFAIL, message = "密码错误", response = String.class),
		@ApiResponse(code = ZhengheConstance.ISTHERETOKEN, message = "tokenName不能为空", response = String.class)
	})
	public ResponseEntity<BaseResult> patientLogin(@ApiParam(required = true, name = "RequestLoginUser", value = "用户类型不用填，其他都要")
		@RequestBody RequestLoginUser requestLoginUser,HttpServletRequest request) throws Exception {
		String phone = requestLoginUser.getPhone();
		String password = requestLoginUser.getPassword();
		String tokenName = requestLoginUser.getTokenName();
		if (StringUtils.isBlank(phone)) {
			return buildFailedResultInfo(ZhengheConstance.ISNULLMOBIE);
		}
		if (StringUtils.isBlank(password)) {
			return buildFailedResultInfo(ZhengheConstance.ISNULLPASSWORD);
		}
		if (StringUtils.isBlank(tokenName)) {
			return buildFailedResultInfo(ZhengheConstance.ISTHERETOKEN);
		}
		
		//验证账号
		ZhenghePatient patient = patientService.findByPhone(phone);
		if(patient==null){
			return buildFailedResultInfo(ZhengheConstance.ISEXISITMOBILE);
		}else{
			String a = patient.getPassword();
			System.out.println("--- a : "+a);
			if(!patient.getPassword().equals(password.toLowerCase())){
				return buildFailedResultInfo(ZhengheConstance.ISPASSWORDFAIL);
			}
		}
		
		//更新数据库用户token
		ZhengheToken token = tokenService.findByPatientId(patient.getId());
		if(token==null){
			token = new ZhengheToken();
			token.setTokenName(tokenName);
			token.setPatientId(patient.getId());
			token.setCreateDate(new Date());
			token.setType("1");
			token.setCreator(phone);
			tokenService.save(token);
		}else{
			if(!token.getTokenName().equals(tokenName)){
				//推送下线通知
				MiPushUtil.sendGetOutMessage(token.getTokenName());
				
				token.setTokenName(tokenName);
				token.setUpdateDate(new Date());
				token.setCreator(phone);
				tokenService.save(token);
			}
		}
		
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
		ResponseLogin rl = new ResponseLogin();
		rl.setId(patient.getId());
		rl.setAccountNumber(patient.getAccountNumber());
		rl.setAge(patient.getAge());
		String avatar = StringUtils.isBlank(patient.getAvatar()) ? "" : basePath+patient.getAvatar();
		rl.setAvatar(avatar);
		Date date = patient.getBirthday();
		rl.setBirthday(date==null?"":DateUtils.formatDate(date,"yyyy-MM-dd"));
		rl.setChannel(patient.getChannel());
		rl.setCityId(patient.getCityId());
		rl.setCityName(patient.getCityId() == null ? "" : cityService.get(patient.getCityId()).getCityName());
		rl.setDistrictId(patient.getDistrictId());
		rl.setDistrictName(patient.getDistrictId() == null ? "" : districtService.get(patient.getDistrictId()).getDistrictName());
		rl.setGender(patient.getGender());
		rl.setPatientName(patient.getPatientName());
		rl.setPhone(patient.getPhone());
		rl.setProvinceName(patient.getProvincialId() == null ? "" : provinceService.get(patient.getProvincialId()).getName());
		rl.setProvincialId(patient.getProvincialId());
		rl.setRemark(patient.getRemark());
		rl.setStatus(patient.getStatus());
		return buildSuccessResultInfo(rl);
	}
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述:短信下发 </br>
	 * 开发人员：李启华</br>
	 * 创建时间：2015年11月10日</br>
	 * @param jsonValue
	 * @return
	 */
	@ApiOperation(value = "短信下发", notes = "手机号、类型(1注册，2忘记密码)必填")
	@RequestMapping(value = "/smsCode", method = RequestMethod.POST)
	@ApiResponses({
		@ApiResponse(code=ZhengheConstance.BASE_SUCCESS_CODE,message="success",response=String.class),
		@ApiResponse(code = ZhengheConstance.BASE_FAIL_CODE, message = "失败", response = String.class),
		@ApiResponse(code = ZhengheConstance.ISNULLMOBIE, message = "手机号不能为空", response = String.class),
		@ApiResponse(code = ZhengheConstance.PHONEISREGISTER, message = "您的号码已经注册过了", response = String.class),
		@ApiResponse(code = ZhengheConstance.PHONEISNOREGISTER, message = "您的号码还没注册", response = String.class),
		@ApiResponse(code = ZhengheConstance.param_fault, message = "参数格式不正确", response = String.class),
	})
	public ResponseEntity<BaseResult> smsCode(@ApiParam(required = true, name = "RequestSmsCode", value = "入参全部必填")
	@RequestBody RequestSmsCode requestSmsCode) {
		String phone = requestSmsCode.getPhone();
		String type = requestSmsCode.getType();
		System.out.println("------------ phone:"+phone+" ,type:"+type);
		boolean b = false;
		String smsStr="";
		
		if (Tool.strBlank(phone,type) || (phone.length()!=11)) {
			System.out.println("-------aaaa");
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		/*if(!type.equals("1") || !type.equals("2")){
			System.out.println("-------bbbb");
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}*/
		//已注册过的手机号码，type为1，不发短信，没有注册过的手机号码，如果type为2，不发短信，防恶意刷短信，短信要钱的~~
		ZhenghePatient patient = patientService.findByPhone(phone);
		if(patient==null){
			if(type.equals("1")){//下发注册短信验证码
				b = true;
				smsStr ="您本次注册的短信验证码为：";
			}else{
				return buildFailedResultInfo(ZhengheConstance.PHONEISNOREGISTER);
			}
		}else{
			if(type.equals("1")){
				return buildFailedResultInfo(ZhengheConstance.PHONEISREGISTER);
			}else{
				b = true;
				smsStr ="您本次修改密码的短信验证码为：";
			}
		}
		
		if(b){
			int code= (int) (Math.random()*9000+1000);
			smsStr =smsStr+code;
			//调用短信接口
			
			
			return buildSuccessResultInfo("1111");
			
		}else{
			return buildFailedResultInfo(ZhengheConstance.BASE_FAIL_CODE);
		}
		//return buildSuccessResultInfo(ZhengheConstance.BASE_SUCCESS_CODE);
	}
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述:注销登录 </br>
	 * 开发人员：李启华</br>
	 * 创建时间：2015年11月10日</br>
	 * @param jsonValue
	 * @return
	 */
	@ApiOperation(value = "注销登录", notes = "token必填")
	@RequestMapping(value = "/patientLogout", method = RequestMethod.POST)
	@ApiResponses({
		@ApiResponse(code=ZhengheConstance.BASE_SUCCESS_CODE,message="success",response=String.class),
		@ApiResponse(code = ZhengheConstance.BASE_FAIL_CODE, message = "失败", response = String.class),
		@ApiResponse(code = ZhengheConstance.ISTHERETOKEN, message = "tokenName不能为空", response = String.class)
	})
	public ResponseEntity<BaseResult> patientLogout(@ApiParam(required = true, name = "RequestToken", value = "入参全部必填")
	@RequestBody RequestToken requestToken) {
		String tokenName = requestToken.getTokenName();
		if (StringUtils.isBlank(tokenName)) {
			return buildFailedResultInfo(ZhengheConstance.ISTHERETOKEN);
		}
		ZhengheToken token = tokenService.findByTokenName(tokenName);
		if(token!=null){
			tokenService.delete(token);
		}
		return buildSuccessResultInfo(ZhengheConstance.BASE_SUCCESS_CODE);
	}
	
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述:重设密码 </br>
	 * 开发人员：李启华</br>
	 * 创建时间：2015年11月11日</br>
	 * @param jsonValue
	 * @return
	 */
	@ApiOperation(value="重置密码",notes="手机号、密码、用户类型必填，tokenName不用填")
	@RequestMapping(value="/resetPwd",method = RequestMethod.POST)
	@ApiResponses({
		@ApiResponse(code = ZhengheConstance.BASE_SUCCESS_CODE,message = "success",response = String.class),
		@ApiResponse(code = ZhengheConstance.BASE_FAIL_CODE,message = "失败",response = String.class),
		@ApiResponse(code = ZhengheConstance.ISNULLMOBIE, message = "手机号不能为空", response = String.class),
		@ApiResponse(code = ZhengheConstance.ISNULLPASSWORD, message = "密码不能为空", response = String.class),
		@ApiResponse(code = ZhengheConstance.param_fault, message = "参数格式不正确", response = String.class),
		@ApiResponse(code = ZhengheConstance.ISEXISITMOBILE, message = "手机号不存在", response = String.class)
	})
	public ResponseEntity<BaseResult> resetPwd(@ApiParam(required = true,name = "",value="用户类型userType:1->患者,2->医生")
	@RequestBody RequestLoginUser requestLoginUser){
		String phone = requestLoginUser.getPhone();
		String password = requestLoginUser.getPassword();
		String userType = requestLoginUser.getUserType();
		if(StringUtils.isBlank(phone)){
			return buildFailedResultInfo(ZhengheConstance.ISNULLMOBIE);
		}
		if(StringUtils.isBlank(password)){
			return buildFailedResultInfo(ZhengheConstance.ISNULLPASSWORD);
		}
		if(StringUtils.isBlank(userType)){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		
		ZhenghePatient patient = null;
		ZhengheDoctor doctor = null;
		
		if("1".equals(userType)){
			patient = patientService.findByPhone(phone);
		}else if("2".equals(userType)){
			doctor = doctorService.findDoctorByPhone(phone);
		}
		
		if(patient == null && doctor ==null){
			return buildFailedResultInfo(ZhengheConstance.ISEXISITMOBILE);
		}
		
		if("1".equals(userType)){
			patient.setPassword(password.toLowerCase());
			patientService.save(patient);
		}else if("2".equals(userType)){
			doctor.setPassword(password.toLowerCase());
			doctorService.save(doctor);
		}
		
		return buildSuccessResultInfo(ZhengheConstance.BASE_SUCCESS_CODE);
	}
	
	
	/*@ApiOperation(value = "修改名字", notes = "")
	@RequestMapping(value = "/updatePatientName", method = RequestMethod.POST)
	@ApiResponses({
		@ApiResponse(code = ZhengheConstance.param_fault, message = "参数格式不正确", response = String.class),
	})
	public ResponseEntity<BaseResult> updatePatientName(@ApiParam(required = true, value = "")
		@RequestBody RequestNumber requestNumber) {
		
	
		return buildSuccessResultInfo(null);
	}*/
	
	
	
}
