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
import com.fullteem.modules.zhenghe.api.entity.request.RequestNumber;
import com.fullteem.modules.zhenghe.api.entity.response.ResponseProvince;
import com.fullteem.modules.zhenghe.api.utils.ZhengheConstance;
import com.fullteem.modules.zhenghe.entity.ZhengheAddress;
import com.fullteem.modules.zhenghe.entity.ZhengheBuyCar;
import com.fullteem.modules.zhenghe.entity.ZhengheCity;
import com.fullteem.modules.zhenghe.entity.ZhengheDepartments;
import com.fullteem.modules.zhenghe.entity.ZhengheDistrict;
import com.fullteem.modules.zhenghe.entity.ZhengheProduct;
import com.fullteem.modules.zhenghe.entity.ZhengheProductClassify;
import com.fullteem.modules.zhenghe.entity.ZhengheProvincial;
import com.fullteem.modules.zhenghe.service.ZhengheAddressService;
import com.fullteem.modules.zhenghe.service.ZhengheBuyCarService;
import com.fullteem.modules.zhenghe.service.ZhengheCityService;
import com.fullteem.modules.zhenghe.service.ZhengheDepartmentsService;
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
 * 类名: ZhengheDepartmentsApiController</br> 
 * 包名：com.fullteem.modules.zhenghe.api.web </br> 
 * 描述: 科室接口</br>
 * 发布版本号：</br>
 * 开发人员： 李启华</br>
 * 创建时间： 2015年11月25日
 */
@Api(value="ZhengheDepartments",description = "科室")
@Controller
@RequestMapping(value="/api/departmentsApiController")
public class ZhengheDepartmentsApiController extends BaseController{
	@Resource
	ZhengheDepartmentsService departmentService;
	
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述：根据一级科室id返回二级科室 </br>
	 * 开发人员：李启华</br>
	 * 创建时间：2015年11月25日</br>
	 * @param jsonValue
	 * @return
	 */
	@ApiOperation(value = "根据一级科室id返回二级科室", notes = "参数必填")
	@RequestMapping(value = "/ajaxDepList", method = RequestMethod.POST)
	@ApiResponses({
		@ApiResponse(code = ZhengheConstance.param_fault, message = "参数格式不正确", response = String.class),
		@ApiResponse(code = ZhengheConstance.departmentId_not_exist, message = "科室id不存在", response = String.class),
	})
	public ResponseEntity<BaseResult> ajaxDepList(HttpServletRequest request) {
		String id = request.getParameter("id");
		System.out.println("---------------------------------id : "+id);
		if(StringUtils.isBlank(id) ){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		ZhengheDepartments d = departmentService.get(id);
		if(d == null){
			return buildFailedResultInfo(ZhengheConstance.departmentId_not_exist);
		}
		if(!d.getType().equals("1")){
			return buildFailedResultInfo(ZhengheConstance.not_a_top_department);
		}
		ZhengheDepartments _d = new ZhengheDepartments();
		_d.setType("2");
		_d.setSDepartmentsId(id);
		List<ZhengheDepartments> dList = departmentService.findList(_d);
		return buildSuccessResultInfo(dList);
	}
	
	
	
	
		
}