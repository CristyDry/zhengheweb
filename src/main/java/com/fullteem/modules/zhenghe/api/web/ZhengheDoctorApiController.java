package com.fullteem.modules.zhenghe.api.web;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.impl.cookie.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fullteem.common.persistence.BaseEntity;
import com.fullteem.common.persistence.DataEntity;
import com.fullteem.common.service.BaseService;
import com.fullteem.common.service.CrudService;
import com.fullteem.common.web.BaseController;
import com.fullteem.common.web.BaseResult;
import com.fullteem.modules.zhenghe.api.alipay.config.AlipayConfig;
import com.fullteem.modules.zhenghe.api.entity.request.RequestArticle;
import com.fullteem.modules.zhenghe.api.entity.request.RequestCollect;
import com.fullteem.modules.zhenghe.api.entity.request.RequestCriteria;
import com.fullteem.modules.zhenghe.api.entity.request.RequestDeletePatient;
import com.fullteem.modules.zhenghe.api.entity.request.RequestDoctor;
import com.fullteem.modules.zhenghe.api.entity.request.RequestFriendMessage;
import com.fullteem.modules.zhenghe.api.entity.request.RequestGroup;
import com.fullteem.modules.zhenghe.api.entity.request.RequestId;
import com.fullteem.modules.zhenghe.api.entity.request.RequestIdAndOther;
import com.fullteem.modules.zhenghe.api.entity.request.RequestLoginUser;
import com.fullteem.modules.zhenghe.api.entity.request.RequestMessage;
import com.fullteem.modules.zhenghe.api.entity.request.RequestMoveGroup;
import com.fullteem.modules.zhenghe.api.entity.request.RequestSearchPatient;
import com.fullteem.modules.zhenghe.api.entity.request.RequestToken;
import com.fullteem.modules.zhenghe.api.entity.request.RequestUser;
import com.fullteem.modules.zhenghe.api.entity.response.ResponseLogin;
import com.fullteem.modules.zhenghe.api.utils.ApiHttpClient;
import com.fullteem.modules.zhenghe.api.utils.CosmeticUpload;
import com.fullteem.modules.zhenghe.api.utils.FormatType;
import com.fullteem.modules.zhenghe.api.utils.MiPushUtil;
import com.fullteem.modules.zhenghe.api.utils.SdkHttpResult;
import com.fullteem.modules.zhenghe.api.utils.Tool;
import com.fullteem.modules.zhenghe.api.utils.ZhengheConstance;
import com.fullteem.modules.zhenghe.entity.DepartmentsZhenghe;
import com.fullteem.modules.zhenghe.entity.DoctorZhenghe;
import com.fullteem.modules.zhenghe.entity.FriendZhenghe;
import com.fullteem.modules.zhenghe.entity.GroupZhenghe;
import com.fullteem.modules.zhenghe.entity.PatientZhenghe;
import com.fullteem.modules.zhenghe.entity.ZhengheArticle;
import com.fullteem.modules.zhenghe.entity.ZhengheArticleClassify;
import com.fullteem.modules.zhenghe.entity.ZhengheCarousel;
import com.fullteem.modules.zhenghe.entity.ZhengheCity;
import com.fullteem.modules.zhenghe.entity.ZhengheCollect;
import com.fullteem.modules.zhenghe.entity.ZhengheDepartments;
import com.fullteem.modules.zhenghe.entity.ZhengheDoctor;
import com.fullteem.modules.zhenghe.entity.ZhengheFriend;
import com.fullteem.modules.zhenghe.entity.ZhengheGroup;
import com.fullteem.modules.zhenghe.entity.ZhengheHospital;
import com.fullteem.modules.zhenghe.entity.ZhengheMessage;
import com.fullteem.modules.zhenghe.entity.ZhenghePatient;
import com.fullteem.modules.zhenghe.entity.ZhengheProvincial;
import com.fullteem.modules.zhenghe.entity.ZhengheToken;
import com.fullteem.modules.zhenghe.entity.ZhengheVersion;
import com.fullteem.modules.zhenghe.service.ZhengheArticleClassifyService;
import com.fullteem.modules.zhenghe.service.ZhengheArticleService;
import com.fullteem.modules.zhenghe.service.ZhengheCarouselService;
import com.fullteem.modules.zhenghe.service.ZhengheCityService;
import com.fullteem.modules.zhenghe.service.ZhengheCollectService;
import com.fullteem.modules.zhenghe.service.ZhengheDepartmentsService;
import com.fullteem.modules.zhenghe.service.ZhengheDistrictService;
import com.fullteem.modules.zhenghe.service.ZhengheDoctorService;
import com.fullteem.modules.zhenghe.service.ZhengheFriendService;
import com.fullteem.modules.zhenghe.service.ZhengheGroupService;
import com.fullteem.modules.zhenghe.service.ZhengheHospitalService;
import com.fullteem.modules.zhenghe.service.ZhengheMessageService;
import com.fullteem.modules.zhenghe.service.ZhenghePatientService;
import com.fullteem.modules.zhenghe.service.ZhengheProfessionService;
import com.fullteem.modules.zhenghe.service.ZhengheProvincialService;
import com.fullteem.modules.zhenghe.service.ZhengheTokenService;
import com.fullteem.modules.zhenghe.service.ZhengheVersionService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@Api(value="ZhengheDoctor" ,description = "医生")
@Controller
@RequestMapping(value = "/api/ZhengheDoctor")
public class ZhengheDoctorApiController extends BaseController {
	
