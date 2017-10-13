package com.fullteem.modules.zhenghe.api.web;


import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.impl.cookie.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.fullteem.modules.zhenghe.api.entity.request.RequestAddPhoto;
import com.fullteem.modules.zhenghe.api.entity.request.RequestBloodPressure;
import com.fullteem.modules.zhenghe.api.entity.request.RequestDisease;
import com.fullteem.modules.zhenghe.api.entity.request.RequestGetCollectList;
import com.fullteem.modules.zhenghe.api.entity.request.RequestHeartRate;
import com.fullteem.modules.zhenghe.api.entity.request.RequestId;
import com.fullteem.modules.zhenghe.api.entity.request.RequestIdea;
import com.fullteem.modules.zhenghe.api.entity.request.RequestLifeLog;
import com.fullteem.modules.zhenghe.api.entity.request.RequestMedicalHistory;
import com.fullteem.modules.zhenghe.api.entity.request.RequestNumber;
import com.fullteem.modules.zhenghe.api.entity.request.RequestWeight;
import com.fullteem.modules.zhenghe.api.utils.CosmeticUpload;
import com.fullteem.modules.zhenghe.api.utils.Tool;
import com.fullteem.modules.zhenghe.api.utils.ZhengheConstance;
import com.fullteem.modules.zhenghe.api.utils.ZhengheUpload;
import com.fullteem.modules.zhenghe.entity.ZhengheArticle;
import com.fullteem.modules.zhenghe.entity.ZhengheBloodPressure;
import com.fullteem.modules.zhenghe.entity.ZhengheCarousel;
import com.fullteem.modules.zhenghe.entity.ZhengheCollect;
import com.fullteem.modules.zhenghe.entity.ZhengheCommonImage;
import com.fullteem.modules.zhenghe.entity.ZhengheCourseDisease;
import com.fullteem.modules.zhenghe.entity.ZhengheHeartRate;
import com.fullteem.modules.zhenghe.entity.ZhengheIdea;
import com.fullteem.modules.zhenghe.entity.ZhengheLifeLog;
import com.fullteem.modules.zhenghe.entity.ZhengheMedicalHistory;
import com.fullteem.modules.zhenghe.entity.ZhenghePatient;
import com.fullteem.modules.zhenghe.entity.ZhengheProduct;
import com.fullteem.modules.zhenghe.entity.ZhengheWeight;
import com.fullteem.modules.zhenghe.service.ZhengheArticleService;
import com.fullteem.modules.zhenghe.service.ZhengheBloodPressureService;
import com.fullteem.modules.zhenghe.service.ZhengheCarouselService;
import com.fullteem.modules.zhenghe.service.ZhengheCollectService;
import com.fullteem.modules.zhenghe.service.ZhengheCommonImageService;
import com.fullteem.modules.zhenghe.service.ZhengheCourseDiseaseService;
import com.fullteem.modules.zhenghe.service.ZhengheDepartmentsService;
import com.fullteem.modules.zhenghe.service.ZhengheDoctorService;
import com.fullteem.modules.zhenghe.service.ZhengheHeartRateService;
import com.fullteem.modules.zhenghe.service.ZhengheIdeaService;
import com.fullteem.modules.zhenghe.service.ZhengheLifeLogService;
import com.fullteem.modules.zhenghe.service.ZhengheMedicalHistoryService;
import com.fullteem.modules.zhenghe.service.ZhenghePatientService;
import com.fullteem.modules.zhenghe.service.ZhengheProductService;
import com.fullteem.modules.zhenghe.service.ZhengheWeightService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

/**
 * 
 * 类名: ZhengheMedicalApiController</br> 
 * 包名：com.fullteem.modules.zhenghe.api.web </br> 
 * 描述: 病历相关</br>
 * 发布版本号：</br>
 * 开发人员： 李启华</br>
 * 创建时间： 2015年11月18日
 */
@Api(value="ZhengheMedical",description = "病历相关")
@Controller
@RequestMapping(value="/api/medicalApiController")
public class ZhengheMedicalApiController extends BaseController{
	@Resource
	ZhengheMedicalHistoryService medicalHistoryService;
	@Resource
	ZhenghePatientService patientService;
	@Resource
	ZhengheDepartmentsService departmentsService;
	@Resource
	ZhengheCourseDiseaseService diseaseService;
	@Autowired
	ZhengheMedicalHistoryService historyService;
	@Resource
	ZhengheBloodPressureService bloodPressureService;
	@Resource
	ZhengheHeartRateService heartRateService;
	@Resource
	ZhengheWeightService weightService;
	@Resource
	ZhengheLifeLogService lifeLogService;
	@Resource
	ZhengheIdeaService ideaService;
	@Autowired
	ZhengheArticleService articleService;
	@Resource
	ZhengheProductService productService;
	@Resource
	ZhengheCollectService collectService;
	@Resource
	ZhengheDoctorService doctorService;
	@Resource
	ZhengheCarouselService carouselService;
	@Resource 
	ZhengheCommonImageService imageService;
	@Autowired
	private HttpServletRequest request;

