package com.fullteem.modules.zhenghe.api.web;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fullteem.common.web.BaseController;
import com.fullteem.common.web.BaseResult;
import com.fullteem.modules.zhenghe.api.entity.request.RequestAddAddress;
import com.fullteem.modules.zhenghe.api.entity.request.RequestAddToCar;
import com.fullteem.modules.zhenghe.api.entity.request.RequestId;
import com.fullteem.modules.zhenghe.api.entity.request.RequestNumber;
import com.fullteem.modules.zhenghe.api.entity.response.ResponseProvince;
import com.fullteem.modules.zhenghe.api.utils.Tool;
import com.fullteem.modules.zhenghe.api.utils.ZhengheConstance;
import com.fullteem.modules.zhenghe.entity.ZhengheAddress;
import com.fullteem.modules.zhenghe.entity.ZhengheBuyCar;
import com.fullteem.modules.zhenghe.entity.ZhengheCity;
import com.fullteem.modules.zhenghe.entity.ZhengheDistrict;
import com.fullteem.modules.zhenghe.entity.ZhengheProduct;
import com.fullteem.modules.zhenghe.entity.ZhengheProductClassify;
import com.fullteem.modules.zhenghe.entity.ZhengheProvincial;
import com.fullteem.modules.zhenghe.service.ZhengheAddressService;
import com.fullteem.modules.zhenghe.service.ZhengheBuyCarService;
import com.fullteem.modules.zhenghe.service.ZhengheCityService;
import com.fullteem.modules.zhenghe.service.ZhengheDistrictService;
import com.fullteem.modules.zhenghe.service.ZhenghePatientService;
import com.fullteem.modules.zhenghe.service.ZhengheProductService;
import com.fullteem.modules.zhenghe.service.ZhengheProvincialService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

/**
 * 
 * 类名: ZhengheAddressApiController</br> 
 * 包名：com.fullteem.modules.zhenghe.api.web </br> 
 * 描述: 用户地址接口</br>
 * 发布版本号：</br>
 * 开发人员： 李启华</br>
 * 创建时间： 2015年11月17日
 */