	@Autowired
	private ZhengheTokenService zhengheTokenService;
	@Autowired
	private ZhengheDoctorService zhengheDoctorService;
	@Autowired
	private ZhengheMessageService zhengheMessageService;
	@Autowired
	private ZhenghePatientService zhenghePatientService;
	@Autowired
	private ZhengheProvincialService provinceService;
	@Autowired
	private ZhengheCityService zhengheCityService;
	@Autowired
	private ZhengheDistrictService districtService;
	@Autowired
	private ZhengheFriendService zhengheFriendService;
	@Autowired
	private ZhengheGroupService zhengheGroupService;
	@Autowired
	private ZhengheDepartmentsService zhengheDepartmentsService;
	@Autowired
	private ZhengheHospitalService zhengheHospitalService;
	@Autowired
	private ZhengheProfessionService zhengheProfessionService;
	@Autowired
	private ZhengheCarouselService zhengheCarouselService;
	@Autowired
	private ZhengheArticleClassifyService zhengheArticleClassifyService;
	@Autowired
	private ZhengheArticleService zhengheArticleService;
	@Autowired
	private ZhengheCollectService zhengheCollectService;
	@Autowired
	private ZhengheProvincialService zhengheProvincialService;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private ZhengheVersionService zhengheVersionService;
	
	
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述: </br>
	 * 开发人员：chenx</br>
	 * 创建时间：2016年1月5日</br>
	 * @return
	 */
	@ApiOperation(value="分享APP",notes="无需传参,返回一个url(页面包含了下载app的连接)")
	@RequestMapping(value="/shareApp",method=RequestMethod.POST)
	@ApiResponses({
		@ApiResponse(code=ZhengheConstance.param_fault,message="参数格式不正确",response=String.class),
	})
	public ResponseEntity<BaseResult> shareApp(){
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("url", "http://www.immomo.com/");
		
		return buildSuccessResultInfo(jsonObject);
	}
	
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述: </br>
	 * 开发人员：chenx</br>
	 * 创建时间：2016年1月4日</br>
	 * @return
	 */
	@ApiOperation(value="第三方登录",notes="前端传过来的第三方的凭证(openId),channel(注册渠道):1->平台注册;2->第三方||accountNumber即为openId")
	@RequestMapping(value="/Oauth",method=RequestMethod.POST)
	@ApiResponses({
		@ApiResponse(code=ZhengheConstance.param_fault,message="参数格式不正确",response=String.class),
		@ApiResponse(code=ZhengheConstance.BASE_SUCCESS_CODE,message="success",response=ResponseLogin.class),
	})
	public ResponseEntity<BaseResult> Oauth(@ApiParam(required=true,name="id,other",value="id填上openId,other填上tokenName")
		@RequestBody RequestIdAndOther param){
		
		String accountNumber = param.getId();
		String tokenName = param.getOther();
		String avatar = param.getAvatar();
		String patientName = param.getPatientName();
		
		com.fullteem.common.utils.Log.println("------------------- accountNumber:"+accountNumber+",tokenName:"+tokenName+",avatar:"+avatar+",patientName:"+patientName);
		
		if(!StringUtils.hasText(accountNumber)||!StringUtils.hasText(tokenName)){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		
		ZhenghePatient patient = zhenghePatientService.findPatientByAccountNumber(accountNumber);
		
		if(patient==null){
			patient = new ZhenghePatient();
			patient.setAccountNumber(accountNumber);
			patient.setChannel("2");
			patient.setStatus("1");
			patient.setPatientName(patientName);
			patient.setAvatar(avatar);
			zhenghePatientService.save(patient);
		}
		return buildSuccessResultInfo(patientToResponse(patient));
	}
	
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述: </br>
	 * 开发人员：chenx</br>
	 * 创建时间：2016年1月4日</br>
	 * @param requestId
	 * @return
	 */
	@ApiOperation(value="版本检测",notes="传入操作系统类型,返回apk或ipa最新的版本号")
	@RequestMapping(value="/checkVersion",method=RequestMethod.POST)
	@ApiResponses({
		@ApiResponse(code=ZhengheConstance.param_fault,message="参数格式不正确",response=String.class),
	})
	public ResponseEntity<BaseResult> checkVersion(@ApiParam(required=true,name="id",value="id填上操作系统类型(1->Android;2->IOS)")
		@RequestBody RequestId requestId){
		
		String type = requestId.getId();
		
		if(!StringUtils.hasText(type)){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		
		ZhengheVersion version = zhengheVersionService.findNewestByOSType(type);
		String url = getBaseContextPath()+version.getUrl().substring(1);
		
		version.setUrl(url);
		
		return buildSuccessResultInfo(version);
	}
	
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述: </br>
	 * 开发人员：chenx</br>
	 * 创建时间：2015年12月30日</br>
	 * @param requestId
	 * @return
	 */
	@ApiOperation(value="手机号验证",notes="传入手机号码,看改手机是否已经注册过(1->已注册;0->未注册)")
	@RequestMapping(value="/validationPhone",method=RequestMethod.POST)
	@ApiResponses({
		@ApiResponse(code=ZhengheConstance.param_fault,message="参数格式不正确",response=String.class),
		@ApiResponse(code = ZhengheConstance.PHONEISREGISTER, message = "您的号码已经注册过了", response = String.class),
		@ApiResponse(code=ZhengheConstance.PHONEISNOREGISTER,message="您的号码还没注册",response=String.class),
	})
	public ResponseEntity<BaseResult> validationPhone(@ApiParam(name="id",value="id填上要验证的手机号码")
			@RequestBody RequestId requestId){
		
		String phone = requestId.getId();
		
		if(!StringUtils.hasText(phone)){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		ZhenghePatient patient = zhenghePatientService.findByPhone(phone);
		
		JSONObject jsonObject = new JSONObject();
		
		if(patient==null){
			jsonObject.put("result", "0");
		}else{
			jsonObject.put("result", "1");
		}
		
		return buildSuccessResultInfo(jsonObject);
	}
	
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述: </br>
	 * 开发人员：chenx</br>
	 * 创建时间：2015年12月30日</br>
	 * @param requestIdAndOther
	 * @return
	 */
	@ApiOperation(value="详细资料",notes="根据患者id与医生id返回患者详细资料")
	@RequestMapping(value="/patientDetails",method=RequestMethod.POST)
	@ApiResponses({
		@ApiResponse(code=ZhengheConstance.param_fault,message="参数格式不正确",response=String.class),
		@ApiResponse(code = ZhengheConstance.userId_is_null, message = "用户id不存在", response = String.class),
		@ApiResponse(code=ZhengheConstance.not_friend,message="对方并不是您的好友",response=String.class),
	})
	public ResponseEntity<BaseResult> patientDetails(@ApiParam(required=true,name="id,other",value="患者id与医生id必填(id填上患者id,other填上医生id)")
			@RequestBody RequestIdAndOther requestIdAndOther){
		
		String patientId = requestIdAndOther.getId();
		String doctorId = requestIdAndOther.getOther();
		
		if(!StringUtils.hasText(patientId)||!StringUtils.hasText(doctorId)){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		
		ZhenghePatient patient = zhenghePatientService.get(patientId);
		ZhengheDoctor doctor = zhengheDoctorService.get(doctorId);
		
		if(doctor==null||patient==null){
			return buildFailedResultInfo(ZhengheConstance.userId_is_null);
		}
		
		List<ZhengheFriend> friendList = zhengheFriendService.findFriendByDoctorIdAndPatientId(doctorId, patientId);
		if(friendList.size()<=0){
			return buildFailedResultInfo(ZhengheConstance.not_friend);
		}
		
		ZhengheFriend friend = friendList.get(0);
		
		if(!"1".equals(friend.getStatus())){
			return buildFailedResultInfo(ZhengheConstance.not_friend);
		}
		
		ZhengheGroup group = zhengheGroupService.get(friend.getGroupingId());
		
		JSONObject jsonObject = patientToJson(patient);
		if(jsonObject!=null){
			jsonObject.put("groupName", group.getGroupName());
			jsonObject.put("groupId", group.getId());
		}
		
		return buildSuccessResultInfo(jsonObject);
	}
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述: </br>
	 * 开发人员：chenx</br>
	 * 创建时间：2015年12月24日</br>
	 * @return
	 */
	@ApiOperation(value="免责声明",notes="返回免责声明的url,无需传参")
	@RequestMapping(value="/disclaimer",method=RequestMethod.POST)
	public ResponseEntity<BaseResult> disclaimer(){
		return buildSuccessResultInfo(getValue("reserve.disclaimer"));
	}
	
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述: </br>
	 * 开发人员：chenx</br>
	 * 创建时间：2015年12月21日</br>
	 * @param requestSearchPatient
	 * @return
	 */
	@ApiOperation(value = "搜索患者", notes = "传入医生id和搜索的关键字(患者名字中的字[+])")
	@RequestMapping(value = "/searchPatient", method = RequestMethod.POST)
	@ApiResponses({
		@ApiResponse(code = ZhengheConstance.param_fault, message = "参数格式不正确", response = String.class),
	})
	public ResponseEntity<BaseResult> searchPatient(@ApiParam(name="doctorId,key",value="医生id与搜索关键字必填")
		@RequestBody RequestSearchPatient requestSearchPatient){
		
		String doctorId = requestSearchPatient.getDoctorId();
		String key = requestSearchPatient.getKey();
		
		if(!StringUtils.hasText(doctorId)){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		
		List<PatientZhenghe>  patientList2  = zhenghePatientService.findPatientByDoctorIdAndKey2(doctorId, key);
		
		for(PatientZhenghe patient:patientList2){
			if(!patient.getAvatar().startsWith("http")){
				patient.setAvatar(getBaseContextPath()+patient.getAvatar());
			}
		}
		
		return buildSuccessResultInfo(patientList2);
	}
	
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述: </br>
	 * 开发人员：chenx</br>
	 * 创建时间：2015年12月19日</br>
	 * @param file
	 * @return
	 */
	@ApiOperation(value = "上传头像", notes = "上传医生头像(患者端上传头像不要调用这个)")
	@RequestMapping(value = "/uploadAvatar", method = RequestMethod.POST)
	@ApiResponses({
		@ApiResponse(code = ZhengheConstance.param_fault, message = "参数格式不正确", response = String.class),
		@ApiResponse(code = ZhengheConstance.userId_is_null, message = "用户id不存在", response = String.class),
		@ApiResponse(code = ZhengheConstance.empty_file, message = "file空", response = String.class),
		@ApiResponse(code = ZhengheConstance.file_mine_fault, message = "file类型错误", response = String.class),
		@ApiResponse(code = ZhengheConstance.upload_fault, message = "上传出错", response = String.class),
	})
	public ResponseEntity<BaseResult> uploadAvatar(@ModelAttribute RequestId requestId,@RequestParam MultipartFile file) {
		String doctorId = requestId.getId();
		if(!StringUtils.hasText(doctorId)){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		ZhengheDoctor doctor = zhengheDoctorService.get(doctorId);
		if(doctor == null){
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
		doctor.setAvatar(result);
		zhengheDoctorService.save(doctor);
		Map<String,String> avatarMap = new HashMap<String,String>();
		if(!doctor.getAvatar().startsWith("http")){
			avatarMap.put("avatar", getBaseContextPath()+doctor.getAvatar());
		}else{
			avatarMap.put("avatar", doctor.getAvatar());
		}
		return buildSuccessResultInfo(avatarMap);
	}
	
	
	@ApiOperation(value="获取token",notes="获取融云token(传入用户id,用户类型userType)")
	@RequestMapping(value="/getRongToken",method=RequestMethod.POST)	
	@ApiResponses({
		@ApiResponse(code=ZhengheConstance.BASE_SUCCESS_CODE,message="success",response=String.class),
		@ApiResponse(code=ZhengheConstance.param_fault,message="参数格式不正确",response=String.class),
		@ApiResponse(code = ZhengheConstance.userId_is_null, message = "用户id不存在", response = String.class),
	})
	public ResponseEntity<BaseResult> getRongToken(@ApiParam(name="user",value="入参必填,userType:1->患者;2->医生")
			@RequestBody RequestUser requestUser)throws Exception{
		
		String userId = requestUser.getUserId();
		String userType = requestUser.getUserType();
		
		if(!StringUtils.hasText(userId)||!StringUtils.hasText(userType)){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		
		ZhenghePatient patient = null;
		ZhengheDoctor doctor = null;
		
		if("1".equals(userType)){
			patient = zhenghePatientService.get(userId);
		}else if("2".equals(userType)){
			doctor = zhengheDoctorService.get(userId);
		}
		
		if(patient==null&&doctor==null){
			return buildFailedResultInfo(ZhengheConstance.userId_is_null);
		}

		String key = "c9kqb3rdku4cj";
		String secret = "ZHLHwSx1rbJ";
		
		SdkHttpResult result = null;
		
		result = ApiHttpClient.getToken(key, secret, userId, patient==null?doctor.getDoctorName():patient.getPatientName(),
				patient==null?doctor.getAvatar():patient.getAvatar(), FormatType.json);
		
		com.fullteem.common.utils.Log.println("gettoken=" + result);
		
		net.sf.json.JSONObject tt = net.sf.json.JSONObject.fromObject(result.getResult());
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("rongToken", tt.get("token"));
		
		return buildSuccessResultInfo(jsonObject);
	}
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述: 阅读系统消息</br>
	 * 开发人员：chenx</br>
	 * 创建时间：2015年11月27日</br>
	 * @return
	 */
	@ApiOperation(value="阅读消息",notes="传入系统消息的id,将消息置为已读")
	@RequestMapping(value="/readMessage",method=RequestMethod.POST)	
	@ApiResponses({
		@ApiResponse(code=ZhengheConstance.BASE_SUCCESS_CODE,message="success",response=String.class),
		@ApiResponse(code=ZhengheConstance.param_fault,message="参数格式不正确",response=String.class)
	})
	public ResponseEntity<BaseResult> readMessage(@ApiParam(name="id",value="系统消息的id")
			@RequestBody RequestId requestId){
		
		if(!StringUtils.hasText(requestId.getId())){
			buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		
		/*ZhengheMessage zhengheMessage = zhengheMessageService.get(requestId.getId());
		if(zhengheMessage!=null){
			zhengheMessage.setStatus("1");
			zhengheMessageService.save(zhengheMessage);
		}*/
		
		return buildSuccessResultInfo(ZhengheConstance.BASE_SUCCESS_CODE);
	}
	
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述: 系统消息</br>
	 * 开发人员：chenx</br>
	 * 创建时间：2015年11月26日</br>
	 * @return
	 */
	@ApiOperation(value="系统消息",notes="传入用户id及用户类型，返回系统消息")
	@RequestMapping(value="/systemMessage",method=RequestMethod.POST)	
	@ApiResponses({
		@ApiResponse(code=ZhengheConstance.BASE_SUCCESS_CODE,message="success",response=String.class),
		@ApiResponse(code=ZhengheConstance.param_fault,message="参数格式不正确",response=String.class)
	})
	public ResponseEntity<BaseResult> systemMessage(@ApiParam(name="id,type",value="用户id,类型必填,type:1->患者,2->医生")
			@RequestBody RequestMessage requestMessage){
		
		if(!StringUtils.hasText(requestMessage.getId())||!StringUtils.hasText(requestMessage.getType())){
			buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		
		JSONArray jsonArray = new JSONArray();
		List<ZhengheMessage> messagesList = null;
		/*if("2".equals(requestMessage.getType())){
			messagesList = zhengheMessageService.findMessagesByDoctorId(requestMessage.getId());
		}else if("1".equals(requestMessage.getType())){
			messagesList = zhengheMessageService.findMessagesByPatientId(requestMessage.getId());
		}*/
		ZhengheMessage mm = new ZhengheMessage();
		mm.setDifferentiateId(requestMessage.getType());
		messagesList = zhengheMessageService.findList(mm);
		
		
		for(ZhengheMessage message:messagesList){
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", message.getId());
			jsonObject.put("title", message.getTitle());
			//jsonObject.put("url", getBaseContextPath()+message.getContent());
			jsonObject.put("url", "http://wangwang.taobao.com/");
			jsonObject.put("date", DateUtils.formatDate(message.getCreateDate(),"yyyy-MM-dd"));
			if("1".equals(message.getStatus())){
				jsonObject.put("status","已读");
			}else if("0".equals(message.getStatus())){
				jsonObject.put("status", "未读");
			}
			jsonArray.add(jsonObject);
		}
		
		return buildSuccessResultInfo(jsonArray.size()>0?jsonArray:null);
	}
	
	/**
	 * (医生||患者)
	 * 方法名: </br>
	 * 详述: 删除好友</br>
	 * 开发人员：chenx</br>
	 * 创建时间：2015年11月26日</br>
	 * @return
	 */
	@ApiOperation(value="删除好友",notes="传入医生id和患者id,解除两个人的好友关系(删除医生,删除患者)")
	@RequestMapping(value="/deleteFriend",method=RequestMethod.POST)	
	@ApiResponses({
		@ApiResponse(code=ZhengheConstance.BASE_SUCCESS_CODE,message="success",response=String.class),
		@ApiResponse(code=ZhengheConstance.param_fault,message="参数格式不正确",response=String.class),
		@ApiResponse(code=ZhengheConstance.not_friend,message="对方并不是您的好友",response=String.class),
	})
	@Transactional
	public ResponseEntity<BaseResult> deleteFriend(@ApiParam(name="doctorId,verifyContent,patientId",value="医生id和患者id必填,验证信息就不要填了哈")
			@RequestBody RequestFriendMessage requestFriendMessage){
		
		if(!StringUtils.hasText(requestFriendMessage.getDoctorId())||!StringUtils.hasText(requestFriendMessage.getPatientId())){
			buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		
		String doctorId = requestFriendMessage.getDoctorId();
		String patientId = requestFriendMessage.getPatientId();
		
		List<ZhengheFriend> friendList = zhengheFriendService.findFriendByDoctorIdAndPatientId(doctorId, patientId);
		ZhengheFriend friend = friendList.get(0);
		if(friend==null){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		if(!"1".equals(friend.getStatus())){
			return buildFailedResultInfo(ZhengheConstance.not_friend);
		}
		//friend.setStatus("2");
		//zhengheFriendService.save(friend);
		
		ZhengheGroup group = zhengheGroupService.get(friend.getGroupingId());
		group.getCount();
		int count = Integer.parseInt(group.getCount());
		count--;
		group.setCount(String.valueOf(count));
		zhengheGroupService.save(group);
		
		zhengheFriendService.delete(friend);
		
		return buildSuccessResultInfo(ZhengheConstance.BASE_SUCCESS_CODE);
	}
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述: 专家推荐</br>
	 * 开发人员：chenx</br>
	 * 创建时间：2015年11月25日</br>
	 * @return
	 */
	@ApiOperation(value="专家推荐",notes="无需传参,返回三位推荐的专家")
	@RequestMapping(value="/expertRecommend",method=RequestMethod.POST)
	public ResponseEntity<BaseResult> expertRecommend(){
		
		ZhengheDoctor doctor = new ZhengheDoctor();
		doctor.setType("2");
		
		List<ZhengheDoctor> doctorList = zhengheDoctorService.findDoctorByTypeAndTop3(doctor);
		
		return buildSuccessResultInfo(doctorListToJson(doctorList));
	}

	/**
	 * 
	 * 方法名: </br>
	 * 详述: </br>
	 * 开发人员：chenx</br>
	 * 创建时间：2015年11月25日</br>
	 * @param requestDoctor
	 * @return
	 */
	@ApiOperation(value="保存资料",notes="传入医生的id及修改后的内容,前端把修改后的专业领域或个人简介传过来")
	@RequestMapping(value="/saveInformation",method=RequestMethod.POST)
	@ApiResponse(code=ZhengheConstance.param_fault,message="参数格式不正确",response=String.class)
	public ResponseEntity<BaseResult> saveInformation(@ApiParam(name="id,professionalField,intro",value="医生id必填")
			@RequestBody RequestDoctor requestDoctor){
		
		if(!StringUtils.hasText(requestDoctor.getId())){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}

		ZhengheDoctor doctor = zhengheDoctorService.get(requestDoctor.getId());
		
		if(StringUtils.hasText(requestDoctor.getProfessionalField())){
			doctor.setProfessionalField(requestDoctor.getProfessionalField());
		}
		
		if(StringUtils.hasText(requestDoctor.getIntro())){
			doctor.setIntro(requestDoctor.getIntro());
		}
		
		zhengheDoctorService.save(doctor);
		
		return buildSuccessResultInfo(ZhengheConstance.BASE_SUCCESS_CODE);
	}
	
	
	/**
	 * 
	 * 方法名:</br>
	 * 详述:个人中心  </br>
	 * 开发人员：chenx</br>
	 * 创建时间：2015年11月19日</br>
	 * @param requestId
	 * @return
	 */
	@ApiOperation(value="详细资料",notes="根据医生id返回医生详细资料")
	@RequestMapping(value="/doctorDetails",method=RequestMethod.POST)
	@ApiResponses({
		@ApiResponse(code=ZhengheConstance.param_fault,message="参数格式不正确",response=String.class),
		@ApiResponse(code = ZhengheConstance.userId_is_null, message = "用户id不存在", response = String.class),
	})
	public ResponseEntity<BaseResult> doctorDetails(@ApiParam(required=true,name="id",value="医生id必填")
			@RequestBody RequestId requestId){
		
		if(!StringUtils.hasText(requestId.getId())){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		
		ZhengheDoctor doctor = zhengheDoctorService.get(requestId.getId());
		
		if(doctor==null){
			return buildFailedResultInfo(ZhengheConstance.userId_is_null);
		}
		
		return buildSuccessResultInfo(doctorToJson(doctor));
	}

	
	/**
	 * 
	 * 方法名: </br>
	 * 详述: </br>
	 * 开发人员：chenx</br>
	 * 创建时间：2015年11月17日</br>
	 * @param requestCollect
	 * @return
	 */
	@ApiOperation(value="点击收藏/取消收藏",notes="传入用户id,用户类型(患者||医生)和要收藏的东西id,东西的类型(文章||药品)")
	@RequestMapping(value="/collectThing",method=RequestMethod.POST)
	public ResponseEntity<BaseResult> collectThing(@ApiParam(required=true,name="userId,userType,thingId,thingType,status",
			value="userType:1->患者,2->医生;thingType:类型->a文章,b->药品,注意(status:1->收藏,0->取消收藏")@RequestBody RequestCollect requestCollect){
		
		if(!StringUtils.hasText(requestCollect.getUserId())||!StringUtils.hasText(requestCollect.getUserId())||!StringUtils.hasText(requestCollect.getUserId())
					||!StringUtils.hasText(requestCollect.getUserId())||!StringUtils.hasText(requestCollect.getStatus())){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		
		ZhengheCollect collect = new ZhengheCollect();
		
		String userType = requestCollect.getUserType();
		String userId = requestCollect.getUserId();
		if("1".equals(userType)){
			collect.setPatientId(userId);
			collect.setType("1");
		}else if("2".equals(userType)){
			collect.setDoctorId(userId);
			collect.setType("2");
		}
		
		String thingType = requestCollect.getThingType();
		String thingId = requestCollect.getThingId();
		if("a".equals(thingType)){
			collect.setArticleId(thingId);
		}else if("b".equals(thingType)){
			collect.setProductId(thingId);
		}
		collect.setCreateDate(new Date());
		
		if("1".equals(requestCollect.getStatus())){
			zhengheCollectService.save(collect);
		}else if("0".equals(requestCollect.getStatus())){
			zhengheCollectService.del(collect);
		}
		
		
		return buildSuccessResultInfo(ZhengheConstance.BASE_SUCCESS_CODE);
	}
	
	
	/** 
	 * 方法名: </br>
	 * 详述:知识</br>
	 * 开发人员：陈协</br>
	 * 创建时间：2015年11月17日</br>
	 * @param 
	 * @return
	 */
	@ApiOperation(value="文章内容",notes="根据文章的id返回文章内容,[如果用户已登录,则要传入用户id和类型,后台判断该用户是否收藏该文章(1->已收藏,0->未收藏)]")
	@RequestMapping(value="/articleDetails",method=RequestMethod.POST)
	@ApiResponse(code=ZhengheConstance.param_fault,message="参数格式不正确",response=String.class)
	public ResponseEntity<BaseResult> articleDetails(@ApiParam(required=true,name="userId,userType,articleId",
		value="文章id必填,userType:1->患者,2->医生;") @RequestBody RequestArticle requestArticle){	
		
		if(!StringUtils.hasText(requestArticle.getArticleId())){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		
		ZhengheCollect collect = new ZhengheCollect();
		collect.setArticleId(requestArticle.getArticleId());

		String userType = requestArticle.getUserType();
		String userId = requestArticle.getUserId();
		
		List<ZhengheCollect> collectList = null;
		
		if(StringUtils.hasText(userType)&&StringUtils.hasText(userId)){
			if("1".equals(userType)){
				collect.setPatientId(userId);
				collect.setType("1");
			}else if("2".equals(userType)){
				collect.setDoctorId(userId);
				collect.setType("2");
			}
			
			collectList = zhengheCollectService.findList(collect);
		}
		
		ZhengheArticle article = zhengheArticleService.get(requestArticle.getArticleId());
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", article.getId());
		jsonObject.put("title", article.getTitle());
		jsonObject.put("createDate", DateUtils.formatDate(article.getCreateDate(),"yyyy-MM-dd"));
		if(!article.getAvatar().startsWith("http")){
			jsonObject.put("avatar",getBaseContextPath()+article.getAvatar());
		}else{
			jsonObject.put("avatar",article.getAvatar());
		}
		jsonObject.put("content", article.getContent());
		jsonObject.put("publish",article.getPublish());
		jsonObject.put("url", "https://www.baidu.com/");
		
		if(collectList==null||collectList.size()==0){
			jsonObject.put("isCollect", "0");
		}else{
			jsonObject.put("isCollect", "1");
		}
		
		return buildSuccessResultInfo(jsonObject);
	}
	

	/** 
	 * 方法名: </br>
	 * 详述:知识</br>
	 * 开发人员：陈协</br>
	 * 创建时间：2015年11月17日</br>
	 * @param 
	 * @return
	 */
	@ApiOperation(value="频道类型",notes="传频道的id,根据所传的频道返回相应的文章列表")
	@RequestMapping(value="/knowledgeWiki",method=RequestMethod.POST)
	@ApiResponse(code=ZhengheConstance.param_fault,message="参数格式不正确",response=String.class)
	public ResponseEntity<BaseResult> knowledgeWiki(@ApiParam(required=true,name="id",value="频道的id必填")
			@RequestBody RequestId requestId){	
		
		if(!StringUtils.hasText(requestId.getId())){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}

		List<ZhengheArticle> articleList = zhengheArticleService.findArticleByClassifyId(requestId.getId());

		JSONArray articleArray = new JSONArray();
		for(ZhengheArticle article:articleList){
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", article.getId());
			jsonObject.put("title", article.getTitle());
			if(!article.getAvatar().startsWith("http")){
				jsonObject.put("avatar",getBaseContextPath()+article.getAvatar());
			}else{
				jsonObject.put("avatar",article.getAvatar());
			}

			articleArray.add(jsonObject);
		}
		
		return buildSuccessResultInfo(articleArray.size()>0?articleArray:null);
	}
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述: 返回排序后的前3张轮播图</br>
	 * 开发人员：chenx</br>
	 * 创建时间：2015年11月25日</br>
	 * @return
	 */
	@ApiOperation(value="文章轮播图",notes="返回3张文章轮播图，无需传参")
	@RequestMapping(value="/articleCarousel",method=RequestMethod.POST)
	@ApiResponse(code=ZhengheConstance.param_fault,message="参数格式不正确",response=String.class)
	public ResponseEntity<BaseResult> articleCarousel(){	
		
		List<ZhengheCarousel> carouselList =  zhengheCarouselService.findCarousel();
		
		JSONArray carouselArray = new JSONArray();
		for(ZhengheCarousel carousel:carouselList){
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id",carousel.getArticleId());
			if(!carousel.getAvatar().startsWith("http")){
				jsonObject.put("avatar", getBaseContextPath()+carousel.getAvatar());
			}else{
				jsonObject.put("avatar", carousel.getAvatar());
			}

			jsonObject.put("title", zhengheArticleService.get(carousel.getArticleId()).getTitle());
			carouselArray.add(jsonObject);
		}

		return buildSuccessResultInfo(carouselArray.size()>0?carouselArray:null);
	}

	/** 
	 * 方法名: </br>
	 * 详述:知识</br>
	 * 开发人员：陈协</br>
	 * 创建时间：2015年11月16日</br>
	 * @param 
	 * @return
	 */
	@ApiOperation(value="频道列表",notes="返回所有的频道无需传参")
	@RequestMapping(value="/knowledgeWikiInit",method=RequestMethod.POST)
	public ResponseEntity<BaseResult> knowledgeWikiInit(){
		
		List<ZhengheArticleClassify> articleClassifyList = zhengheArticleClassifyService.findList(null);
		
		JSONArray articleClassifyArray = new JSONArray();
		for(ZhengheArticleClassify articleClassify:articleClassifyList){
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", articleClassify.getId());
			jsonObject.put("classifyName", articleClassify.getClassifyName()==null?"":articleClassify.getClassifyName());
			articleClassifyArray.add(jsonObject);
		}
		
		return buildSuccessResultInfo(articleClassifyArray);
	}
	
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述: </br>
	 * 开发人员：chenx</br>
	 * 创建时间：2015年12月21日</br>
	 * @param requestId
	 * @return
	 */
	@ApiOperation(value="清除通知",notes="根据患者的id,把他发给医生的申请屏蔽掉")
	@RequestMapping(value="/clearMessage",method=RequestMethod.POST)
	@ApiResponses({
		@ApiResponse(code=ZhengheConstance.BASE_SUCCESS_CODE,message="success",response=String.class),
		@ApiResponse(code=ZhengheConstance.param_fault,message="参数格式不正确",response=String.class)
	})
	public ResponseEntity<BaseResult> clearMessage(@ApiParam(name="id",value="患者的id")
			@RequestBody RequestId requestId){
		
		if(!StringUtils.hasText(requestId.getId())){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}

		List<ZhengheFriend> friendList = zhengheFriendService.findFriendByPatientIdAndPatientStatus(requestId.getId(),"1");
		
		for(ZhengheFriend friend:friendList){
			friend.setPatientStatus("0");
			zhengheFriendService.save(friend);
		}

		return buildSuccessResultInfo(ZhengheConstance.BASE_SUCCESS_CODE);
	}
	

	/** 
	 * 方法名: </br>
	 * 详述:问诊</br>
	 * 开发人员：陈协</br>
	 * 创建时间：2015年11月16日</br>
	 * @param 
	 * @return
	 */
	@ApiOperation(value="申请通知",notes="根据患者id返回通知")
	@RequestMapping(value="/applyMessage",method=RequestMethod.POST)
	@ApiResponse(code=ZhengheConstance.param_fault,message="参数格式不正确",response=String.class)
	public ResponseEntity<BaseResult> applyMessage(@ApiParam(required = true, name = "id", value = "患者id必填")
		@RequestBody RequestId requestId ){
		
		if(!StringUtils.hasText(requestId.getId())){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		
		List<ZhengheFriend> friendList = zhengheFriendService.findFriendByPatientIdAndPatientStatus(requestId.getId(),"1");
		
		JSONArray jsonArray = new JSONArray();
		for(ZhengheFriend friend:friendList){
			ZhengheDoctor doctor = zhengheDoctorService.get(friend.getDoctorId());
			JSONObject jsonObject = new JSONObject();
			if(!doctor.getAvatar().startsWith("http")){
				jsonObject.put("avatar", getBaseContextPath()+doctor.getAvatar());
			}else{
				jsonObject.put("avatar", doctor.getAvatar());
			}

			jsonObject.put("doctor", doctor.getDoctorName());
			jsonObject.put("content", friend.getVerifyContent());
			if("1".equals(friend.getStatus())){
				jsonObject.put("status", "已同意");
			}else if("2".equals(friend.getStatus())){
				jsonObject.put("status", "待同意");
			}else if("0".equals(friend.getStatus())){
				jsonObject.put("status", "已拒绝");
			}
			jsonArray.add(jsonObject);
		}
		return buildSuccessResultInfo(jsonArray.size()>0?jsonArray:null);
	}
	
	
	/** 
	 * 方法名: </br>
	 * 详述:问诊</br>
	 * 开发人员：陈协</br>
	 * 创建时间：2015年11月16日</br>
	 * @param 
	 * @return
	 */
	@ApiOperation(value="我的医生",notes="根据患者id返回医生")
	@RequestMapping(value="/myDoctor",method=RequestMethod.POST)
	@ApiResponse(code=ZhengheConstance.param_fault,message="参数格式不正确",response=String.class)
	public ResponseEntity<BaseResult> myDoctor(@ApiParam(required = true, name = "id", value = "患者id必填")
		@RequestBody RequestId requestId ){
		
		if(!StringUtils.hasText(requestId.getId())){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		
		List<ZhengheFriend> friendList = zhengheFriendService.findFriendByPatientIdAndStatus(requestId.getId(),"1");
		List<ZhengheDoctor> doctorList = new ArrayList<ZhengheDoctor>();
		for(ZhengheFriend friend:friendList){
			ZhengheDoctor doctor = zhengheDoctorService.get(friend.getDoctorId());
			if(doctor!=null){
				doctorList.add(doctor);
			}
		}
		return buildSuccessResultInfo(doctorListToJson(doctorList));
	}
	

	/** 
	 * 方法名: </br>
	 * 详述:扫一扫</br>
	 * 开发人员：陈协</br>
	 * 创建时间：2015年11月16日</br>
	 * @param 
	 * @return
	 */
	@ApiOperation(value="申请咨询",notes="接收医生id,验证和患者id消息,把验证请求存进数据库(status:1->对方已是您的好友;2->发送申请成功(之前已经发送过一次申请);3->发送申请成功)")
	@RequestMapping(value="/applyConsult",method=RequestMethod.POST)
	@ApiResponses({
		@ApiResponse(code=ZhengheConstance.BASE_SUCCESS_CODE,message="success",response=String.class),
		@ApiResponse(code=ZhengheConstance.param_fault,message="参数格式不正确",response=String.class),
		@ApiResponse(code=ZhengheConstance.good_friend,message="对方已是您的好友",response=String.class),
	})
	public ResponseEntity<BaseResult> applyConsult(@ApiParam(required = true, name ="doctorId,verifyContent,patientId", value = "医生id和患者id必填")
			@RequestBody RequestFriendMessage friendMessage){
		
		String doctorId = friendMessage.getDoctorId();
		String patientId = friendMessage.getPatientId();
		
		if(!StringUtils.hasText(doctorId)||!StringUtils.hasText(patientId)){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		
		List<ZhengheFriend> friendList = zhengheFriendService.findFriendByDoctorIdAndPatientId(doctorId, patientId);
		JSONObject jsonObject = new JSONObject();
		if(friendList.size()>0){
			ZhengheFriend friend = friendList.get(0);
			if("1".equals(friend.getStatus())){
				jsonObject.put("status", "1");
				return buildSuccessResultInfo(jsonObject);
			}
			friend.setStatus("2");
			friend.setPatientStatus("1");
			friend.setDoctorStatus("1");
			friend.setVerifyContent(friendMessage.getVerifyContent());
			friend.setUpdateDate(new Date());
			friend.setIsNewRecord(false);
			zhengheFriendService.save(friend);
			jsonObject.put("status", "2");
			return buildSuccessResultInfo(jsonObject);
		}
		
		ZhengheFriend friend = new ZhengheFriend();
		friend.setDoctorId(friendMessage.getDoctorId());
		friend.setVerifyContent(friendMessage.getVerifyContent());
		friend.setPatientId(friendMessage.getPatientId());
		friend.setCreateDate(new Date());
		friend.setStatus("2");
		friend.setPatientStatus("1");
		friend.setDoctorStatus("1");
		zhengheFriendService.save(friend);
		jsonObject.put("status", "3");
		return buildSuccessResultInfo(jsonObject);
	}
	
	
	
	/** 
	 * 方法名: </br>
	 * 详述:扫一扫</br>
	 * 开发人员：陈协</br>
	 * 创建时间：2015年11月16日</br>
	 * @param 
	 * @return
	 */
	@ApiOperation(value="扫码找医",notes="根据医生id返回医生详情")
	@RequestMapping(value="/scanCode",method=RequestMethod.POST)
	@ApiResponse(code=ZhengheConstance.param_fault,message="参数格式不正确",response=String.class)
	public ResponseEntity<BaseResult> scanCode(@ApiParam(required = true, name = "Id", value = "医生id必填")
			@RequestBody RequestId requestId){
		
		if(!StringUtils.hasText(requestId.getId())){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		
		ZhengheDoctor doctor = zhengheDoctorService.get(requestId.getId());
		return buildSuccessResultInfo(doctorToJson(doctor));
	}
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述: </br>
	 * 开发人员：chenx</br>
	 * 创建时间：2015年12月12日</br>
	 * @return
	 */
	@ApiOperation(value="预约说明",notes="返回预约说明,无需传参")
	@RequestMapping(value="/reserveExplain",method=RequestMethod.POST)
	public ResponseEntity<BaseResult> reserveExplain(){
		return buildSuccessResultInfo(getValue("reserve.explain"));
	}
	
	/** 
	 * 方法名: </br>
	 * 详述:寻医</br>
	 * 开发人员：陈协</br>
	 * 创建时间：2015年11月16日</br>
	 * @param 
	 * @return
	 */
	@ApiOperation(value="联系电话",notes="返回预约热线与联系客服电话,无需传参(phone为预约热线;service为联系客服)")
	@RequestMapping(value="/phone",method=RequestMethod.POST)
	public ResponseEntity<BaseResult> phone(){
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("phone", getValue("reserve.phone"));
		jsonObj.put("service", getValue("reserve.service"));
		return buildSuccessResultInfo(jsonObj);
	}
	
	
	/** 
	 * 方法名: </br>
	 * 详述:寻医</br>
	 * 开发人员：陈协</br>
	 * 创建时间：2015年11月16日</br>
	 * @param 
	 * @return
	 */
	@ApiOperation(value="专家会诊",notes="无需传参")
	@RequestMapping(value="/expertConsultation",method=RequestMethod.POST)
	public ResponseEntity<BaseResult> expertConsultation(){
		List<ZhengheDoctor> doctorList = zhengheDoctorService.findDoctorByType("2");
		return buildSuccessResultInfo(doctorListToJson(doctorList));
	}
	

	/** 
	 * 方法名: </br>
	 * 详述:寻医</br>
	 * 开发人员：陈协</br>
	 * 创建时间：2015年11月12日</br>
	 * 心情：This is the most disgusting I have written the code -_-
	 * @param 
	 * @return
	 */
	@ApiOperation(value="搜索医生",notes="根据(医生名,科室,职称,医院)关键字||(一级科室id,二级科室id)||(省,市的id)搜索医生")
	@RequestMapping( value="/findDoctorByCriteria",method = RequestMethod.POST)
	@ApiResponse(code=ZhengheConstance.param_fault,message="参数格式不正确",response=String.class)
	public ResponseEntity<BaseResult> findDoctorByCriteria(@ApiParam(required = true, name = "keyword,cityId,provincialId,firstDepartmentsId,secondDepartmentsId", 
			value = "全部参数可为空")@RequestBody RequestCriteria requestCriteria){
		
			String keyword = requestCriteria.getKeyword();
			String cityId = requestCriteria.getCityId();
			String provincialId = null;
			if(!StringUtils.hasText(cityId)){
				provincialId = requestCriteria.getProvincialId();
			}

			String secondDepartmentsId = requestCriteria.getSecondDepartmentsId();
			String firstDepartmentsId = null;
			if(!StringUtils.hasText(secondDepartmentsId)){
				firstDepartmentsId = requestCriteria.getFirstDepartmentsId();
			}
			
			Map<String,String> map = new HashMap<String,String>();
			map.put("keyword", keyword);
			map.put("cityId", cityId);
			map.put("provincialId", provincialId);
			map.put("firstDepartmentsId", firstDepartmentsId);
			map.put("secondDepartmentsId", secondDepartmentsId);
			
			List<DoctorZhenghe> doctorList = zhengheDoctorService.findDoctorByCriteria(map);
			
			
			for(DoctorZhenghe doctor:doctorList){
				if(!doctor.getAvatar().startsWith("http")){
					doctor.setAvatar(getBaseContextPath()+doctor.getAvatar());
				}

			}

			return buildSuccessResultInfo(doctorList);
	}
	
	
	
	@ApiOperation(value="二级科室",notes="根据传入的一级科室id返回下属的全部二级科室")
	@RequestMapping(value="/findSecondDepartments",method=RequestMethod.POST)
	@ApiResponse(code=ZhengheConstance.param_fault,message="参数格式不正确",response=String.class)
	public ResponseEntity<BaseResult> findSecondDepartments(@ApiParam(name="id",value="一级科室id,必填")
			@RequestBody RequestId requestId){
		
		if(!StringUtils.hasText(requestId.getId())){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		
		List<ZhengheDepartments> list = zhengheDepartmentsService.findBySDepartmentsId(requestId.getId());
		List<DepartmentsZhenghe> departmentList = new ArrayList<DepartmentsZhenghe>();

		for(ZhengheDepartments department:list){
			DepartmentsZhenghe departmentsZhenghe = new DepartmentsZhenghe();
			departmentsZhenghe.setId(department.getId());
			departmentsZhenghe.setDepartmentsName(department.getDepartmentsName()==null?"":department.getDepartmentsName());
			if(department.getAvatar()==null){
				departmentsZhenghe.setAvatar("");
			}else {
				if(!department.getAvatar().startsWith("http")){
					departmentsZhenghe.setAvatar(getBaseContextPath()+department.getAvatar());
				}else{
					departmentsZhenghe.setAvatar(department.getAvatar());
				}
			}

			departmentList.add(departmentsZhenghe);
		}

		return buildSuccessResultInfo(departmentList);
	}
	
	/** 
	 * 方法名: </br>
	 * 详述:寻医</br>
	 * 开发人员：陈协</br>
	 * 创建时间：2015年11月12日</br>
	 * @param 
	 * @return
	 */
	@ApiOperation(value="一级科室",notes="返回全部一级科室,无需传参")
	@RequestMapping( value="/findFirstDepartments",method = RequestMethod.POST)
	public ResponseEntity<BaseResult> findFirstDepartments(){
		List<ZhengheDepartments> list = zhengheDepartmentsService.findByType("1");
		List<DepartmentsZhenghe> departmentList = new ArrayList<DepartmentsZhenghe>();
		
		for(ZhengheDepartments department:list){
			DepartmentsZhenghe departmentsZhenghe = new DepartmentsZhenghe();
			departmentsZhenghe.setId(department.getId());
			departmentsZhenghe.setDepartmentsName(department.getDepartmentsName()==null?"":department.getDepartmentsName());
			if(department.getAvatar()==null){
				departmentsZhenghe.setAvatar("");
			}else{
				if(!department.getAvatar().startsWith("http")){
					departmentsZhenghe.setAvatar(getBaseContextPath()+department.getAvatar());
				}else{
					departmentsZhenghe.setAvatar(department.getAvatar());
				}
			}


			departmentList.add(departmentsZhenghe);
		}
		
		return buildSuccessResultInfo(departmentList);
	}
	
	
	
	/** 
	 * 方法名: </br>
	 * 详述:我的诊室</br>
	 * 开发人员：陈协</br>
	 * 创建时间：2015年11月12日</br>
	 * @param 
	 * @return
	 */
	@ApiOperation(value="我的名片",notes="根据医生id返回医生二维码")
	@RequestMapping( value="/businessCard",method = RequestMethod.POST)
	@ApiResponses({
		@ApiResponse(code=ZhengheConstance.param_fault,message="参数格式不正确",response=String.class),
		@ApiResponse(code = ZhengheConstance.userId_is_null, message = "用户id不存在", response = String.class),
	})
	public ResponseEntity<BaseResult> businessCard(@ApiParam(required = true, name = "id", value = "医生id必填")
	@RequestBody RequestId requestId){
		
		if(!StringUtils.hasText(requestId.getId())){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		
		ZhengheDoctor zhengheDoctor = zhengheDoctorService.get(requestId.getId());
		if(zhengheDoctor==null){
			return buildFailedResultInfo( ZhengheConstance.userId_is_null);
		}
		
		JSONObject josnObject = doctorToJson(zhengheDoctor);
		if(zhengheDoctor.getWmUrl()==null){
			josnObject.put("wmUrl","");
		}else{
			if(!zhengheDoctor.getAvatar().startsWith("http")){
				josnObject.put("wmUrl",getBaseContextPath()+zhengheDoctor.getWmUrl());
			}else{
				josnObject.put("wmUrl",zhengheDoctor.getWmUrl());
			}
		}

		return buildSuccessResultInfo(josnObject);
	}
	
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述: </br>
	 * 开发人员：chenx</br>
	 * 创建时间：2015年12月16日</br>
	 * @param requestId
	 * @return
	 */
	@ApiOperation(value="患者分组",notes="根据医生id返回相应的分组和患者")
	@RequestMapping(value="/patientGroup",method=RequestMethod.POST)
	@ApiResponse(code=ZhengheConstance.param_fault,message="参数格式不正确",response=String.class)
	public ResponseEntity<BaseResult> patientGroup(@ApiParam(name="id",value="医生id必填")
			@RequestBody RequestId requestId){
		
		String doctorId = requestId.getId();
		
		if(!StringUtils.hasText(doctorId)){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		
		
		List<ZhengheGroup> groupList = zhengheGroupService.findGroupByDoctorId(doctorId);

		JSONArray groupArray = new JSONArray();
		
		for(ZhengheGroup group:groupList){
			JSONObject groupObject = new JSONObject();
			groupObject.put("id", group.getId());
			groupObject.put("grouping",group.getGroupName());
			List<ZhengheFriend> friendList = zhengheFriendService.findFriendByGroupIdAndStatus(group.getId(), "1");
			JSONArray friendArray = new JSONArray();
			for(ZhengheFriend friend:friendList){
				ZhenghePatient patient = zhenghePatientService.get(friend.getPatientId());
				if(patient!=null){
					JSONObject jsonObject = patientToJson(patient);
					if(jsonObject!=null){
						jsonObject.put("groupName", group.getGroupName());
						jsonObject.put("groupId", group.getId());
					}
					friendArray.add(jsonObject);
				}
			}
			groupObject.put("name",friendArray);
			groupObject.put("count", group.getCount());
			groupArray.add(groupObject);
		}
		
		return buildSuccessResultInfo(groupArray);
	}
	
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述: </br>
	 * 开发人员：chenx</br>
	 * 创建时间：2015年12月16日</br>
	 * @param requestMoveGroup
	 * @return
	 */
	@ApiOperation(value="移动分组",notes="接收患者原来的分组的id、患者的id和新的分组的id，把患者移动到新的分组里面")
	@RequestMapping( value="/moveGroup",method = RequestMethod.POST)
	@ApiResponses({
		@ApiResponse(code=ZhengheConstance.BASE_SUCCESS_CODE,message="success",response=String.class),
		@ApiResponse(code = ZhengheConstance.BASE_FAIL_CODE, message = "失败", response = String.class),
		@ApiResponse(code=ZhengheConstance.param_fault,message="参数格式不正确",response=String.class),
		@ApiResponse(code=ZhengheConstance.not_friend,message="对方并不是您的好友",response=String.class),
		@ApiResponse(code=ZhengheConstance.not_current_user,message="目标组id不是当前用户的，不能乱来",response=String.class),
		
	})
	@Transactional
	public ResponseEntity<BaseResult> moveGroup(@ApiParam(required = true, name = "oldGroupId,patientId,newGroupId", value = "入参必填")
 		@RequestBody RequestMoveGroup requestMoveGroup){
		
		String oldGroupId = requestMoveGroup.getOldGroupId();
		String patientId = requestMoveGroup.getPatientId();
		String newGroupId = requestMoveGroup.getNewGroupId();
		
		if(Tool.strBlank(oldGroupId,patientId,newGroupId)){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		
		List<ZhengheFriend> friendList = zhengheFriendService.findFriendByPatientIdAndGroupId(patientId, oldGroupId);
		
		ZhengheFriend friend = friendList.get(0);
		if(!"1".equals(friend.getStatus())){
			return buildFailedResultInfo(ZhengheConstance.not_friend);
		}
		
		ZhengheGroup newGroup = zhengheGroupService.get(newGroupId);
		if(newGroup==null){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		
		if(!newGroup.getDoctorId().equals(friend.getDoctorId())){
			return buildFailedResultInfo(ZhengheConstance.not_current_user);
		}
		
		friend.setGroupingId(newGroupId);
		zhengheFriendService.save(friend);
		
		int count;
		ZhengheGroup group_old = zhengheGroupService.get(oldGroupId);
		if(group_old==null){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		count = Integer.parseInt(group_old.getCount());
		count--;
		group_old.setCount(count+"");
		zhengheGroupService.save(group_old);
		
		ZhengheGroup group_new = newGroup;
		count = Integer.parseInt(group_new.getCount());
		count++;
		group_new.setCount(count+"");
		zhengheGroupService.save(group_new);
		
		return buildSuccessResultInfo(ZhengheConstance.BASE_SUCCESS_CODE);
	}
		
		

	@ApiOperation(value="删除分组",notes="根据分组id删除分组,要删除的分组必须为空.为不为空后端会做判断,不为空时返回操作失败(我的患者是默认分组，不允许删除)")
	@RequestMapping( value="/deleteGroup",method = RequestMethod.POST)
	@ApiResponses({
		@ApiResponse(code=ZhengheConstance.BASE_SUCCESS_CODE,message="success",response=String.class),
		@ApiResponse(code = ZhengheConstance.BASE_FAIL_CODE, message = "失败", response = String.class),
		@ApiResponse(code=ZhengheConstance.param_fault,message="参数格式不正确",response=String.class)
	})
	public ResponseEntity<BaseResult> deleteGroup(@ApiParam(required = true, name = "id", value = "组id必填")
 		@RequestBody RequestId requestId){
		
		if(!StringUtils.hasText(requestId.getId())){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		
		ZhengheGroup group = zhengheGroupService.get(requestId.getId());
		
		if(!"0".equals(group.getCount())){
			return buildFailedResultInfo(ZhengheConstance.BASE_FAIL_CODE);
		}
		
		zhengheGroupService.delete(group);
		
		return buildSuccessResultInfo(ZhengheConstance.BASE_SUCCESS_CODE);
	}
	
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述: 分组管理</br>
	 * 开发人员：chenx</br>
	 * 创建时间：2015年11月19日</br>
	 * @param requestGroup
	 * @return
	 */
	@ApiOperation(value="保存|修改分组",notes="保存(新建||修改后)的分组,注意:不允许同名的分组")
	@RequestMapping( value="/saveGroup",method = RequestMethod.POST)
	@ApiResponses({
		@ApiResponse(code=ZhengheConstance.BASE_SUCCESS_CODE,message="success",response=String.class),
		@ApiResponse(code = ZhengheConstance.BASE_FAIL_CODE, message = "失败", response = String.class),
		@ApiResponse(code=ZhengheConstance.param_fault,message="参数格式不正确",response=String.class),
		@ApiResponse(code=ZhengheConstance.group_already_exist,message="分组已经存在",response=String.class)
	})
	public ResponseEntity<BaseResult> saveGroup(@ApiParam(required = true, name = "id,groupName,doctorId", value = "组名,医生id必填,注意了->组id不填时为添加")
 	@RequestBody RequestGroup requestGroup){
		
		if(Tool.strBlank(requestGroup.getGroupName(),requestGroup.getDoctorId())){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		
		List<ZhengheGroup> groupList = zhengheGroupService.findGroupByDoctorId(requestGroup.getDoctorId());
		ZhengheGroup sameNameGroup=null;
		for(ZhengheGroup zhengheGroup:groupList){
			if(zhengheGroup.getGroupName().equals(requestGroup.getGroupName().trim())){
				sameNameGroup=zhengheGroup;
				break;
			}
		}
		
		ZhengheGroup group=null;
		
		if(StringUtils.hasText(requestGroup.getId())){
			if(sameNameGroup!=null){
				if(sameNameGroup.getId().equals(requestGroup.getId())){
					group = sameNameGroup;
				}else{
					return buildFailedResultInfo(ZhengheConstance.group_already_exist);
				}
			}else{
				group = zhengheGroupService.get(requestGroup.getId());
			}
			group.setGroupName(requestGroup.getGroupName());
		}else{
			if(sameNameGroup!=null){
				return buildFailedResultInfo(ZhengheConstance.group_already_exist);
			}
			group = new ZhengheGroup();
			group.setGroupName(requestGroup.getGroupName());
			group.setDoctorId(requestGroup.getDoctorId());
			group.setCount("0");
		}
		
		zhengheGroupService.save(group);
		
		return buildSuccessResultInfo(ZhengheConstance.BASE_SUCCESS_CODE);
	}
	
	/** 
	 * 方法名: </br>
	 * 详述:我的诊室</br>
	 * 开发人员：陈协</br>
	 * 创建时间：2015年11月12日</br>
	 * @param 
	 * @return
	 */
	@ApiOperation(value="管理分组",notes="根据医生id返回患者分组")
	@RequestMapping( value="/manageGroup",method = RequestMethod.POST)
	@ApiResponses({
		@ApiResponse(code=ZhengheConstance.BASE_SUCCESS_CODE,message="success",response=String.class),
		@ApiResponse(code = ZhengheConstance.BASE_FAIL_CODE, message = "失败", response = String.class),
		@ApiResponse(code=ZhengheConstance.param_fault,message="参数格式不正确",response=String.class)
	})
	public ResponseEntity<BaseResult> manageGroup(@ApiParam(required = true, name = "id", value = "医生id必填")
 	@RequestBody RequestId requestId){
		
		if(!StringUtils.hasText(requestId.getId())){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		
		List<ZhengheGroup> list = zhengheGroupService.findGroupByDoctorId(requestId.getId());
		List<GroupZhenghe> groupList = new ArrayList<GroupZhenghe>();
		
		for(ZhengheGroup group:list){
			GroupZhenghe groupZhenghe = new GroupZhenghe();
			groupZhenghe.setId(group.getId());
			groupZhenghe.setGroupName(group.getGroupName());
			groupZhenghe.setCount(group.getCount());
			groupList.add(groupZhenghe);
		}
		
		return buildSuccessResultInfo(groupList);
	}
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述: </br>
	 * 开发人员：chenx</br>
	 * 创建时间：2015年12月16日</br>
	 * @param requestId
	 * @return
	 */
	@ApiOperation(value="忽略申请",notes="根据申请的id,把这条发给医生的申请屏蔽掉")
	@RequestMapping(value="/ignoreApply",method=RequestMethod.POST)
	@ApiResponses({
		@ApiResponse(code=ZhengheConstance.BASE_SUCCESS_CODE,message="success",response=String.class),
		@ApiResponse(code=ZhengheConstance.param_fault,message="参数格式不正确",response=String.class)
	})
	public ResponseEntity<BaseResult> ignoreApply(@ApiParam(name="id",value="申请的id必传")
	@RequestBody RequestId requestId){

		if(!StringUtils.hasText(requestId.getId())){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		
		ZhengheFriend friend = zhengheFriendService.get(requestId.getId());
		friend.setDoctorStatus("0");
		zhengheFriendService.save(friend);
		
		return buildSuccessResultInfo(ZhengheConstance.BASE_SUCCESS_CODE);
	}
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述: </br>
	 * 开发人员：chenx</br>
	 * 创建时间：2015年12月16日</br>
	 * @param requestId
	 * @return
	 */
	@ApiOperation(value="清除申请",notes="根据医生的id,把发给他的申请全部屏蔽掉")
	@RequestMapping(value="/clearApply",method=RequestMethod.POST)
	@ApiResponses({
		@ApiResponse(code=ZhengheConstance.BASE_SUCCESS_CODE,message="success",response=String.class),
		@ApiResponse(code=ZhengheConstance.param_fault,message="参数格式不正确",response=String.class)
	})
	public ResponseEntity<BaseResult> clearApply(@ApiParam(name="id",value="医生的id")
			@RequestBody RequestId requestId){
		
		if(!StringUtils.hasText(requestId.getId())){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}

		List<ZhengheFriend> friendList = zhengheFriendService.findFriendByDoctorIdAndDoctorStatus(requestId.getId(),"1");
		
		for(ZhengheFriend friend:friendList){
			friend.setDoctorStatus("0");
			zhengheFriendService.save(friend);
		}

		return buildSuccessResultInfo(ZhengheConstance.BASE_SUCCESS_CODE);
	}
	
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述: </br>
	 * 开发人员：chenx</br>
	 * 创建时间：2015年11月20日</br>
	 * @param requestId
	 * @return
	 */
	@ApiOperation(value="同意申请",notes="患者申请的消息的id")
	@RequestMapping(value="/agreeApply",method=RequestMethod.POST)
	@ApiResponses({
		@ApiResponse(code=ZhengheConstance.BASE_SUCCESS_CODE,message="success",response=String.class),
		@ApiResponse(code=ZhengheConstance.param_fault,message="参数格式不正确",response=String.class)
	})
	@Transactional
	public ResponseEntity<BaseResult> agreeApply(@ApiParam(name="id",value="患者申请的消息的id")
			@RequestBody RequestId requestId){
		
		if(!StringUtils.hasText(requestId.getId())){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		
		ZhengheFriend friend = zhengheFriendService.get(requestId.getId());
		
		if(friend==null)return buildFailedResultInfo(ZhengheConstance.param_fault);
		
		friend.setStatus("1");
		List<ZhengheGroup> groupList = zhengheGroupService.findGroupByDoctorId(friend.getDoctorId());
		
		for(ZhengheGroup group:groupList){
			if("我的患者".equals(group.getGroupName().trim())){
				
				int count = Integer.parseInt(group.getCount());
				count++;
				group.setCount(count+"");
				zhengheGroupService.save(group);
				
				friend.setGroupingId(group.getId());
				break;
			}
		}
		
		zhengheFriendService.save(friend);
		
		return buildSuccessResultInfo(ZhengheConstance.BASE_SUCCESS_CODE);
	}

	
	/** 
	 * 方法名: </br>
	 * 详述:我的诊室</br>
	 * 开发人员：陈协</br>
	 * 创建时间：2015年11月12日</br>
	 * @param 
	 * @return
	 */
	@ApiOperation(value="患者申请",notes="根据医生id返回患者申请")
	@RequestMapping( value="/patientApply",method = RequestMethod.POST)
	@ApiResponses({
		@ApiResponse(code=ZhengheConstance.BASE_SUCCESS_CODE,message="success",response=String.class),
		@ApiResponse(code = ZhengheConstance.BASE_FAIL_CODE, message = "失败", response = String.class),
		@ApiResponse(code=ZhengheConstance.param_fault,message="参数格式不正确",response=String.class)
	})
	public ResponseEntity<BaseResult> patientApply(@ApiParam(required = true, name = "id", value = "医生id必填")
 	@RequestBody RequestId requestId){
		
		if(!StringUtils.hasText(requestId.getId())){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}

		List<ZhengheFriend> list = zhengheFriendService.findFriendByDoctorIdAndDoctorStatus(requestId.getId(),"1");
		
		List<FriendZhenghe> friendsList = new ArrayList<FriendZhenghe>();
		
		for(ZhengheFriend friend:list){
			FriendZhenghe friendZhenghe = new FriendZhenghe();
			friendZhenghe.setId(friend.getId());
			ZhenghePatient patient = zhenghePatientService.get(friend.getPatientId());
			if(patient==null)continue;
			if(patient.getAvatar() == null){
				friendZhenghe.setAvatar("");
			}else {
				if(!patient.getAvatar().startsWith("http")){
					friendZhenghe.setAvatar(getBaseContextPath()+patient.getAvatar());
				}else{
					friendZhenghe.setAvatar(patient.getAvatar());
				}
			}

			friendZhenghe.setName(patient.getPatientName()==null?"":patient.getPatientName());
			friendZhenghe.setVerifyContent(friend.getVerifyContent());
			if("1".equals(friend.getStatus())){
				friendZhenghe.setStatus("已同意");
			}else if("2".equals(friend.getStatus())){
				friendZhenghe.setStatus("同意");
			}else if("0".equals(friend.getStatus())){
				friendZhenghe.setStatus("已拒绝");
			}
			friendZhenghe.setGender(patient.getGender()==null?"":patient.getGender().equals("1")?"男":"女");
			friendZhenghe.setAge(patient.getAge()==null?"":patient.getAge());
			
			String address = "";
			if(patient.getProvincialId()!=null){
				ZhengheProvincial provincial = zhengheProvincialService.get(patient.getProvincialId());
				if(provincial!=null){
					address+=provincial.getName();
				}
			}

			if(patient.getCityId()!=null){
				ZhengheCity city = zhengheCityService.get(patient.getCityId());
				if(city!=null){
					address+=city.getCityName();
				}
			}
			friendZhenghe.setAddress(address);
			
			friendsList.add(friendZhenghe);
		}
		
		return buildSuccessResultInfo(friendsList);
	}
	

	/**
	 * 方法名:loginDoctor</br>
	 * 详述:医生登录 </br>
	 * 开发人员：陈协</br>
	 * 创建时间：2015年11月11日</br>
	 * @param 
	 * @return
	 */
	@ApiOperation(value = "登录", notes = "用户登录")
	@RequestMapping(value = "/loginDoctor", method = RequestMethod.POST)
	@ApiResponses({
		@ApiResponse(code=ZhengheConstance.BASE_SUCCESS_CODE,message="success",response=String.class),
		@ApiResponse(code = ZhengheConstance.BASE_FAIL_CODE, message = "失败", response = String.class),
		@ApiResponse(code = ZhengheConstance.ISNULLMOBIE, message = "手机号不能为空", response = String.class),
		@ApiResponse(code = ZhengheConstance.ISNULLPASSWORD, message = "密码不能为空", response = String.class),
		@ApiResponse(code = ZhengheConstance.ISEXISITMOBILE, message = "手机号不存在", response = String.class),
		@ApiResponse(code = ZhengheConstance.ISPASSWORDFAIL, message = "密码错误", response = String.class),
		@ApiResponse(code = ZhengheConstance.ISTHERETOKEN, message = "tokenName不能为空", response = String.class)
	})
	public ResponseEntity<BaseResult> loginDoctor(@ApiParam(required = true, name = "RequestLoginUser", value = "除用户类型外，其他参全部必填")
		@RequestBody RequestLoginUser requestLoginUser) {
		String phone = requestLoginUser.getPhone();
		String password = requestLoginUser.getPassword();
		String tokenName = requestLoginUser.getTokenName();
		if (!StringUtils.hasText(phone)) {
			return buildFailedResultInfo(ZhengheConstance.ISNULLMOBIE);
		}
		if (!StringUtils.hasText(password)) {
			return buildFailedResultInfo(ZhengheConstance.ISNULLPASSWORD);
		}
		if (!StringUtils.hasText(tokenName)) {
			return buildFailedResultInfo(ZhengheConstance.ISTHERETOKEN);
		}
		
		//验证账号
		ZhengheDoctor doctor = zhengheDoctorService.findDoctorByPhone(phone);
		if(doctor==null){
			return buildFailedResultInfo(ZhengheConstance.ISEXISITMOBILE);
		}else{
			if(!doctor.getPassword().equals(password.toLowerCase())){
				return buildFailedResultInfo(ZhengheConstance.ISPASSWORDFAIL);
			}
		}
		
		//登录成功，更新数据库中的token记录
		ZhengheToken token = zhengheTokenService.findTokenByDoctorId(doctor.getId());
		if(token==null){
			token = new ZhengheToken();
		}else{
			if(!token.getTokenName().equals(tokenName)){
				//新机器登录，推送下线通知
				try {
					MiPushUtil.sendGetOutMessage(token.getTokenName());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		token.setCreateDate(new Date());
		token.setTokenName(tokenName);
		token.setType("2");
		token.setDoctorId(doctor.getId());
		zhengheTokenService.save(token);
		JSONObject jsonObject = doctorToJson(doctor);
		jsonObject.put("phone", doctor.getPhone());
		//返回医生信息给前端
		return buildSuccessResultInfo(jsonObject);
	}
	
	/*
	 * 第三方登录 
	 */
	private ResponseLogin patientToResponse(ZhenghePatient patient){
		ResponseLogin rl = new ResponseLogin();
		rl.setId(patient.getId()==null?"":patient.getId());
		rl.setAccountNumber(patient.getAccountNumber()==null?"":patient.getAccountNumber());
		rl.setAge(patient.getAge()==null?"":patient.getAge());
		String avatar = "";
		if(StringUtils.hasText(patient.getAvatar())){
			if(patient.getAvatar().contains("http")){
				avatar = patient.getAvatar();
			}else{
				avatar = getBaseContextPath()+patient.getAvatar();
			}
		}
		rl.setAvatar(avatar);
		Date date = patient.getBirthday();
		rl.setBirthday(date==null?"":DateUtils.formatDate(date,"yyyy-MM-dd"));
		rl.setChannel(patient.getChannel()==null?"":patient.getChannel());
		rl.setCityId(patient.getCityId()==null?"":patient.getCityId());
		rl.setCityName(patient.getCityId() == null ? "" : zhengheCityService.get(patient.getCityId()).getCityName());
		rl.setDistrictId(patient.getDistrictId()==null?"":patient.getDistrictId());
		rl.setDistrictName(patient.getDistrictId() == null ? "" : districtService.get(patient.getDistrictId()).getDistrictName());
		rl.setGender(patient.getGender()==null?"":patient.getGender());
		rl.setPatientName(patient.getPatientName()==null?"":patient.getPatientName());
		rl.setPhone(patient.getPhone()==null?"":patient.getPhone());
		rl.setProvinceName(patient.getProvincialId() == null ? "" : provinceService.get(patient.getProvincialId()).getName());
		rl.setProvincialId(patient.getProvincialId()==null?"":patient.getProvincialId());
		rl.setRemark(patient.getRemark());
		rl.setStatus(patient.getStatus()==null?"":patient.getStatus());
		return rl;
	}
	
	
	/*
	 * 根据键获取对应的值
	 */
	private String getValue(String key){
		Properties prop = new Properties();
		InputStream ins = this.getClass().getResourceAsStream("/fullteem.properties");
		String value=null;
		try {
			prop.load(ins);
			value = prop.getProperty(key);
			ins.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}


	/*
	 * 把患者集合封装成json数组
	 */
	private JSONArray patientListToJson(List<ZhenghePatient> patientList){
		if(patientList!=null){
			JSONArray jsonArray = new JSONArray();
			for(ZhenghePatient patient:patientList){
				JSONObject jsonObject = patientToJson(patient);
				jsonArray.add(jsonObject);
			}
			if(jsonArray.size()>0){
				return jsonArray;
			}
			return null;
		}
		return null;
	}
	
	
	
	/*
	 * 把患者对象封装成json对象
	 */
	private JSONObject patientToJson(ZhenghePatient patient){
		if(patient!=null){
			JSONObject jsonObject = new JSONObject();
			
			jsonObject.put("id", patient.getId());
			if(patient.getAvatar()==null){
				jsonObject.put("avatar","");
			}else{
				if(!patient.getAvatar().startsWith("http")){
					jsonObject.put("avatar",getBaseContextPath()+patient.getAvatar());
				}else{
					jsonObject.put("avatar",patient.getAvatar());
				}
			}

			jsonObject.put("patient",patient.getPatientName()==null?"":patient.getPatientName());
			jsonObject.put("gender",patient.getGender()==null?"":patient.getGender().equals("1")?"男":"女");
			jsonObject.put("age",patient.getAge()==null?"":patient.getAge());
			
			String address = "";
			if(patient.getProvincialId()!=null){
				address+=zhengheProvincialService.get(patient.getProvincialId()).getName();
			}
			if(patient.getCityId()!=null){
				address+=zhengheCityService.get(patient.getCityId()).getCityName();
			}
			if(patient.getDistrictId()!=null){
				address+=districtService.get(patient.getDistrictId()).getDistrictName();
			}
			jsonObject.put("address", address);
			
			return jsonObject;
		}
		return null;
	}
	
	
	/*
	 * 根据tokenName获取医生id
	 */
	private String getDoctorIdByTokenName(String tokenName){
		ZhengheToken token = zhengheTokenService.findTokenByTokenName(tokenName);
		return token.getDoctorId();
	}
	
	
	/*
	 * 根据tokenName验证用户是否已登录
	 */
	private boolean isLogin(String tokenName){
		ZhengheToken token = zhengheTokenService.findTokenByTokenName(tokenName);
		if(token==null){
			return false;
		}
		return true;
	}


	/*
	 * 把医生集合封装成json数组
	 */
	private JSONArray doctorListToJson(List<ZhengheDoctor> doctorList){
		if(doctorList!=null){
			JSONArray jsonArray = new JSONArray();
			for(ZhengheDoctor doctor:doctorList){
				JSONObject jsonObject = doctorToJson(doctor);
				jsonArray.add(jsonObject);
			}
			if(jsonArray.size()>0){
				return jsonArray;
			}
			return null;
		}
		return null;
	}
	
	
	/*
	 * 把医生对象封装成json对象
	 */
	private JSONObject doctorToJson(ZhengheDoctor doctor){
		if(doctor!=null){
			JSONObject jsonObject = new JSONObject();
			
			jsonObject.put("id", doctor.getId());
			if(doctor.getAvatar()==null){
				jsonObject.put("avatar","");
			}else{
				if(!doctor.getAvatar().startsWith("http")){
					jsonObject.put("avatar",getBaseContextPath()+doctor.getAvatar());
				}else{
					jsonObject.put("avatar",doctor.getAvatar());
				}
			}
			jsonObject.put("doctor", doctor.getDoctorName()==null?"":doctor.getDoctorName());
			String departmentsId = doctor.getDepartmentsId();
			String departmentName = "";
			if(departmentsId!=null){
				String sDepartmentsId = zhengheDepartmentsService.get(departmentsId).getSDepartmentsId();
				departmentName = zhengheDepartmentsService.get(sDepartmentsId).getDepartmentsName();
			}
			jsonObject.put("department",departmentName);
			String jobTitle = doctor.getJobTitle();
			String professional = "";
			if(jobTitle!=null){
				professional = zhengheProfessionService.get(jobTitle).getProfession();
			}
			jsonObject.put("professional", professional);
			String hospitalId = doctor.getHospitalId();
			String hospitalName = "";
			if(hospitalId!=null){
				hospitalName = zhengheHospitalService.get(hospitalId).getHospitalName();
			}
			jsonObject.put("hospital", hospitalName);
			jsonObject.put("professionalField", doctor.getProfessionalField()==null?"":doctor.getProfessionalField());
			jsonObject.put("intro", doctor.getIntro()==null?"":doctor.getIntro());
			//jsonObject.put("phone", doctor.getPhone()==null?"":doctor.getPhone());
			return jsonObject;
		}
		return null;
	}

}