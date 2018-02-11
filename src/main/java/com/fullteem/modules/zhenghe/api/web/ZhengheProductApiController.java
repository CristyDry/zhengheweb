package com.fullteem.modules.zhenghe.api.web;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.fullteem.modules.zhenghe.api.utils.ProductPicUtils;
import net.sf.json.JSONObject;

import org.activiti.engine.impl.util.json.JSONArray;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fullteem.common.web.BaseController;
import com.fullteem.common.web.BaseResult;
import com.fullteem.modules.zhenghe.api.entity.request.RequestNumber;
import com.fullteem.modules.zhenghe.api.entity.request.RequestProduct;
import com.fullteem.modules.zhenghe.api.entity.request.RequestSearchProduct;
import com.fullteem.modules.zhenghe.api.utils.ZhengheConstance;
import com.fullteem.modules.zhenghe.entity.ZhengheCarousel;
import com.fullteem.modules.zhenghe.entity.ZhengheCollect;
import com.fullteem.modules.zhenghe.entity.ZhengheCommonImage;
import com.fullteem.modules.zhenghe.entity.ZhengheProduct;
import com.fullteem.modules.zhenghe.entity.ZhengheProductClassify;
import com.fullteem.modules.zhenghe.service.ZhengheCarouselService;
import com.fullteem.modules.zhenghe.service.ZhengheCollectService;
import com.fullteem.modules.zhenghe.service.ZhengheCommonImageService;
import com.fullteem.modules.zhenghe.service.ZhengheProductClassifyService;
import com.fullteem.modules.zhenghe.service.ZhengheProductService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@Api(value="ZhengheProduct" ,description = "药品")
@Controller
@RequestMapping(value = "/api/ZhengheProduct")
public class ZhengheProductApiController extends BaseController{
	@Resource
	ZhengheCarouselService carouselService;
	@Resource
	ZhengheProductClassifyService classifyService;
	@Resource
	ZhengheProductService productService;
	@Autowired
	private ZhengheCollectService zhengheCollectService;
	@Resource 
	ZhengheCommonImageService imageService;
	@Autowired
	private HttpServletRequest request;