@Api(value="ZhengheAddress",description = "地址")
@Controller
@RequestMapping(value="/api/addressApiController")
public class ZhengheAddressApiController extends BaseController{
	@Resource
	ZhengheProvincialService provinceService;
	@Resource
	ZhengheCityService cityService;
	@Resource
	ZhengheDistrictService districtService;
	@Resource
	ZhenghePatientService patientService;
	@Resource
	ZhengheAddressService addressService;
	
	
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述：根据患者id获取地址列表 </br>
	 * 开发人员：李启华</br>
	 * 创建时间：2015年11月17日</br>
	 * @param jsonValue
	 * @return
	 */
	@ApiOperation(value = "根据患者id获取地址列表", notes = "参数必填，患者id")
	@RequestMapping(value = "/getPatientAddress", method = RequestMethod.POST)
	@ApiResponses({
		@ApiResponse(code = ZhengheConstance.param_fault, message = "参数格式不正确", response = String.class),
		@ApiResponse(code = ZhengheConstance.userId_is_null, message = "用户id不存在", response = String.class),
	})
	public ResponseEntity<BaseResult> getPatientAddress(@ApiParam(required = true,value = "必填，患者id")
		@RequestBody RequestNumber param) {
		String patientId = param.getNum();
		if(StringUtils.isBlank(patientId)){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		if(patientService.get(patientId) == null){
			return buildFailedResultInfo(ZhengheConstance.userId_is_null);
		}
		ZhengheAddress addr = new ZhengheAddress();
		addr.setPatientId(patientId);
		List<ZhengheAddress> aList = addressService.findList(addr);
		if(aList != null && aList.size()>0){
			for(ZhengheAddress a : aList){
				a.setProvinceName(provinceService.get(a.getProvincialId()).getName());
				a.setCityName(a.getCityId() == null ? "" : cityService.get(a.getCityId()).getCityName());
				a.setDistrictName(a.getDistrictId() == null ? "" : districtService.get(a.getDistrictId()).getDistrictName());
			}
		}
		return buildSuccessResultInfo(aList);
	}
	
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述：添加地址 </br>
	 * 开发人员：李启华</br>
	 * 创建时间：2015年11月17日</br>
	 * @param jsonValue
	 * @return
	 */
	@ApiOperation(value = "添加地址", notes = "name是收货人，phone是手机号码，patientId是患者id，provinceId是省份id，cityId是城市id，districtId是地区id，address是详细地址。id不用填，修改操作时才需要填")
	@RequestMapping(value = "/addAddress", method = RequestMethod.POST)
	@ApiResponses({
		@ApiResponse(code = ZhengheConstance.param_fault, message = "参数格式不正确", response = String.class),
		@ApiResponse(code = ZhengheConstance.provinceId_not_exist, message = "该省份id不存在", response = String.class),
		@ApiResponse(code = ZhengheConstance.cityId_not_exist, message = "该城市id不存在", response = String.class),
		@ApiResponse(code = ZhengheConstance.districtId_not_exist, message = "该地区id不存在", response = String.class),
	})
	public ResponseEntity<BaseResult> addAddress(@ApiParam(required = true,value = "入参必填")
		@RequestBody RequestAddAddress param) {
		String patientId = param.getPatientId();
		String provinceId = param.getProvinceId();
		String cityId = param.getCityId();
		String districtId = param.getDistrictId();
		String address = param.getAddress();
		String name = param.getName();
		String phone = param.getPhone();
		if(StringUtils.isBlank(provinceId) || StringUtils.isBlank(patientId) || StringUtils.isBlank(address) || StringUtils.isBlank(patientId) 
				|| StringUtils.isBlank(name) || !StringUtils.isNumeric(phone) || StringUtils.isBlank(cityId) || StringUtils.isBlank(districtId)){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		if(patientService.get(patientId) == null){
			return buildFailedResultInfo(ZhengheConstance.userId_is_null);
		}
		if(provinceService.get(provinceId) == null){
			return buildFailedResultInfo(ZhengheConstance.provinceId_not_exist);
		}
		ZhengheAddress addr = new ZhengheAddress();
		addr.setPatientId(patientId);
		addr.setProvincialId(provinceId);
		addr.setAddress(address);
		addr.setName(name);
		addr.setPhone(phone);
		if(StringUtils.isNotBlank(cityId) && cityService.get(cityId) == null){
			return buildFailedResultInfo(ZhengheConstance.cityId_not_exist);
		}else{
			addr.setCityId(cityId);
		}
		if(StringUtils.isNotBlank(districtId) && districtService.get(districtId) == null){
			return buildFailedResultInfo(ZhengheConstance.districtId_not_exist);
		}else{
			addr.setDistrictId(districtId);
		}
		addressService.save(addr);
		
		return buildSuccessResultInfo(ZhengheConstance.BASE_SUCCESS_CODE);
	}
	
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述：修改地址 </br>
	 * 开发人员：李启华</br>
	 * 创建时间：2015年12月02日</br>
	 * @param jsonValue
	 * @return
	 */
	@ApiOperation(value = "修改地址", notes = "id是要修改的地址id，name是收货人，phone是手机号码，patientId是患者id，provinceId是省份id，cityId是城市id，districtId是地区id，address是详细地址。")
	@RequestMapping(value = "/editAddress", method = RequestMethod.POST)
	@ApiResponses({
		@ApiResponse(code = ZhengheConstance.param_fault, message = "参数格式不正确", response = String.class),
		@ApiResponse(code = ZhengheConstance.provinceId_not_exist, message = "该省份id不存在", response = String.class),
		@ApiResponse(code = ZhengheConstance.cityId_not_exist, message = "该城市id不存在", response = String.class),
		@ApiResponse(code = ZhengheConstance.districtId_not_exist, message = "该地区id不存在", response = String.class),
		@ApiResponse(code = ZhengheConstance.address_not_exist, message = "用户地址不存在", response = String.class),
	})
	public ResponseEntity<BaseResult> editAddress(@ApiParam(required = true,value = "入参必填")
		@RequestBody RequestAddAddress param) {
		String id = param.getId();
		String patientId = param.getPatientId();
		String provinceId = param.getProvinceId();
		String cityId = param.getCityId();
		String districtId = param.getDistrictId();
		String address = param.getAddress();
		String name = param.getName();
		String phone = param.getPhone();
		if(Tool.strBlank(id,patientId,provinceId,address,name,phone,cityId,districtId)){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		ZhengheAddress addr = addressService.get(id);
		if(addr == null){
			return buildFailedResultInfo(ZhengheConstance.address_not_exist);
		}
		if(patientService.get(patientId) == null){
			return buildFailedResultInfo(ZhengheConstance.userId_is_null);
		}
		if(provinceService.get(provinceId) == null){
			return buildFailedResultInfo(ZhengheConstance.provinceId_not_exist);
		}
		addr.setPatientId(patientId);
		addr.setProvincialId(provinceId);
		addr.setAddress(address);
		addr.setName(name);
		addr.setPhone(phone);
		
		if(StringUtils.isNotBlank(cityId) && cityService.get(cityId) == null){
			return buildFailedResultInfo(ZhengheConstance.cityId_not_exist);
		}else{
			addr.setCityId(cityId);
		}
		if(StringUtils.isNotBlank(districtId) && districtService.get(districtId) == null){
			return buildFailedResultInfo(ZhengheConstance.districtId_not_exist);
		}else{
			addr.setDistrictId(districtId);
		}
		addressService.save(addr);
		return buildSuccessResultInfo(ZhengheConstance.BASE_SUCCESS_CODE);
	}
	
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述：删除地址 </br>
	 * 开发人员：李启华</br>
	 * 创建时间：2015年12月02日</br>
	 * @param jsonValue
	 * @return
	 */
	@ApiOperation(value = "删除地址", notes = "id必传")
	@RequestMapping(value = "/delAddress", method = RequestMethod.POST)
	@ApiResponses({
		@ApiResponse(code = ZhengheConstance.param_fault, message = "参数格式不正确", response = String.class),
		@ApiResponse(code = ZhengheConstance.address_not_exist, message = "用户地址不存在", response = String.class),
	})
	public ResponseEntity<BaseResult> delAddress(@ApiParam(required = true,value = "id必传")
		@RequestBody RequestId param) {
		String id = param.getId();
		if(Tool.strBlank(id)){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		ZhengheAddress addr = addressService.get(id);
		if(addr == null){
			return buildFailedResultInfo(ZhengheConstance.address_not_exist);
		}
		addressService.delete(addr);
		return buildSuccessResultInfo(ZhengheConstance.BASE_SUCCESS_CODE);
	}
	
	
	
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述:获取所有省份 </br>
	 * 开发人员：李启华</br>
	 * 创建时间：2015年11月17日</br>
	 * @param jsonValue
	 * @return
	 */
	@ApiOperation(value = "liqihua-获取所有省份", notes = "不需要参数")
	@RequestMapping(value = "/getProvinceList", method = RequestMethod.POST)
	@ApiResponses({
		@ApiResponse(code = ZhengheConstance.BASE_SUCCESS_CODE, message = "成功", response = ResponseProvince.class),
	})
	public ResponseEntity<BaseResult> getProvinceList() {
		List<ZhengheProvincial> pList = provinceService.findList(null);
		return buildSuccessResultInfo(pList);
	}
	
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述：根据省份id获取城市列表 </br>
	 * 开发人员：李启华</br>
	 * 创建时间：2015年11月17日</br>
	 * @param jsonValue
	 * @return
	 */
	@ApiOperation(value = "根据省份id获取城市列表", notes = "参数必填")
	@RequestMapping(value = "/getCityList", method = RequestMethod.POST)
	@ApiResponses({
		@ApiResponse(code = ZhengheConstance.param_fault, message = "参数格式不正确", response = String.class),
		@ApiResponse(code = ZhengheConstance.provinceId_not_exist, message = "该省份id不存在", response = String.class),
	})
	public ResponseEntity<BaseResult> getCityList(@ApiParam(required = true,value = "必填")
		@RequestBody RequestNumber param) {
		String provinceId = param.getNum();
		if(StringUtils.isBlank(provinceId) || !StringUtils.isNumeric(provinceId)){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		if(provinceService.get(provinceId) == null){
			return buildFailedResultInfo(ZhengheConstance.provinceId_not_exist);
		}
		List<ZhengheCity> pList = cityService.findByProvinceId(provinceId);
		return buildSuccessResultInfo(pList);
	}
	
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述：根据城市id获取地区列表 </br>
	 * 开发人员：李启华</br>
	 * 创建时间：2015年11月17日</br>
	 * @param jsonValue
	 * @return
	 */
	@ApiOperation(value = "根据城市id获取地区列表", notes = "参数必填")
	@RequestMapping(value = "/getDistrictList", method = RequestMethod.POST)
	@ApiResponses({
		@ApiResponse(code = ZhengheConstance.param_fault, message = "参数格式不正确", response = String.class),
		@ApiResponse(code = ZhengheConstance.cityId_not_exist, message = "该城市id不存在", response = String.class),
	})
	public ResponseEntity<BaseResult> getDistrictList(@ApiParam(required = true,value = "必填")
		@RequestBody RequestNumber param) {
		String cityId = param.getNum();
		if(StringUtils.isBlank(cityId) || !StringUtils.isNumeric(cityId)){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		if(cityService.get(cityId) == null){
			return buildFailedResultInfo(ZhengheConstance.cityId_not_exist);
		}
		List<ZhengheDistrict> dList = districtService.findByCityId(cityId);
		return buildSuccessResultInfo(dList);
	}
	
	
	
	
	
	
	
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述：根据省份id获取城市列表 </br>
	 * 开发人员：李启华</br>
	 * 创建时间：2015年11月19日</br>
	 * @param jsonValue
	 * @return
	 */
	@ApiOperation(value = "根据省份id获取城市列表", notes = "参数必填")
	@RequestMapping(value = "/ajaxCityList", method = RequestMethod.POST)
	@ApiResponses({
		@ApiResponse(code = ZhengheConstance.param_fault, message = "参数格式不正确", response = String.class),
		@ApiResponse(code = ZhengheConstance.provinceId_not_exist, message = "该省份id不存在", response = String.class),
	})
	public ResponseEntity<BaseResult> ajaxCityList(HttpServletRequest request) {
		String provinceId = request.getParameter("pid");
		System.out.println("---------------------------------pid : "+provinceId);
		if(StringUtils.isBlank(provinceId) || !StringUtils.isNumeric(provinceId)){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		if(provinceService.get(provinceId) == null){
			return buildFailedResultInfo(ZhengheConstance.provinceId_not_exist);
		}
		List<ZhengheCity> pList = cityService.findByProvinceId(provinceId);
		return buildSuccessResultInfo(pList);
	}
	
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述：根据城市id获取地区列表 </br>
	 * 开发人员：李启华</br>
	 * 创建时间：2015年11月19日</br>
	 * @param jsonValue
	 * @return
	 */
	@ApiOperation(value = "根据城市id获取地区列表", notes = "参数必填")
	@RequestMapping(value = "/ajaxDistrictList", method = RequestMethod.POST)
	@ApiResponses({
		@ApiResponse(code = ZhengheConstance.param_fault, message = "参数格式不正确", response = String.class),
		@ApiResponse(code = ZhengheConstance.cityId_not_exist, message = "该城市id不存在", response = String.class),
	})
	public ResponseEntity<BaseResult> ajaxDistrictList(HttpServletRequest request) {
		String cityId = request.getParameter("cid");
		if(StringUtils.isBlank(cityId) || !StringUtils.isNumeric(cityId)){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		if(cityService.get(cityId) == null){
			return buildFailedResultInfo(ZhengheConstance.cityId_not_exist);
		}
		List<ZhengheDistrict> dList = districtService.findByCityId(cityId);
		return buildSuccessResultInfo(dList);
	}
	
	
	
		
}