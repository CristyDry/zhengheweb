package com.fullteem.modules.zhenghe.api.web;


import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fullteem.common.mapper.JsonMapper;
import com.fullteem.common.web.BaseController;
import com.fullteem.common.web.BaseResult;
import com.fullteem.modules.zhenghe.api.entity.request.RequestTest;
import com.fullteem.modules.zhenghe.api.utils.CosmeticUpload;
import com.fullteem.modules.zhenghe.api.utils.ZhengheConstance;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

/**
 * 
 * 类名: TestApiController</br> 
 * 包名：com.fullteem.modules.cosmetic.api.web </br> 
 * 描述: 上传测试</br>
 * 发布版本号：</br>
 * 开发人员： 朱恋青</br>
 * 创建时间： 2015年11月2日
 */
@Api(value="TestApiController",description = "上传测试")
@Controller
@RequestMapping(value="/api/testApiController")
public class TestApiController extends BaseController{
	
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述: 上传测试</br>
	 * 开发人员：朱恋青</br>
	 * 创建时间：2015年11月2日</br>
	 * @param request
	 * @param test
	 * @param file
	 * @return
	 */
	@ApiOperation(value = "test1",notes = "test",httpMethod = "POST",response = String.class)
	@ApiResponses({	
		@ApiResponse(code = ZhengheConstance.BASE_SUCCESS_CODE,message = "成功",response =  String.class),
		@ApiResponse(code = ZhengheConstance.BASE_FAIL_CODE,message = "失败",response = String.class)	
	})
	@RequestMapping(value = "/test1",method = RequestMethod.POST)
	public ResponseEntity<BaseResult> test1(HttpServletRequest request,@ModelAttribute RequestTest test,@RequestParam MultipartFile file){
		
		String username = test.getUsername();
		String password = test.getUserpwd();
		System.out.println("----------------------------- username : "+username+" , password : "+password);
		
		//上传开始================================
		String str = CosmeticUpload.uploadFile(request, "1", file);
		String realPath = request.getSession().getServletContext()
				.getRealPath("/").replaceAll("\\\\", "/");
		//上传结束================================
		//数据库保存相对路径，读取的时候，拼接网络地址返回给前端
		
		//System.out.println(realPath);
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
		return  buildSuccessResultInfo("fileUrl:"+basePath+str);
	}
	
	
	
	
		
}