	/**
	 * 
	 * 方法名: </br>
	 * 详述: </br>
	 * 开发人员：chenx</br>
	 * 创建时间：2015年12月14日</br>
	 * @param requestId
	 * @return
	 */
	@ApiOperation(value = "删除血压记录", notes = "血压记录id必传")
	@RequestMapping(value = "/delBloodPressureRecord", method = RequestMethod.POST)
	@ApiResponses({
		@ApiResponse(code = ZhengheConstance.param_fault, message = "参数格式不正确", response = String.class),
	})
	public ResponseEntity<BaseResult> delBloodPressureRecord(@ApiParam(required = true, value = "血压记录id必传")
		@RequestBody RequestId requestId) {
		
		if(StringUtils.isBlank(requestId.getId())){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		
		ZhengheBloodPressure bloodPressure = bloodPressureService.get(requestId.getId());
		
		if(bloodPressure==null){
			return buildFailedResultInfo(ZhengheConstance.BASE_FAIL_CODE);
		}
		
		bloodPressureService.delete(bloodPressure);
		
		return buildSuccessResultInfo(ZhengheConstance.BASE_SUCCESS_CODE);
	}
	
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述: </br>
	 * 开发人员：chenx</br>
	 * 创建时间：2015年12月14日</br>
	 * @param requestId
	 * @return
	 */
	@ApiOperation(value = "删除心率记录", notes = "心率记录id必传")
	@RequestMapping(value = "/delHeartRateRecord", method = RequestMethod.POST)
	@ApiResponses({
		@ApiResponse(code = ZhengheConstance.param_fault, message = "参数格式不正确", response = String.class),
	})
	public ResponseEntity<BaseResult> delHeartRateRecord(@ApiParam(required = true, value = "心率记录id必传")
		@RequestBody RequestId requestId) {
		
		if(StringUtils.isBlank(requestId.getId())){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		
		ZhengheHeartRate heartRate = heartRateService.get(requestId.getId());
		
		if(heartRate==null){
			return buildFailedResultInfo(ZhengheConstance.BASE_FAIL_CODE);
		}
		
		heartRateService.delete(heartRate);
		
		return buildSuccessResultInfo(ZhengheConstance.BASE_SUCCESS_CODE);
	}
	
	
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述: </br>
	 * 开发人员：chenx</br>
	 * 创建时间：2015年12月14日</br>
	 * @param requestId
	 * @return
	 */
	@ApiOperation(value = "删除体重记录", notes = "体重记录id必传")
	@RequestMapping(value = "/delWeightRecord", method = RequestMethod.POST)
	@ApiResponses({
		@ApiResponse(code = ZhengheConstance.param_fault, message = "参数格式不正确", response = String.class),
	})
	public ResponseEntity<BaseResult> delWeightRecord(@ApiParam(required = true, value = "体重记录id必传")
		@RequestBody RequestId requestId) {
		
		if(StringUtils.isBlank(requestId.getId())){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		
		ZhengheWeight weight = weightService.get(requestId.getId());
		
		if(weight==null){
			return buildFailedResultInfo(ZhengheConstance.BASE_FAIL_CODE);
		}
		
		weightService.delete(weight);
		
		return buildSuccessResultInfo(ZhengheConstance.BASE_SUCCESS_CODE);
	}
	
	
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述:获取收藏列表</br>
	 * 开发人员：李启华</br>
	 * 创建时间：2015年11月19日</br>
	 * @param jsonValue
	 * @return
	 */
	@ApiOperation(value = "获取收藏列表", notes = "id->用户id，type->1文章2商品，user->1患者2医生，全部必选")
	@RequestMapping(value = "/getPatientCollectList", method = RequestMethod.POST)
	@ApiResponses({
		@ApiResponse(code = ZhengheConstance.param_fault, message = "参数格式不正确", response = String.class),
		@ApiResponse(code = ZhengheConstance.userId_is_null, message = "用户id不存在", response = String.class),
	})
	public ResponseEntity<BaseResult> getPatientCollectList(@ApiParam(required = true, value = "type->1文章2商品，全部必选")
		@RequestBody RequestGetCollectList param) {
		String id = param.getId();
		String type = param.getType();
		String user = param.getUser();
		if(Tool.strBlank(id,type)){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		if(user.equals("1") && patientService.get(id) == null){
			return buildFailedResultInfo(ZhengheConstance.userId_is_null);
		}
		if(user.equals("2") && doctorService.get(id) == null){
			return buildFailedResultInfo(ZhengheConstance.userId_is_null);
		}
		ZhengheCollect c = new ZhengheCollect();
		
		if(user.equals("1")){
			c.setPatientId(id);
			List<ZhengheCollect> cList = collectService.findList(c);
			if(cList != null && cList.size()>0){
				if(type.equals("1")){
					List<ZhengheArticle> aList = new ArrayList<ZhengheArticle>();
						for(ZhengheCollect collect : cList){
							if(collect.getArticleId() != null){
								ZhengheArticle a = articleService.get(collect.getArticleId());
								if(a != null){
									a.setAvatar(getBasePath()+a.getAvatar());
									aList.add(a);
								}
							}
						}
					return buildSuccessResultInfo(aList);
				}else{
					List<ZhengheProduct> pList = new ArrayList<ZhengheProduct>();
					for(ZhengheCollect collect : cList){
						if(collect.getProductId() != null){
							ZhengheProduct p = productService.get(collect.getProductId());
							if(p != null){
								pList.add(p);
							}
						}
					}
					if(pList.size() > 0){
						for(ZhengheProduct product : pList){
							List<ZhengheCarousel> caList = carouselService.findListByProductId(product.getId());
							product.setProductPic((caList == null || caList.size()<1) ? "" : caList.get(0).getAvatar());
						}
					}
				return buildSuccessResultInfo(pList);
				}
			}
		}else{
			c.setDoctorId(id);
			List<ZhengheCollect> cList = collectService.findList(c);
			if(cList != null && cList.size()>0){
				List<ZhengheArticle> aList = new ArrayList<ZhengheArticle>();
				for(ZhengheCollect collect : cList){
					if(collect.getArticleId() != null){
						ZhengheArticle a = articleService.get(collect.getArticleId());
						if(a != null){
							aList.add(a);
						}
					}
				}
			return buildSuccessResultInfo(aList);
			}
		}
		return buildSuccessResultInfo(ZhengheConstance.BASE_FAIL_CODE);
	}
	
	
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述:添加反馈意见 </br>
	 * 开发人员：李启华</br>
	 * 创建时间：2015年11月18日</br>
	 * @param jsonValue
	 * @return
	 */
	@ApiOperation(value = "添加反馈意见", notes = "用户id、用户类型、content反馈内容必传，phone手机号码选填")
	@RequestMapping(value = "/addIdea", method = RequestMethod.POST)
	@ApiResponses({
		@ApiResponse(code = ZhengheConstance.param_fault, message = "参数格式不正确", response = String.class),
		@ApiResponse(code = ZhengheConstance.userId_is_null, message = "用户id不存在", response = String.class),
	})
	public ResponseEntity<BaseResult> addIdea(@ApiParam(required = true, value = "userType用户类型:1->患者;2->医生")
		@RequestBody RequestIdea param) {
		String userId = param.getUserId();
		String content = param.getContent();
		String phone = param.getPhone();
		String userType = param.getUserType();
		if(Tool.strBlank(userId,content,userType)){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		
		if("1".equals(userType)){
			if(patientService.get(userId) == null){
				return buildFailedResultInfo(ZhengheConstance.userId_is_null);
			}
			
			ZhengheIdea i = new ZhengheIdea();
			i.setPatientId(userId);
			i.setContent(content);
			i.setType("1");
			if(StringUtils.isNotBlank(phone)){
				i.setPhone(phone);
			}
			ideaService.save(i);
			
		}else if("2".equals(userType)){
			if(doctorService.get(userId)==null){
				return buildFailedResultInfo(ZhengheConstance.userId_is_null);
			}
			
			ZhengheIdea i = new ZhengheIdea();
			i.setDoctorId(userId);
			i.setContent(content);
			i.setType("1");
			if(StringUtils.isNotBlank(phone)){
				i.setPhone(phone);
			}
			ideaService.save(i);
		}
		
		return buildSuccessResultInfo(ZhengheConstance.BASE_SUCCESS_CODE);
	}
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述:删除生活日志 </br>
	 * 开发人员：李启华</br>
	 * 创建时间：2015年11月18日</br>
	 * @param jsonValue
	 * @return
	 */
	@ApiOperation(value = "删除生活日志", notes = "生活日志id必填")
	@RequestMapping(value = "/deleteLifeLog", method = RequestMethod.POST)
	@ApiResponses({
		@ApiResponse(code=ZhengheConstance.BASE_SUCCESS_CODE,message="success",response=String.class),
		@ApiResponse(code = ZhengheConstance.param_fault, message = "参数格式不正确", response = String.class),
		@ApiResponse(code = ZhengheConstance.userId_is_null, message = "用户id不存在", response = String.class),
	})
	public ResponseEntity<BaseResult> deleteLifeLog(@ApiParam(required = true, value = "生活日志id必填")
		@RequestBody RequestId param) {
		String lifeLogId = param.getId();
		if(StringUtils.isBlank(lifeLogId)){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		
		ZhengheLifeLog lifeLog = new ZhengheLifeLog();
		lifeLog.setId(lifeLogId);
		
		lifeLogService.delete(lifeLog);
		
		return buildSuccessResultInfo(ZhengheConstance.BASE_SUCCESS_CODE);
		
	}
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述: </br>
	 * 开发人员：chenx</br>
	 * 创建时间：2015年12月18日</br>
	 * @param id
	 * @return
	 */
	private List<String> getImage(String id){
		
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
		
		ZhengheCommonImage image = imageService.findByRelevanceId(id);
		
		if(image==null)
			return null;
		
		List<String> images = new ArrayList<String>();
		Class<? extends ZhengheCommonImage> clazz = image.getClass();
		try {
			for(int i=0;i<9;i++){
				String str = "getImage"+(i+1);
				Method method = clazz.getDeclaredMethod(str);
				Object obj = method.invoke(image);
				if(obj!=null&&!StringUtils.isBlank(obj.toString())){
					String url = basePath+obj.toString();
					images.add(url);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return images;
	}
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述:获取病人生活日志 </br>
	 * 开发人员：李启华</br>
	 * 创建时间：2015年11月18日</br>
	 * @param jsonValue
	 * @return
	 */
	@ApiOperation(value = "获取病人生活日志", notes = "病人id必填")
	@RequestMapping(value = "/getLifeLog", method = RequestMethod.POST)
	@ApiResponses({
		@ApiResponse(code = ZhengheConstance.param_fault, message = "参数格式不正确", response = String.class),
		@ApiResponse(code = ZhengheConstance.userId_is_null, message = "用户id不存在", response = String.class),
	})
	public ResponseEntity<BaseResult> getLifeLog(@ApiParam(required = true, value = "病人id必填")
		@RequestBody RequestId param) {
		String patientId = param.getId();
		if(StringUtils.isBlank(patientId)){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		if(patientService.get(patientId) == null){
			return buildFailedResultInfo(ZhengheConstance.userId_is_null);
		}
		
		ZhengheLifeLog l = new ZhengheLifeLog();
		l.setPatientId(patientId);
		List<ZhengheLifeLog> lList = lifeLogService.findList(l);
		
		for(ZhengheLifeLog lifeLog:lList){
			lifeLog.setImage(getImage(lifeLog.getId()));
		}
		
		return buildSuccessResultInfo(lList);
	}
	
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述:添加/修改生活日志 </br>
	 * 开发人员：李启华</br>
	 * 创建时间：2015年11月18日</br>
	 * @param jsonValue
	 * @return
	 */
	@ApiOperation(value = "添加/修改生活日志", notes = "除生活日志id外其它参数全部必传:title标题，content内容(如果不传生活日志id为添加,否则为修改生活日志)")
	@RequestMapping(value = "/addLifeLog", method = RequestMethod.POST)
	@ApiResponses({
		@ApiResponse(code = ZhengheConstance.param_fault, message = "参数格式不正确", response = String.class),
		@ApiResponse(code = ZhengheConstance.userId_is_null, message = "用户id不存在", response = String.class),
		@ApiResponse(code = ZhengheConstance.file_mine_fault, message = "file类型错误", response = String.class),
		@ApiResponse(code = ZhengheConstance.upload_fault, message = "上传出错", response = String.class),
	})
	public ResponseEntity<BaseResult> addLifeLog(@ApiParam(required = true, value = "除生活日志id外其它参数全部必传:title标题，content内容")
		@ModelAttribute RequestLifeLog param,@RequestParam(required=false) MultipartFile [] file,HttpServletRequest request) {
		String patientId = param.getPatientId();
		String title = param.getTitle();
		String content = param.getContent();
		String logId = param.getLogId();
		if(Tool.strBlank(patientId,title,content)){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		if(patientService.get(patientId) == null){
			return buildFailedResultInfo(ZhengheConstance.userId_is_null);
		}
		ZhengheLifeLog l = new ZhengheLifeLog();
		l.setPatientId(patientId);
		l.setTitle(title);
		l.setContent(content);
		if(StringUtils.isBlank(logId)){
			lifeLogService.save(l);
			int result = saveImage(file,"3",l.getId(),request);
			if(result!=0){
				return buildFailedResultInfo(result);
			}
		}else{
			l.setIsNewRecord(false);
			l.setId(logId);
			lifeLogService.save(l);
			imageService.delByRelevanceId(logId);
			int result = saveImage(file,"3",l.getId(),request);
			if(result!=0){
				return buildFailedResultInfo(result);
			}
		}
		return buildSuccessResultInfo(ZhengheConstance.BASE_SUCCESS_CODE);
	}
	
	
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述:获取病人体重记录 </br>
	 * 开发人员：李启华</br>
	 * 创建时间：2015年11月18日</br>
	 * @param jsonValue
	 * @return
	 */
	@ApiOperation(value = "获取病人体重记录", notes = "病人id必填")
	@RequestMapping(value = "/getWeightList", method = RequestMethod.POST)
	@ApiResponses({
		@ApiResponse(code = ZhengheConstance.param_fault, message = "参数格式不正确", response = String.class),
		@ApiResponse(code = ZhengheConstance.userId_is_null, message = "用户id不存在", response = String.class),
	})
	public ResponseEntity<BaseResult> getWeightList(@ApiParam(required = true, value = "病人id必填")
		@RequestBody RequestId param) {
		String patientId = param.getId();
		if(StringUtils.isBlank(patientId)){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		if(patientService.get(patientId) == null){
			return buildFailedResultInfo(ZhengheConstance.userId_is_null);
		}
		ZhengheWeight w = new ZhengheWeight();
		w.setPatientId(patientId);
		List<ZhengheWeight> wList = weightService.findList(w);
		return buildSuccessResultInfo(wList);
	}
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述:添加/修改体重记录 </br>
	 * 开发人员：李启华</br>
	 * 创建时间：2015年11月18日</br>
	 * @param jsonValue
	 * @return
	 */
	@ApiOperation(value = "添加/修改体重记录", notes = "除体重记录id外其它参数全部必传:stature身高，weight体重(如果不传体重记录id为添加,否则为修改体重记录)")
	@RequestMapping(value = "/addWeight", method = RequestMethod.POST)
	@ApiResponses({
		@ApiResponse(code = ZhengheConstance.param_fault, message = "参数格式不正确", response = String.class),
		@ApiResponse(code = ZhengheConstance.userId_is_null, message = "用户id不存在", response = String.class),
	})
	public ResponseEntity<BaseResult> addWeight(@ApiParam(required = true, value = "参数全部必传:stature身高(单位cm)，weight体重(单位kg)")
		@RequestBody RequestWeight param) {
		String patientId = param.getPatientId();
		String stature = param.getStature();		//身高
		String weight = param.getWeight();			//体重
		String wId = param.getwId();
		if(Tool.strBlank(patientId,stature,weight)){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		if(patientService.get(patientId) == null){
			return buildFailedResultInfo(ZhengheConstance.userId_is_null);
		}
		Double exponent;		// 指数
		try{
			Double height = Double.parseDouble(stature)/100;		//身高(单位m)
			exponent = Double.parseDouble(weight)/(height*height);
			DecimalFormat df = new DecimalFormat(".##");
			exponent = Double.valueOf(df.format(exponent));
		}catch(NumberFormatException e){
			com.fullteem.common.utils.Log.println("---------------------------- stature : "+stature+" , weight :　"+weight);
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		ZhengheWeight w = new ZhengheWeight();
		w.setPatientId(patientId);
		w.setStature(stature);
		w.setWeight(weight);
		w.setExponent(exponent+"");
		if(StringUtils.isBlank(wId)){
			weightService.save(w);
		}else{
			w.setIsNewRecord(false);
			w.setId(wId);
			weightService.save(w);
		}
		return buildSuccessResultInfo(ZhengheConstance.BASE_SUCCESS_CODE);
	}
	
	
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述:获取病人心率记录 </br>
	 * 开发人员：李启华</br>
	 * 创建时间：2015年11月18日</br>
	 * @param jsonValue
	 * @return
	 */
	@ApiOperation(value = "获取病人心率记录", notes = "病人id必填")
	@RequestMapping(value = "/getHeartRateList", method = RequestMethod.POST)
	@ApiResponses({
		@ApiResponse(code = ZhengheConstance.param_fault, message = "参数格式不正确", response = String.class),
		@ApiResponse(code = ZhengheConstance.userId_is_null, message = "用户id不存在", response = String.class),
	})
	public ResponseEntity<BaseResult> getHeartRateList(@ApiParam(required = true, value = "病人id必填")
		@RequestBody RequestId param) {
		String patientId = param.getId();
		if(StringUtils.isBlank(patientId)){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		if(patientService.get(patientId) == null){
			return buildFailedResultInfo(ZhengheConstance.userId_is_null);
		}
		ZhengheHeartRate h = new ZhengheHeartRate();
		h.setPatientId(patientId);
		List<ZhengheHeartRate> hList = heartRateService.findList(h);
		return buildSuccessResultInfo(hList);
	}
	
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述:添加/修改心率记录 </br>
	 * 开发人员：李启华</br>
	 * 创建时间：2015年11月18日</br>
	 * @param jsonValue
	 * @return
	 */
	@ApiOperation(value = "添加/修改心率记录", notes = "除心率记录id外其它参数全部必传:heartRate心率，scene场景(如果不传心率记录id为添加,否则为修改心率记录)")
	@RequestMapping(value = "/addHeartRate", method = RequestMethod.POST)
	@ApiResponses({
		@ApiResponse(code = ZhengheConstance.param_fault, message = "参数格式不正确", response = String.class),
		@ApiResponse(code = ZhengheConstance.userId_is_null, message = "用户id不存在", response = String.class),
	})
	public ResponseEntity<BaseResult> addHeartRate(@ApiParam(required = true, value = "参数全部必传:heartRate心率，scene场景")
		@RequestBody RequestHeartRate param) {
		String patientId = param.getPatientId();
		String heartRate = param.getHeartRate();
		String scene = param.getScene();
		String hrId = param.getHrId();
		if(Tool.strBlank(patientId,heartRate,scene)){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		if(patientService.get(patientId) == null){
			return buildFailedResultInfo(ZhengheConstance.userId_is_null);
		}
		ZhengheHeartRate h = new ZhengheHeartRate();
		h.setPatientId(patientId);
		h.setHeartRate(heartRate);
		h.setScene(scene);
		if(StringUtils.isBlank(hrId)){
			heartRateService.save(h);
		}else{
			h.setIsNewRecord(false);
			h.setId(hrId);
			heartRateService.save(h);
		}
		return buildSuccessResultInfo(ZhengheConstance.BASE_SUCCESS_CODE);
	}
	
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述:获取病人血压记录 </br>
	 * 开发人员：李启华</br>
	 * 创建时间：2015年11月18日</br>
	 * @param jsonValue
	 * @return
	 */
	@ApiOperation(value = "获取病人血压记录", notes = "参数为病人id，必传")
	@RequestMapping(value = "/getBloodPressureList", method = RequestMethod.POST)
	@ApiResponses({
		@ApiResponse(code = ZhengheConstance.param_fault, message = "参数格式不正确", response = String.class),
		@ApiResponse(code = ZhengheConstance.userId_is_null, message = "用户id不存在", response = String.class),
	})
	public ResponseEntity<BaseResult> getBloodPressureList(@ApiParam(required = true, value = "参数为病人id，必传")
		@RequestBody RequestId param) {
		String patientId = param.getId();
		if(StringUtils.isBlank(patientId)){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		if(patientService.get(patientId) == null){
			return buildFailedResultInfo(ZhengheConstance.userId_is_null);
		}
		ZhengheBloodPressure b = new ZhengheBloodPressure();
		b.setPatientId(patientId);
		List<ZhengheBloodPressure> bList = bloodPressureService.findList(b);
		return buildSuccessResultInfo(bList);
	}
	
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述:添加/修改血压记录 </br>
	 * 开发人员：李启华</br>
	 * 创建时间：2015年11月18日</br>
	 * @param jsonValue
	 * @return
	 */
	@ApiOperation(value = "添加/修改血压记录", notes = "除血压记录id外其它参数全部必传:sbp收缩压，dbp舒张压(如果不传血压记录id为添加,否则为修改血压记录)")
	@RequestMapping(value = "/addBloodPressure", method = RequestMethod.POST)
	@ApiResponses({
		@ApiResponse(code = ZhengheConstance.param_fault, message = "参数格式不正确", response = String.class),
		@ApiResponse(code = ZhengheConstance.userId_is_null, message = "用户id不存在", response = String.class),
	})
	public ResponseEntity<BaseResult> addBloodPressure(@ApiParam(required = true, value = "参数全部必传:sbp收缩压，dbp舒张压")
		@RequestBody RequestBloodPressure param) {
		String patientId = param.getPatientId();
		String sbp = param.getSbp();
		String dbp = param.getDbp();
		String bpId = param.getBpId();
		if(Tool.strBlank(patientId,sbp,dbp)){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		if(patientService.get(patientId) == null){
			return buildFailedResultInfo(ZhengheConstance.userId_is_null);
		}
		ZhengheBloodPressure b = new ZhengheBloodPressure();
		b.setPatientId(patientId);
		b.setSbp(sbp);
		b.setDbp(dbp);
		if(StringUtils.isBlank(bpId)){
			bloodPressureService.save(b);
		}else{
			b.setIsNewRecord(false);
			b.setId(bpId);
			bloodPressureService.save(b);
		}
		return buildSuccessResultInfo(ZhengheConstance.BASE_SUCCESS_CODE);
	}
	
	
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述:获取病历列表 </br>
	 * 开发人员：李启华</br>
	 * 创建时间：2015年11月18日</br>
	 * @param jsonValue
	 * @return
	 */
	@ApiOperation(value = "获取病历列表", notes = "参数为患者id，必传")
	@RequestMapping(value = "/getHistoryList", method = RequestMethod.POST)
	@ApiResponses({
		@ApiResponse(code = ZhengheConstance.param_fault, message = "参数格式不正确", response = String.class),
		@ApiResponse(code = ZhengheConstance.gender_param_fault, message = "性别参数格式错误，参数必须为'男'或'女'", response = String.class),
		@ApiResponse(code = ZhengheConstance.date_param_fault, message = "日期字符参数格式错误", response = String.class),
		@ApiResponse(code = ZhengheConstance.userId_is_null, message = "用户id不存在", response = String.class),
		@ApiResponse(code = ZhengheConstance.departmentId_not_exist, message = "科室id不存在", response = String.class),
		@ApiResponse(code = ZhengheConstance.mhId_not_exist, message = "病历id不存在", response = String.class),
	})
	public ResponseEntity<BaseResult> getHistoryList(@ApiParam(required = true, value = "参数为患者id，必传")
		@RequestBody RequestId requestId) {
		String patientId = requestId.getId();				// 病人id
		/*
		 * 非空校验
		 */
		if(StringUtils.isBlank(patientId)){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		ZhenghePatient patient = patientService.get(patientId);
		if(patient == null){
			return buildFailedResultInfo(ZhengheConstance.userId_is_null);
		}
		ZhengheMedicalHistory mh = new ZhengheMedicalHistory();
		mh.setPatientId(patientId);
		List<ZhengheMedicalHistory> mhList = medicalHistoryService.findList(mh);
		
		for(ZhengheMedicalHistory m : mhList){
			m.setBirthday2(m.getBirthday()==null?"":DateUtils.formatDate(m.getBirthday(),"yyyy-MM-dd"));
			m.setSeeadoctorDate2(m.getSeeadoctorDate()==null?"":DateUtils.formatDate(m.getSeeadoctorDate(),"yyyy-MM-dd"));
			m.setStandby1(patient.getAge());
			m.setImage(getImage(m.getId()));
			m.setUpdateDate2(m.getUpdateDate()==null?"":DateUtils.formatDate(m.getUpdateDate(),"yyyy-MM-dd"));
			ZhengheCourseDisease d = new ZhengheCourseDisease();
			d.setMhId(m.getId());
			List<ZhengheCourseDisease> cdList = diseaseService.findList(d);
			for(ZhengheCourseDisease cd:cdList){
				cd.setImage(getImage(cd.getId()));
				cd.setCdDate2(cd.getCdDate()==null?"":DateUtils.formatDate(cd.getCdDate(),"yyyy-MM-dd"));
			}
			m.setCdList(cdList);
		}
		return buildSuccessResultInfo(mhList);
	}
	
	
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述:添加/修改病程 </br>
	 * 开发人员：李启华</br>
	 * 创建时间：2015年11月18日</br>
	 * @param jsonValue
	 * @return
	 */
	@ApiOperation(value = "添加/修改病程", notes = "除病程id外,其他参数全部必填(如果不传病程id为添加,否则为修改病程)"
			+ "日期cdDate必须为'yyyy-MM-dd'格式字符串")
	@RequestMapping(value = "/addDisease", method = RequestMethod.POST)
	@ApiResponses({
		@ApiResponse(code = ZhengheConstance.param_fault, message = "参数格式不正确", response = String.class),
		@ApiResponse(code = ZhengheConstance.gender_param_fault, message = "性别参数格式错误，参数必须为'男'或'女'", response = String.class),
		@ApiResponse(code = ZhengheConstance.date_param_fault, message = "日期字符参数格式错误", response = String.class),
		@ApiResponse(code = ZhengheConstance.userId_is_null, message = "用户id不存在", response = String.class),
		@ApiResponse(code = ZhengheConstance.departmentId_not_exist, message = "科室id不存在", response = String.class),
		@ApiResponse(code = ZhengheConstance.mhId_not_exist, message = "病历id不存在", response = String.class),
		@ApiResponse(code = ZhengheConstance.file_mine_fault, message = "file类型错误", response = String.class),
		@ApiResponse(code = ZhengheConstance.upload_fault, message = "上传出错", response = String.class),
	})
	public ResponseEntity<BaseResult> addDisease(@ApiParam(required = true, value = "除病程id外,其他参数全部必填，日期cdDate必须为'yyyy-MM-dd'格式字符串")
		@ModelAttribute RequestDisease param,@RequestParam(required=false) MultipartFile [] file,HttpServletRequest request) {
		String mhId = param.getMhId();				// 病历id
		String cdDate = param.getCdDate();			// 日期
		String incident = param.getIncident();		// 事件
		String diseaseId = param.getDiseaseId();	// 病程id
		String remark = param.getRemark();			// 备注
		/*
		 * 非空校验
		 */
		if(Tool.strBlank(mhId,cdDate,incident,remark)){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		/*
		 * 日期字符参数校验、转化
		 */
		SimpleDateFormat df =   new SimpleDateFormat("yyyy-MM-dd");	//出生日期格式
		Date _cdDate;
		try {
			_cdDate = df.parse(cdDate);
		} catch (ParseException e) {return buildFailedResultInfo(ZhengheConstance.date_param_fault);}
		
		/*
		 * 病历id非空校验
		 */
		if(medicalHistoryService.get(mhId) == null){
			return buildFailedResultInfo(ZhengheConstance.mhId_not_exist);
		}
		ZhengheCourseDisease cd = new ZhengheCourseDisease();
		cd.setMhId(mhId);
		cd.setCdDate(_cdDate);
		cd.setIncident(incident);
		cd.setRemark(remark);
		if(StringUtils.isBlank(diseaseId)){
			diseaseService.save(cd);
			int result = saveImage(file,"2",cd.getId(),request);
			if(result!=0){
				return buildFailedResultInfo(result);
			}
		}else{
			cd.setIsNewRecord(false);
			cd.setId(diseaseId);
			diseaseService.save(cd);
			imageService.delByRelevanceId(diseaseId);
			int result = saveImage(file,"2",cd.getId(),request);
			if(result!=0){
				return buildFailedResultInfo(result);
			}
		}
		
		cd.setImage(getImage(cd.getId()));
		
		cd.setCdDate2(df.format(cd.getCdDate()));
		
		return buildSuccessResultInfo(cd);
	}
	
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述:添加/修改病历 </br>
	 * 开发人员：李启华</br>
	 * 创建时间：2015年11月18日</br>
	 * @param jsonValue
	 * @return
	 */
	@ApiOperation(value = "添加/修改病历", notes = "除病历id和图片外,其他参数全部必填(如果不传病历id为添加,否则为修改病历)"
			+ "出生日期->birthday 必须为'yyyy-MM-dd'格式字符串，就诊时间->seeadoctorDate 必须为'yyyy-MM-dd'格式字符串")
	@RequestMapping(value = "/addMedicalHistory", method = RequestMethod.POST)
	@ApiResponses({
		@ApiResponse(code = ZhengheConstance.param_fault, message = "参数格式不正确", response = String.class),
		@ApiResponse(code = ZhengheConstance.gender_param_fault, message = "性别参数格式错误，参数必须为'男'或'女'", response = String.class),
		@ApiResponse(code = ZhengheConstance.date_param_fault, message = "日期字符参数格式错误", response = String.class),
		@ApiResponse(code = ZhengheConstance.userId_is_null, message = "用户id不存在", response = String.class),
		@ApiResponse(code = ZhengheConstance.file_mine_fault, message = "file类型错误", response = String.class),
		@ApiResponse(code = ZhengheConstance.upload_fault, message = "上传出错", response = String.class),
	})
	public ResponseEntity<BaseResult> addMedicalHistory(@ApiParam(required = true, value = "除病历id外,其他参数全部必填")
		@ModelAttribute RequestMedicalHistory param,@RequestParam(required=false) MultipartFile [] file,HttpServletRequest request) {
		String patientId = param.getPatientId();			//病人id
		String mhTitle = param.getMhTitle();				//病历标题
		String mhName = param.getMhName();					//患者姓名
		String gender = param.getGender();					//性别
		String birthday = param.getBirthday();				//出生日期
		String departmentName = param.getDepartmentName();	//科室名
		String seeadoctorDate = param.getSeeadoctorDate();	//就医时间
		String diagnose = param.getDiagnose();				//医生诊断
		String description = param.getDescription();		//基本病情
		String mhId = param.getMedicalId();					//病历id
		
		com.fullteem.common.utils.Log.println("\n\n\n\n////////////////////////////////////");
		com.fullteem.common.utils.Log.println("patientId="+patientId+"\nmhTitle="+mhTitle+"\nmhName="+mhName+"\ngender="+gender+"\nbirthday="+birthday+"\ndepartmentName="+departmentName
				+"\nseeadoctorDate="+seeadoctorDate+"\ndiagnose="+diagnose+"\ndescription="+description+"\nmhId="+mhId+"\nfileSize="+file.length);
		com.fullteem.common.utils.Log.println("////////////////////////////////////\n\n\n\n");
		
		/*
		 * 非空校验
		 */
		if(Tool.strBlank(patientId,mhTitle,mhName,gender,birthday,departmentName,seeadoctorDate,diagnose,description)){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		/*
		 * 性别参数校验
		 */
		if("男".equals(gender)||"女".equals(gender)){}else return buildFailedResultInfo(ZhengheConstance.gender_param_fault);
		/*
		 * 日期字符参数校验、转化
		 */
		SimpleDateFormat birthdf =   new SimpleDateFormat("yyyy-MM-dd");	//出生日期格式
		SimpleDateFormat seedocdf =   new SimpleDateFormat("yyyy-MM-dd");	//就医时间格式
		Date _birthday;
		Date _seeadoctorDate;
		try {
			_birthday = birthdf.parse(birthday);
			_seeadoctorDate = seedocdf.parse(seeadoctorDate);
		} catch (ParseException e) {return buildFailedResultInfo(ZhengheConstance.date_param_fault);}
		/*
		 * 用户id非空校验
		 */
		if(patientService.get(patientId) == null){
			return buildFailedResultInfo(ZhengheConstance.userId_is_null);
		}
		
		
		ZhengheMedicalHistory md = null;
		if(StringUtils.isBlank(mhId)){
			md = new ZhengheMedicalHistory();
		}else{
			md = medicalHistoryService.get(mhId);
		}
		md.setPatientId(patientId);
		md.setMhTitle(mhTitle);
		md.setMhName(mhName);
		md.setGender(gender);
		md.setBirthday(_birthday);
		md.setDepartmentsName(departmentName);
		md.setSeeadoctorDate(_seeadoctorDate);
		md.setDiagnose(diagnose);
		md.setDescription(description);
		
		if(StringUtils.isBlank(mhId)){
			medicalHistoryService.save(md);
			int result = saveImage(file,"1",md.getId(),request);
			if(result!=0){
				return buildFailedResultInfo(result);
			}
		}else{
			md.setIsNewRecord(false);
			md.setId(mhId);
			medicalHistoryService.save(md);
			imageService.delByRelevanceId(mhId);
			int result = saveImage(file,"1",md.getId(),request);
			if(result!=0){
				return buildFailedResultInfo(result);
			}
		}
		
		md.setBirthday2(birthday);
		md.setSeeadoctorDate2(seeadoctorDate);
		md.setImage(getImage(md.getId()));
		
		return buildSuccessResultInfo(md);
	}
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述: </br>
	 * 开发人员：chenx</br>
	 * 创建时间：2015年12月15日</br>
	 * @param file 要保存的文件
	 * @param type 类型:1->患者病历;2->患者病程;3->生活日志;
	 * @param id   关联id
	 * @param request
	 * @return
	 */
	private int saveImage(MultipartFile [] file,String type,String id,HttpServletRequest request){
		
		List<MultipartFile> fileList = new ArrayList<MultipartFile>();
		
		for(int i=0;i<file.length;i++){
			if(i==9)break;
			fileList.add(file[i]);
		}

		ZhengheUpload zhengheUpload = new ZhengheUpload();
		
		zhengheUpload.choosePath(Integer.valueOf(type));
	
		String result = zhengheUpload.uploadFile(request,fileList);
		if(result.equals("1")){
			//return ZhengheConstance.empty_file;
		}
		if(result.equals("2")){
			return ZhengheConstance.file_mine_fault;
		}
		if(result.equals("3")){
			return ZhengheConstance.upload_fault;
		}

		ZhengheCommonImage image = new ZhengheCommonImage();
		image.setRelevanceId(id);
		image.setType(type);
		
		String [] paths = result.split("\\|");
		
		Class<? extends ZhengheCommonImage> clazz = image.getClass();
		try {
			for(int i=0;i<paths.length;i++){
				String str = "setImage"+(i+1);
				Method method = clazz.getDeclaredMethod(str, String.class);
				method.invoke(image, paths[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		imageService.save(image);
		
		return 0;
	}
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述:上传 病历/病程 图片 </br>
	 * 开发人员：李启华</br>
	 * 创建时间：2015年11月20日</br>
	 * @param jsonValue
	 * @return
	 */
	/*@ApiOperation(value = "上传 病历/病程 图片", notes = "图片最多上传9张,多于9张时只取前9张")
	@RequestMapping(value = "/addPhoto", method = RequestMethod.POST)
	@ApiResponses({
		@ApiResponse(code = ZhengheConstance.param_fault, message = "参数格式不正确", response = String.class),
		@ApiResponse(code = ZhengheConstance.empty_file, message = "file空", response = String.class),
		@ApiResponse(code = ZhengheConstance.file_mine_fault, message = "file类型错误", response = String.class),
		@ApiResponse(code = ZhengheConstance.upload_fault, message = "上传出错", response = String.class),
		@ApiResponse(code = ZhengheConstance.mhId_not_exist, message = "病历id不存在", response = String.class),
		@ApiResponse(code = ZhengheConstance.cdId_not_exist, message = "病程id不存在", response = String.class),
	})*/
	public ResponseEntity<BaseResult> addPhoto(HttpServletRequest request,@ModelAttribute RequestAddPhoto param,
			@RequestParam(required=false) MultipartFile [] file){
		String id = param.getId();
		String type = param.getType();
		if(Tool.strBlank(id,type) || ((!type.equals("1")) && (!type.equals("2")))||!(file.length>0)){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		if(type.equals("1")){
			if(historyService.get(id) == null){
				return buildFailedResultInfo(ZhengheConstance.mhId_not_exist);
			}
		}else{
			if(diseaseService.get(id) == null){
				return buildFailedResultInfo(ZhengheConstance.cdId_not_exist);
			}
		}

		List<MultipartFile> fileList = new ArrayList<MultipartFile>();
		
		for(int i=0;i<file.length;i++){
			if(i==9)break;
			fileList.add(file[i]);
		}

		ZhengheUpload zhengheUpload = new ZhengheUpload();
		
		if("1".equals(param.getType())){
			zhengheUpload.choosePath(1);
		}else if("2".equals(param.getType())){
			zhengheUpload.choosePath(2);
		}
	
		String result = zhengheUpload.uploadFile(request,fileList);
		if(result.equals("1")){
			return buildFailedResultInfo(ZhengheConstance.empty_file);
		}
		if(result.equals("2")){
			return buildFailedResultInfo(ZhengheConstance.file_mine_fault);
		}
		if(result.equals("3")){
			return buildFailedResultInfo(ZhengheConstance.upload_fault);
		}

		ZhengheCommonImage image = new ZhengheCommonImage();
		image.setRelevanceId(id);
		image.setType(type);
		
		String [] paths = result.split("\\|");
		
		Class<? extends ZhengheCommonImage> clazz = image.getClass();
		try {
			for(int i=0;i<paths.length;i++){
				String str = "setImage"+(i+1);
				Method method = clazz.getDeclaredMethod(str, String.class);
				method.invoke(image, paths[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		imageService.save(image);
		
		return buildSuccessResultInfo(ZhengheConstance.BASE_SUCCESS_CODE);
	}
	
	/*
	 * 获取路径(http协议+服务器ip[或域名]+端口号)
	 */
	private String getBasePath(){

		String basePath = new StringBuilder(request.getScheme()).append("://").append(request.getServerName()).append(":")
				.append(request.getServerPort()).toString();
		return basePath;
	}
		
}