	/**
	 * 
	 * 方法名: </br>
	 * 详述:获取商城顶部轮播图 </br>
	 * 开发人员：李启华</br>
	 * 创建时间：2015年11月16日</br>
	 * @return
	 */
	@ApiOperation(value = "获取商城顶部轮播图", notes = "图片数量必填")
	@RequestMapping(value = "/getStoreCarousel", method = RequestMethod.POST)
	@ApiResponses({
		@ApiResponse(code = ZhengheConstance.param_fault, message = "参数格式不正确", response = String.class),
	})
	public ResponseEntity<BaseResult> getStoreCarousel(@ApiParam(required = true, name = "", value = "图片数量必填")
		@RequestBody RequestNumber requestNumber,HttpServletRequest request) {
		String num = requestNumber.getNum();
		if(StringUtils.isBlank(num) || !StringUtils.isNumeric(num)){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		
		List<ZhengheCarousel> cList = carouselService.findListByNum(Integer.parseInt(num));
		for(ZhengheCarousel c : cList){
			c.setAvatar(imageServer+c.getAvatar());
		}
		return buildSuccessResultInfo(cList);
	}
	
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述:获取商品分类列表 </br>
	 * 开发人员：李启华</br>
	 * 创建时间：2015年11月16日</br>
	 * @return
	 */
	@ApiOperation(value = "获取商品分类列表", notes = "不需要参数")
	@RequestMapping(value = "/getProductClassifyList", method = RequestMethod.POST)
	public ResponseEntity<BaseResult> getProductClassifyList(HttpServletRequest request) {
		List<ZhengheProductClassify> cList = classifyService.findList(null);
		for(ZhengheProductClassify c : cList){
			c.setAvatar(imageServer+c.getAvatar());
		}
		return buildSuccessResultInfo(cList);
	}
	
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述:搜索商品接口 </br>
	 * 开发人员：李启华</br>
	 * 创建时间：2015年11月16日</br>
	 * @return
	 */
	@ApiOperation(value = "搜索商品", notes = "全部选填。classifyId是分类id，priceMin和priceMax是商品价格范围，成双提供有效，type必须为otc(非处方药)rx(处方药)之一，否则无效，orderBy是排序条件:1.更新时间(综合)，2.销售量，3.价格低到高，4.价格高到低。pageNo是页码，默认0，pageSize是每页条数，默认4")
	@RequestMapping(value = "/searchProduct", method = RequestMethod.POST)
	@ApiResponses({
		@ApiResponse(code = ZhengheConstance.param_fault, message = "参数格式不正确", response = String.class),
	})
	public ResponseEntity<BaseResult> searchProduct(@ApiParam(required = true,value = "参数orderBy,排序条件:1.更新时间(综合)，2.销售量，3.价格低到高，4.价格高到低")
		@RequestBody RequestSearchProduct param) {
		String keys = param.getKeys();				//搜索关键字
		String classifyId = param.getClassifyId();	//分类id
		String priceMin = param.getPriceMin();		//价格最小值
		String priceMax = param.getPriceMax();		//价格最大值
		String type = param.getType();				//药品类型:otc非处方药rx处方药
		Boolean isSimple = param.getSimple();		//不返还说明
		String orderBy = param.getOrderBy();		//排序条件:1.更新时间(综合)，2.销售量，3.价格低到高，4.价格高到低
		String pageNo = param.getPageNo();			//页码
		String pageSize = param.getPageSize();		//每页数量
		//com.fullteem.common.utils.Log.println("------------------------- keys:"+keys+" ,classifyId:"+classifyId+" ,priceMin:"+priceMin+" ,priceMax:"+priceMax+" ,type:"+type+" ,orderBy:"+orderBy+" ,pageNo:"+pageNo+" ,pageSize:"+pageSize);
		if(StringUtils.isBlank(pageNo) || !StringUtils.isNumeric(pageNo)){
			pageNo = "0";
		}
		if(StringUtils.isBlank(pageSize) || !StringUtils.isNumeric(pageSize)){
			pageSize = "4";
		}
		if(StringUtils.isBlank(classifyId)){
			classifyId = null;
		}
		String priceBetween = null;
		/*
		 * 如果priceMin和priceMax两个参数都不为空而且都是数子类型，变量priceBetween就生成between语句
		 */
		if(StringUtils.isNotBlank(priceMin) && StringUtils.isNotBlank(priceMax)){
			try{
				priceBetween = " between "+Double.parseDouble(priceMin)+" and "+Double.parseDouble(priceMax);
			}catch(NumberFormatException e){priceBetween = null;}
		}
		/*
		 * 药品类型参数必须符合otc或rx两者之一，否则设null
		 */
		if(!   ( StringUtils.isNotBlank(type) && (type.equals("otc") || type.equals("rx")) )   ){
			type=null;
		}
		/*
		 * 排序参数
		 */
		if(StringUtils.isNotBlank(orderBy) && StringUtils.isNumeric(orderBy)){
			int orderType = Integer.parseInt(orderBy);
			switch(orderType){
			case 1:
				orderBy = "a.update_date desc";break;			//1.时间(综合)
			case 2:
				orderBy = "a.sales_num desc";break;			//2.销售量
			case 3:
				orderBy = "a.price asc";break;				//3.价格低到高
			case 4:
				orderBy = "a.price desc";break;				//4.价格高到低
			default:
				orderBy = "a.update_date desc";break;			//默认按时间排序
			}
		}else{
			orderBy = "a.update_date desc";					//参数为空，默认按时间排序
		}
		List<ZhengheProduct> pList = productService.searchProduct(keys, classifyId, priceBetween, type, orderBy,Integer.parseInt(pageNo),Integer.parseInt(pageSize));
		for(ZhengheProduct p : pList){
			//List<ZhengheCarousel> cList = carouselService.findListByProductId(p.getId());
			//p.setProductPic((cList == null || cList.size()<1) ? "" : this.imgPrefix+cList.get(0).getAvatar());
			p.setProductPic(ProductPicUtils.getPic(p.getId()));
		}
		for(ZhengheProduct z : pList){
			if(isSimple != null && isSimple){
				z.setExplains(null);
			}
		}
		return buildSuccessResultInfo(pList);
	}
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述:根据分类id获取商品列表 </br>
	 * 开发人员：李启华</br>
	 * 创建时间：2015年11月16日</br>
	 * @return
	 */
	@ApiOperation(value = "根据分类id获取商品列表", notes = "classifyId(分类id)必传，keys不用传，其他选填。priceMin和priceMax是商品价格范围，成双提供有效，type必须为otc(非处方药)rx(处方药)之一，否则无效，orderBy是排序条件:1.更新时间(综合)，2.销售量，3.价格低到高，4.价格高到低")
	@RequestMapping(value = "/getProductList", method = RequestMethod.POST)
	@ApiResponses({
		@ApiResponse(code = ZhengheConstance.param_fault, message = "参数格式不正确", response = String.class),
	})
	public ResponseEntity<BaseResult> getProductList(@ApiParam(required = true,value = "参数orderBy,排序条件:1.更新时间(综合)，2.销售量，3.价格低到高，4.价格高到低")
		@RequestBody RequestSearchProduct param) {
		String classifyId = param.getClassifyId();	//分类id
		String priceMin = param.getPriceMin();		//价格最小值
		String priceMax = param.getPriceMax();		//价格最大值
		String type = param.getType();				//药品类型:otc非处方药rx处方药
		String orderBy = param.getOrderBy();		//排序条件:1.更新时间(综合)，2.销售量，3.价格低到高，4.价格高到低
		String pageNo = param.getPageNo();			//页码
		String pageSize = param.getPageSize();		//每页数量
		if(StringUtils.isBlank(classifyId)){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		if(StringUtils.isBlank(pageNo) || !StringUtils.isNumeric(pageNo)){
			pageNo = "0";
		}
		if(StringUtils.isBlank(pageSize) || !StringUtils.isNumeric(pageSize)){
			pageSize = "4";
		}
		String priceBetween = null;
		/*
		 * 如果priceMin和priceMax两个参数都不为空而且都是数子类型，变量priceBetween就生成between语句
		 */
		if(StringUtils.isNotBlank(priceMin) && StringUtils.isNotBlank(priceMax)){
			try{
				priceBetween = " between "+Double.parseDouble(priceMin)+" and "+Double.parseDouble(priceMax);
			}catch(NumberFormatException e){priceBetween = null;}
		}
		/*
		 * 药品类型参数必须符合otc或rx两者之一，否则设null
		 */
		if(!   ( StringUtils.isNotBlank(type) && (type.equals("otc") || type.equals("rx")) )   ){
			type=null;
		}
		/*
		 * 排序参数
		 */
		if(StringUtils.isNotBlank(orderBy) && StringUtils.isNumeric(orderBy)){
			int orderType = Integer.parseInt(orderBy);
			switch(orderType){
			case 1:
				orderBy = "a.update_date desc";break;			//1.时间(综合)
			case 2:
				orderBy = "a.sales_num desc";break;			//2.销售量
			case 3:
				orderBy = "a.price asc";break;				//3.价格低到高
			case 4:
				orderBy = "a.price desc";break;				//4.价格高到低
			default:
				orderBy = "a.update_date desc";break;			//默认按时间排序
			}
		}else{
			orderBy = "a.update_date desc";					//参数为空，默认按时间排序
		}
		List<ZhengheProduct> pList = productService.getProductListByCid(classifyId, priceBetween, type, orderBy,Integer.parseInt(pageNo),Integer.parseInt(pageSize));
		for(ZhengheProduct p : pList){
			//List<ZhengheCarousel> cList = carouselService.findListByProductId(p.getId());
			//p.setProductPic((cList == null || cList.size()<1) ? "" : this.imgPrefix+cList.get(0).getAvatar());
			//ZhengheCommonImage images = imageService.findByRelevanceId(p.getId());
			//if(images!=null){
			//	p.setProductPic(getBasePath()+images.getImage1());
			//}
			p.setProductPic(ProductPicUtils.getPic(p.getId()));
		}
		return buildSuccessResultInfo(pList);
	}

	/**
	 * 
	 * 方法名: </br>
	 * 详述:商品详情 </br>
	 * 开发人员：李启华</br>
	 * 创建时间：2015年11月16日</br>
	 * @return
	 * @throws IOException 
	 */
	@ApiOperation(value = "商品详情", notes = "商品id必填,[如果用户已登录,则要传入用户id和类型,后台判断该用户是否收藏该商品(1->已收藏,0->未收藏)]")
	@RequestMapping(value = "/getProductDesc", method = RequestMethod.POST)
	@ApiResponses({
		@ApiResponse(code = ZhengheConstance.param_fault, message = "参数格式不正确", response = String.class),
		@ApiResponse(code = ZhengheConstance.productId_is_null, message = "商品不存在", response = String.class),
	})
	public ResponseEntity<BaseResult> getProductDesc(@ApiParam(required=true,value="商品id必填,userType:1->患者,2->医生;") 
			@RequestBody RequestProduct param) throws IOException {
		
		String productId = param.getProductId();
		String userId = param.getUserId();
		String userType = param.getUserType();
		if(StringUtils.isBlank(productId)){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		ZhengheProduct p = productService.get(productId);
		/*List<String> cList = null;
		if(p!=null){
			ZhengheCarousel c = new ZhengheCarousel();
			c.setProductId(p.getId());
			cList = carouselService.findList(c);
		}
		if(cList != null && cList.size() > 0){
			for(ZhengheCarousel c : cList){
				c.setAvatar(this.imgPrefix+c.getAvatar());
			}
		}*/
		List<ZhengheCollect> collectList=null;
		if(!StringUtils.isBlank(userType)&&!StringUtils.isBlank(userId)){
			ZhengheCollect collect = new ZhengheCollect();
			collect.setProductId(productId);
			collect.setPatientId(userId);
			collect.setType(userType);
			
			collectList = zhengheCollectService.findList(collect);
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		if(p!=null){
			map.put("product", p);
			List<String> images = ProductPicUtils.getImage(p.getId());
			if(images!=null){
				if(images.size()==0){
					map.put("carousel",new com.alibaba.fastjson.JSONArray());
				}else{
					map.put("carousel",images);
				}
			}else{
				map.put("carousel",new com.alibaba.fastjson.JSONArray());
			}
			
			if(collectList==null||collectList.size()==0){
				map.put("isCollect", "0");
			}else{
				map.put("isCollect", "1");
			}
		}else{
			return buildFailedResultInfo(ZhengheConstance.productId_is_null);
		}
		
		return buildSuccessResultInfo(map);
	}

}
