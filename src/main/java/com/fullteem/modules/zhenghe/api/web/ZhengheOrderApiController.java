package com.fullteem.modules.zhenghe.api.web;


import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.fullteem.modules.zhenghe.api.utils.Pays;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fullteem.common.web.BaseController;
import com.fullteem.common.web.BaseResult;
import com.fullteem.modules.zhenghe.api.entity.request.RequestAddOrder;
import com.fullteem.modules.zhenghe.api.entity.request.RequestAddToCar;
import com.fullteem.modules.zhenghe.api.entity.request.RequestBuyNow;
import com.fullteem.modules.zhenghe.api.entity.request.RequestDelFromCar;
import com.fullteem.modules.zhenghe.api.entity.request.RequestId;
import com.fullteem.modules.zhenghe.api.entity.request.RequestNumber;
import com.fullteem.modules.zhenghe.api.entity.request.RequestOrderList;
import com.fullteem.modules.zhenghe.api.utils.Tool;
import com.fullteem.modules.zhenghe.api.utils.ZhengheConstance;
import com.fullteem.modules.zhenghe.entity.ZhengheAddress;
import com.fullteem.modules.zhenghe.entity.ZhengheBuyCar;
import com.fullteem.modules.zhenghe.entity.ZhengheCarousel;
import com.fullteem.modules.zhenghe.entity.ZhengheCommonImage;
import com.fullteem.modules.zhenghe.entity.ZhengheOrder;
import com.fullteem.modules.zhenghe.entity.ZhengheOrderItem;
import com.fullteem.modules.zhenghe.entity.ZhengheProduct;
import com.fullteem.modules.zhenghe.service.ZhengheAddressService;
import com.fullteem.modules.zhenghe.service.ZhengheBuyCarService;
import com.fullteem.modules.zhenghe.service.ZhengheCarouselService;
import com.fullteem.modules.zhenghe.service.ZhengheCityService;
import com.fullteem.modules.zhenghe.service.ZhengheCommonImageService;
import com.fullteem.modules.zhenghe.service.ZhengheDistrictService;
import com.fullteem.modules.zhenghe.service.ZhengheDoctorService;
import com.fullteem.modules.zhenghe.service.ZhengheOrderItemService;
import com.fullteem.modules.zhenghe.service.ZhengheOrderService;
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
 * 类名: OrderApiController</br> 
 * 包名：com.fullteem.modules.zhenghe.api.web </br> 
 * 描述: 商品订单控制层</br>
 * 发布版本号：</br>
 * 开发人员： 李启华</br>
 * 创建时间： 2015年11月17日
 */
@Api(value="ZhengheOrder",description = "订单接口")
@Controller
@RequestMapping(value="/api/orderApiController")
public class ZhengheOrderApiController extends BaseController{
	@Resource
	ZhengheProductService productService;
	@Resource
	ZhenghePatientService patientService;
	@Resource
	ZhengheBuyCarService buyCarService;
	@Resource
	ZhengheOrderService orderService;
	@Resource
	ZhengheAddressService addressService;
	@Resource
	ZhengheOrderItemService orderItemService;
	@Resource
	ZhengheProvincialService provinceService;
	@Resource
	ZhengheCityService cityService;
	@Resource
	ZhengheDistrictService districtService;
	@Resource
	ZhengheDoctorService doctorService;
	@Resource
	ZhengheCarouselService carouselService;
	@Autowired
	private HttpServletRequest request;
	@Resource 
	ZhengheCommonImageService imageService;
	
	
	
	@ApiOperation(value = "取消订单 ", notes = "传入订单id")
	@RequestMapping(value = "/cancelOrder", method = RequestMethod.POST)
	@ApiResponses({
		@ApiResponse(code = ZhengheConstance.param_fault, message = "参数格式不正确", response = String.class),
		@ApiResponse(code = ZhengheConstance.orderId_not_exist, message = "订单id不存在", response = String.class),
		@ApiResponse(code = ZhengheConstance.BASE_SUCCESS_CODE, message = "操作成功", response = String.class),
		
	})
	public ResponseEntity<BaseResult> cancelOrder(@ApiParam(required = true,value = "订单id必填")
		@RequestBody RequestId requestId) {
		
		String id = requestId.getId();
		
		if(StringUtils.isBlank(id)){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		ZhengheOrder order = orderService.get(id);
		if(order==null){
			return buildFailedResultInfo(ZhengheConstance.orderId_not_exist);
		}
		
		order.setStatus("5");
		orderService.save(order);
		
		return buildSuccessResultInfo(ZhengheConstance.BASE_SUCCESS_CODE);
	}
	
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述:已收货 </br>
	 * 开发人员：李启华</br>
	 * 创建时间：2015年11月17日</br>
	 * @param jsonValue
	 * @return
	 */
	@ApiOperation(value = "订单支付-付款(订单状态：3已发货->4已收货) ", notes = "订单id，必传")
	@RequestMapping(value = "/gotProduct", method = RequestMethod.POST)
	@ApiResponses({
		@ApiResponse(code = ZhengheConstance.param_fault, message = "参数格式不正确", response = String.class),
		@ApiResponse(code = ZhengheConstance.orderId_not_exist, message = "订单id不存在", response = String.class),
		@ApiResponse(code = ZhengheConstance.BASE_SUCCESS_CODE, message = "操作成功", response = String.class),
		@ApiResponse(code = ZhengheConstance.order_status_fault, message = "订单状态异常", response = String.class),
	})
	public ResponseEntity<BaseResult> gotProduct(@ApiParam(required = true,value = "订单id，必传。订单状态：1待支付2已支付3已发货4已收货5已取消")
		@RequestBody RequestNumber param) {
		String orderId = param.getNum();
		if(StringUtils.isBlank(orderId)){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		ZhengheOrder order = orderService.get(orderId);
		if(order == null){
			return buildFailedResultInfo(ZhengheConstance.orderId_not_exist);
		}
		if(!order.getStatus().equals("3")){
			return buildFailedResultInfo(ZhengheConstance.order_status_fault);
		}
		order.setStatus("4");
		orderService.save(order);
		return buildSuccessResultInfo(ZhengheConstance.BASE_SUCCESS_CODE);
	}
	
	
	
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述:订单支付 </br>
	 * 开发人员：李启华</br>
	 * 创建时间：2015年11月17日</br>
	 * @param jsonValue
	 * @return
	 */
	@ApiOperation(value = "订单支付-付款(订单状态：1未付款->2已付款) ", notes = "订单id，必传")
	@RequestMapping(value = "/orderOrder", method = RequestMethod.POST)
	@ApiResponses({
		@ApiResponse(code = ZhengheConstance.param_fault, message = "参数格式不正确", response = String.class),
		@ApiResponse(code = ZhengheConstance.orderId_not_exist, message = "订单id不存在", response = String.class),
		@ApiResponse(code = ZhengheConstance.BASE_SUCCESS_CODE, message = "操作成功", response = String.class),
		@ApiResponse(code = ZhengheConstance.order_status_fault, message = "订单状态异常", response = String.class),
	})
	public ResponseEntity<BaseResult> orderOrder(@ApiParam(required = true,value = "订单id，必传。订单状态：1待支付2已支付3已发货4已收货5已取消")
		@RequestBody RequestNumber param) {
		String orderId = param.getNum();
		if(StringUtils.isBlank(orderId)){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		ZhengheOrder order = orderService.get(orderId);
		if(order == null){
			return buildFailedResultInfo(ZhengheConstance.orderId_not_exist);
		}
		if(!order.getStatus().equals("1")){
			return buildFailedResultInfo(ZhengheConstance.order_status_fault);
		}
		order.setStatus("2");
		orderService.save(order);
		return buildSuccessResultInfo(ZhengheConstance.BASE_SUCCESS_CODE);
	}
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述:获取订单列表(我的订单) </br>
	 * 开发人员：李启华</br>
	 * 创建时间：2015年11月17日</br>
	 * @param jsonValue
	 * @return
	 */
	@ApiOperation(value = "获取订单列表(我的订单) ", notes = "病人id必填,订单状态选填(订单状态6为获取已支付(状态2)和已发货(状态3)的订单)")
	@RequestMapping(value = "/orderList", method = RequestMethod.POST)
	@ApiResponses({
		@ApiResponse(code = ZhengheConstance.param_fault, message = "参数格式不正确", response = String.class),
		@ApiResponse(code = ZhengheConstance.userId_is_null, message = "用户id不存在", response = String.class),
	})
	public ResponseEntity<BaseResult> orderList(@ApiParam(required = true,value = "订单状态：1待支付2已支付3已发货4已收货5已取消")
		@RequestBody RequestOrderList param,HttpServletRequest request) {
		String patientId = param.getPatientId();
		String status = param.getStatus();
		if(StringUtils.isBlank(patientId)){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		if(patientService.get(patientId) == null){
			return buildFailedResultInfo(ZhengheConstance.userId_is_null);
		}
		ZhengheOrder order = new ZhengheOrder();
		List<ZhengheOrder> oList = new ArrayList<ZhengheOrder>();
		if(!StringUtils.isBlank(status) && !StringUtils.isNumeric(status)){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}else{
			order.setPatientId(patientId);
			if("6".equals(status)){
				order.setStatus("2");
				List<ZhengheOrder> oList2 = orderService.findList(order);
				oList.addAll(oList2);
				order.setStatus("3");
				List<ZhengheOrder> oList3 = orderService.findList(order);
				oList.addAll(oList3);
			}else{
				order.setStatus(status);
				List<ZhengheOrder> oList1 = orderService.findList(order);
				oList.addAll(oList1);
			}
		}
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
		if(oList != null && oList.size() > 0){
			for(ZhengheOrder o : oList){
				ZhengheOrderItem i = new ZhengheOrderItem();
				i.setOrderId(o.getId());
				o.setExpressName(o.getExpressName()==null?"":o.getExpressName());
				o.setExpressNo(o.getExpressNo()==null?"":o.getExpressNo());
				List<ZhengheOrderItem> iList = orderItemService.findList(i);
				if(iList != null && iList.size() > 0){
					for(ZhengheOrderItem item : iList){
						// 找出订单项对应的商品规格
						ZhengheProduct product = productService.get(item.getProductId());
						item.setStandard(product.getStandard());
						// 找出订单项对应的图片
						/*List<ZhengheCarousel> carousel = carouselService.findListByProductId(product.getId());
						item.setProductPic((carousel == null || carousel.size() < 1) ? "" : basePath+carousel.get(0).getAvatar());*/
						// 123456
						List<String> images = getImage(product.getId());
						item.setProductPic((images == null || images.size() < 1) ? "" : images.get(0));
					}
					o.setItems(iList);
				}
			}
		}
		return buildSuccessResultInfo(oList);
	}
	
	
	private List<String> getImage(String id){
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
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
	 * 详述:购物车->生成订单 </br>
	 * 开发人员：李启华</br>
	 * 创建时间：2015年11月17日</br>
	 * @param jsonValue
	 * @return
	 */
	@ApiOperation(value = "购物车->生成订单 ", notes = "病人id,地址id和购物车项id全部必填，多个购物车项用<英文>逗号拼接")
	@RequestMapping(value = "/addOrder", method = RequestMethod.POST)
	@ApiResponses({
		@ApiResponse(code = ZhengheConstance.param_fault, message = "参数格式不正确", response = String.class),
		@ApiResponse(code = ZhengheConstance.userId_is_null, message = "用户id不存在", response = String.class),
		@ApiResponse(code = ZhengheConstance.address_not_exist, message = "地址id不存在", response = String.class),
		@ApiResponse(code = ZhengheConstance.empty_buy_cart, message = "购物车是空的", response = String.class),
		@ApiResponse(code = ZhengheConstance.product_not_in_car, message = "购物车没有该商品", response = String.class),
	})
	public ResponseEntity<BaseResult> addOrder(@ApiParam(required = true,value = "病人id，地址id和购物车项id全部必填")
		@RequestBody RequestAddOrder param) {
		String addressId = param.getAddressId();
		String patientId = param.getPatientId();
		String cartIds = param.getCartIds();
		
		if(StringUtils.isBlank(addressId) || StringUtils.isBlank(patientId)||StringUtils.isBlank(cartIds)){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		if(patientService.get(patientId) == null){
			return buildFailedResultInfo(ZhengheConstance.userId_is_null);
		}
		ZhengheAddress addr = addressService.get(addressId);
		if(addr == null){
			return buildFailedResultInfo(ZhengheConstance.address_not_exist);
		}
		/*
		 * 检查该患者的购物车是不是空
		 */
		ZhengheBuyCar bc = new ZhengheBuyCar();
		bc.setPatientId(patientId);
		List<ZhengheBuyCar> cList = buyCarService.findList(bc);
		if(cList == null || cList.size()<1){
			return buildFailedResultInfo(ZhengheConstance.empty_buy_cart);
		}
		/*
		 * 计算总额
		 */
		double totalAmount = 0.0;
		String[] cIds = cartIds.split(",");
		for(String cId : cIds){
			ZhengheBuyCar buyCarItem = buyCarService.get(cId);
			if(buyCarItem == null){
				return buildFailedResultInfo(ZhengheConstance.product_not_in_car);
			}
			totalAmount += buyCarItem.getSumPrice();
		}
		/*
		 * 拼接地址字符
		 */
		String province = provinceService.get(addr.getProvincialId()).getName();
		String city = cityService.get(addr.getCityId()) == null ? "" : cityService.get(addr.getCityId()).getCityName();
		String district = districtService.get(addr.getDistrictId()) == null ? "" : districtService.get(addr.getDistrictId()).getDistrictName();
		String orderAddress = province + city + district + addr.getAddress();
		/*
		 * 生成订单
		 */
		ZhengheOrder order = new ZhengheOrder();
		order.setParentOrderNo(makeOrderNo());			//订单号
		order.setTotalAmount(totalAmount+"");			//订单总额
		order.setPatientId(patientId);					//患者id
		order.setStatus("1");							//状态-> 1未支付
		order.setAddress(orderAddress);					//送货地址
		order.setCreateDate(new Date());				//下单时间
		order.setName(addr.getName());					//收货人
		order.setPhone(addr.getPhone());				//手机号码
		orderService.save(order);
		/*
		 * 循环购物车每一项，生成订单项
		 */
		for(String cId : cIds){
			ZhengheBuyCar buyCarItem = buyCarService.get(cId);
			ZhengheProduct p = productService.get(buyCarItem.getProductId());
			ZhengheOrderItem item = new ZhengheOrderItem();
			item.setProductId(p.getId());
			item.setOrderId(order.getId());
			item.setOrderItemNo(makeOrderItemNo());
			item.setProductName(p.getProductName());
			item.setOriginalPrice(p.getPrice()+"");
			item.setRealityPrice(p.getPrice()+"");
			item.setCount(buyCarItem.getCount());
			item.setSumPrice(buyCarItem.getSumPrice()+"");
			if(buyCarItem.getRemark() != null && (doctorService.get(buyCarItem.getRemark()) != null)){
				item.setDoctorId(buyCarItem.getRemark());
			}
			buyCarService.delete(buyCarItem); 		//将购物车该项删除
			orderItemService.save(item);	//保存该订单项
		}
		
		return buildSuccessResultInfo(order);
	}
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述:立即购买 </br>
	 * 开发人员：李启华</br>
	 * 创建时间：2015年11月17日</br>
	 * @param jsonValue
	 * @return
	 */
	@ApiOperation(value = "立即购买 ", notes = "病人id，地址id,商品id，购买数量必填，医生id选填")
	@RequestMapping(value = "/buyNow", method = RequestMethod.POST)
	@ApiResponses({
		@ApiResponse(code = ZhengheConstance.param_fault, message = "参数格式不正确", response = String.class),
		@ApiResponse(code = ZhengheConstance.userId_is_null, message = "用户id不存在", response = String.class),
		@ApiResponse(code = ZhengheConstance.address_not_exist, message = "地址id不存在", response = String.class),
		@ApiResponse(code = ZhengheConstance.productId_is_null, message = "商品不存在", response = String.class),
		@ApiResponse(code = ZhengheConstance.doctor_not_exist, message = "医生不存在", response = String.class),
	})
	public ResponseEntity<BaseResult> buyNow(@ApiParam(required = true,value = "病人id，地址id,商品id，购买数量必填，医生id选填")
		@RequestBody RequestBuyNow param) {
		String patientId = param.getPatientId();
		String addressId = param.getAddressId();
		String productId = param.getProductId();
		String count = param.getCount();
		String doctorId = param.getDoctorId();
		
		if(Tool.strBlank(patientId,addressId,productId,count) || Tool.isNotNumber(count)){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		if(patientService.get(patientId) == null){
			return buildFailedResultInfo(ZhengheConstance.userId_is_null);
		}
		ZhengheAddress addr = addressService.get(addressId);
		if(addr == null){
			return buildFailedResultInfo(ZhengheConstance.address_not_exist);
		}
		ZhengheProduct product = productService.get(productId);
		if(product == null){
			return buildFailedResultInfo(ZhengheConstance.productId_is_null);
		}
		if(StringUtils.isNotBlank(doctorId) && doctorService.get(doctorId)==null){
			return buildFailedResultInfo(ZhengheConstance.doctor_not_exist);
		}
		
		/*
		 * 计算总额
		 */
		Double price = product.getPrice();
		double totalAmount = price * Integer.parseInt(count);
		/*
		 * 拼接地址字符
		 */
		String province = provinceService.get(addr.getProvincialId()).getName();
		String city = cityService.get(addr.getCityId()) == null ? "" : cityService.get(addr.getCityId()).getCityName();
		String district = districtService.get(addr.getDistrictId()) == null ? "" : districtService.get(addr.getDistrictId()).getDistrictName();
		String orderAddress = province + city + district + addr.getAddress();
		/*
		 * 生成订单
		 */
		ZhengheOrder order = new ZhengheOrder();
		order.setParentOrderNo(makeOrderNo());			//订单号
		order.setTotalAmount(totalAmount+"");			//订单总额
		order.setPatientId(patientId);					//患者id
		order.setStatus("1");							//状态-> 1未支付
		order.setAddress(orderAddress);					//送货地址
		order.setCreateDate(new Date());				//下单时间
		order.setName(addr.getName());					//收货人
		order.setPhone(addr.getPhone());				//手机号码
		orderService.save(order);
		/*
		 * 生成订单项
		 */
		ZhengheOrderItem item = new ZhengheOrderItem();
		item.setProductId(product.getId());
		item.setOrderId(order.getId());
		item.setOrderItemNo(makeOrderItemNo());
		item.setProductName(product.getProductName());
		item.setOriginalPrice(product.getPrice()+"");
		item.setRealityPrice(product.getPrice()+"");
		item.setCount(count);
		item.setSumPrice(totalAmount+"");
		if(StringUtils.isNotBlank(doctorId)){
			item.setDoctorId(doctorId);
		}
		orderItemService.save(item);	//保存该订单项
		return buildSuccessResultInfo(order);
	}
	
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述:获取购物车列表 </br>
	 * 开发人员：李启华</br>
	 * 创建时间：2015年11月18日</br>
	 * @param jsonValue
	 * @return
	 */
	@ApiOperation(value = "获取购物车列表 ", notes = "参数必选，患者id")
	@RequestMapping(value = "/buyCarList", method = RequestMethod.POST)
	@ApiResponses({
		@ApiResponse(code = ZhengheConstance.param_fault, message = "参数格式不正确", response = String.class),
		@ApiResponse(code = ZhengheConstance.userId_is_null, message = "用户不存在", response = String.class),
		@ApiResponse(code = ZhengheConstance.empty_buy_cart, message = "购物车是空的", response = String.class),
	})
	public ResponseEntity<BaseResult> buyCarList(@ApiParam(required = true,value = "参数必选，患者id")
		@RequestBody RequestNumber param,HttpServletRequest request) {
		String patientId = param.getNum();
		if(StringUtils.isBlank(patientId)){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		if(patientService.get(patientId) == null){
			return buildFailedResultInfo(ZhengheConstance.userId_is_null);
		}
		ZhengheBuyCar c = new ZhengheBuyCar();
		c.setPatientId(patientId);
		List<ZhengheBuyCar> cList = buyCarService.findList(c);
		if(cList == null || cList.size()<1){
			return buildFailedResultInfo(ZhengheConstance.empty_buy_cart);
		}
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
		for(ZhengheBuyCar car : cList){
			// 找对应的商品
			ZhengheProduct product = productService.get(car.getProductId());
			car.setProductName(product.getProductName());
			car.setStandard(product.getStandard());
			// 找对应的轮播图
			ZhengheCarousel pic = new ZhengheCarousel();
			pic.setProductId(product.getId());
			List<ZhengheCarousel> picList = carouselService.findList(pic);
			car.setProductPic((picList == null || picList.size() < 1) ? "" : basePath+picList.get(0).getAvatar());
		}
		return buildSuccessResultInfo(cList);
	}
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述:将商品添加进去购物车 </br>
	 * 开发人员：李启华</br>
	 * 创建时间：2015年11月17日</br>
	 * @param jsonValue
	 * @return
	 */
	@ApiOperation(value = "将商品添加进去购物车 ", notes = "商品id，病人id，购买数量 必填，医生id选填")
	@RequestMapping(value = "/addToBuyCar", method = RequestMethod.POST)
	@ApiResponses({
		@ApiResponse(code = ZhengheConstance.param_fault, message = "参数格式不正确", response = String.class),
		@ApiResponse(code = ZhengheConstance.userId_is_null, message = "用户不存在", response = String.class),
		@ApiResponse(code = ZhengheConstance.productId_is_null, message = "商品不存在", response = String.class),
	})
	public ResponseEntity<BaseResult> addToBuyCar(@ApiParam(required = true,value = "商品id，病人id，购买数量 必填，医生id选填")
		@RequestBody RequestAddToCar param) {
		String productId = param.getProductId();
		String patientId = param.getPatientId();
		String count = param.getCount();
		String doctorId = param.getDoctorId();
		if(StringUtils.isBlank(productId) || StringUtils.isBlank(patientId) || StringUtils.isBlank(count) || !StringUtils.isNumeric(count)){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		if(patientService.get(patientId) == null){
			return buildFailedResultInfo(ZhengheConstance.userId_is_null);
		}
		ZhengheProduct p = productService.get(productId);
		if(p == null){
			return buildFailedResultInfo(ZhengheConstance.productId_is_null);
		}
		
		ZhengheBuyCar criteria = new ZhengheBuyCar();
		criteria.setProductId(productId);
		criteria.setPatientId(patientId);
		List<ZhengheBuyCar> existList = buyCarService.findList(criteria);
		if(existList != null && existList.size() > 0){
			System.out.println("-------------------- exist");
			ZhengheBuyCar exist = existList.get(0);
			exist.setCount(count);
			exist.setSumPrice(Integer.parseInt(count) * p.getPrice());
			buyCarService.save(exist);
		}else{
			System.out.println("--------------------- new ");
			ZhengheBuyCar buyCar = new ZhengheBuyCar();
			buyCar.setProductId(productId);
			buyCar.setPatientId(patientId);
			buyCar.setCount(count);
			buyCar.setRealityPrice(p.getPrice());
			buyCar.setSumPrice(Integer.parseInt(count) * p.getPrice());
			if(StringUtils.isNotBlank(doctorId) && (doctorService.get(doctorId) != null)){
				buyCar.setRemark(doctorId);
			}
			buyCarService.save(buyCar);
		}
		return buildSuccessResultInfo(ZhengheConstance.BASE_SUCCESS_CODE);
	}
	
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述:删除购物车某一项 </br>
	 * 开发人员：李启华</br>
	 * 创建时间：2015年12月03日</br>
	 * @param jsonValue
	 * @return
	 */
	@ApiOperation(value = "删除购物车一项或多项", notes = "购物车项id、病人id必填，多个购物车项用<英文>逗号拼接")
	@RequestMapping(value = "/delFromBuyCar", method = RequestMethod.POST)
	@ApiResponses({
		@ApiResponse(code = ZhengheConstance.param_fault, message = "参数格式不正确", response = String.class),
		@ApiResponse(code = ZhengheConstance.userId_is_null, message = "用户不存在", response = String.class),
		@ApiResponse(code = ZhengheConstance.productId_is_null, message = "商品不存在", response = String.class),
		@ApiResponse(code = ZhengheConstance.product_not_in_car, message = "购物车没有该商品", response = String.class),
	})
	public ResponseEntity<BaseResult> delFromBuyCar(@ApiParam(required = true,value = "购物车项id、病人id必填，多个购物车项用<英文>逗号拼接")
		@RequestBody RequestDelFromCar param) {
		String cartIds = param.getCartIds();
		String patientId = param.getPatientId();
		if(Tool.strBlank(cartIds,patientId)){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		if(patientService.get(patientId) == null){
			return buildFailedResultInfo(ZhengheConstance.userId_is_null);
		}
		String[] cIds = cartIds.split(",");
		for(String cId : cIds){
			ZhengheBuyCar buyCarItem = buyCarService.get(cId);
			if(buyCarItem == null){
				return buildFailedResultInfo(ZhengheConstance.product_not_in_car);
			}
			buyCarService.delete(buyCarItem);
		}
		return buildSuccessResultInfo(ZhengheConstance.BASE_SUCCESS_CODE);
	}


	/**
	 *
	 * 方法名: </br>
	 * 详述:生成预支付订单 </br>
	 * 开发人员：LeVis</br>
	 * 创建时间：2015年12月03日</br>
	 * @return
	 */
	@ApiOperation(value = "微信支付", notes = "生成预支付订单")
	@RequestMapping(value = "/weixin", method = RequestMethod.POST)
	@ApiResponses({
			@ApiResponse(code = ZhengheConstance.param_fault, message = "参数格式不正确", response = String.class),
			@ApiResponse(code = ZhengheConstance.orderId_not_exist, message = "订单id不存在", response = String.class),
			@ApiResponse(code = ZhengheConstance.order_status_fault, message = "订单已支付", response = String.class),
			@ApiResponse(code = ZhengheConstance.BASE_SUCCESS_CODE, message = "操作成功", response = String.class)
	})
	public ResponseEntity<BaseResult> weixin(@ApiParam(required = true,value = "订单id")
												 @RequestBody RequestId requestId) {

		String id = requestId.getId();
		if(StringUtils.isBlank(id)){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		ZhengheOrder order = orderService.get(id);
		if(order==null){
			return buildFailedResultInfo(ZhengheConstance.orderId_not_exist);
		}
		if(!"1".equals(order.getStatus())){
			//已付款订单
			return buildFailedResultInfo(ZhengheConstance.order_status_fault);
		}
		if(com.fullteem.common.utils.StringUtils.isEmpty(order.getTotalAmount())){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		BigDecimal totalAmount = new BigDecimal(order.getTotalAmount());
		if (totalAmount.multiply(new BigDecimal(100)).intValue() <1){
			//价格有误
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		//返回预支付信息
		Object result = Pays.payByWeiXin(order.getParentOrderNo(),totalAmount);
		return buildSuccessResultInfo(result);
	}

	/**
	 *
	 * 方法名: </br>
	 * 详述:生成预支付订单 </br>
	 * 开发人员：LeVis</br>
	 * 创建时间：2015年12月03日</br>
	 * @return
	 */
	@ApiOperation(value = "支付宝支付", notes = "生成预支付订单")
	@RequestMapping(value = "/aliPay", method = RequestMethod.POST)
	@ApiResponses({
			@ApiResponse(code = ZhengheConstance.param_fault, message = "参数格式不正确", response = String.class),
			@ApiResponse(code = ZhengheConstance.orderId_not_exist, message = "订单id不存在", response = String.class),
			@ApiResponse(code = ZhengheConstance.order_status_fault, message = "订单已支付", response = String.class),
			@ApiResponse(code = ZhengheConstance.BASE_SUCCESS_CODE, message = "操作成功", response = String.class)
	})
	public ResponseEntity<BaseResult> aliPay(@ApiParam(required = true,value = "订单id")
											 @RequestBody RequestId requestId) {

		String id = requestId.getId();
		if(StringUtils.isBlank(id)){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		ZhengheOrder order = orderService.get(id);
		if(order==null){
			return buildFailedResultInfo(ZhengheConstance.orderId_not_exist);
		}
		if(!"1".equals(order.getStatus())){
			//已付款订单
			return buildFailedResultInfo(ZhengheConstance.order_status_fault);
		}
		if(com.fullteem.common.utils.StringUtils.isEmpty(order.getTotalAmount())){
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		BigDecimal totalAmount = new BigDecimal(order.getTotalAmount());
		if (totalAmount.multiply(new BigDecimal(100)).intValue() <1){
			//价格有误
			return buildFailedResultInfo(ZhengheConstance.param_fault);
		}
		//返回预支付信息
		Object result = Pays.appAliPay(order.getParentOrderNo(),totalAmount);
		return buildSuccessResultInfo(result);
	}



	/*
	 * 生成订单表中不重复的订单号
	 */
	private String makeOrderNo(){
		String orderNo = System.currentTimeMillis()+"";
		ZhengheOrder order = new ZhengheOrder();
		order.setParentOrderNo(orderNo);
		if(orderService.get(order) != null){
			orderNo = makeOrderNo();
		}
		return orderNo;
	}
	
	/*
	 * 生成订单'项'表中不重复的订单号
	 */
	private String makeOrderItemNo(){
		String orderItemNo = System.currentTimeMillis()+"";
		ZhengheOrderItem item = new ZhengheOrderItem();
		item.setOrderItemNo(orderItemNo);
		if(orderItemService.get(item) != null){
			orderItemNo = makeOrderItemNo();
		}
		return orderItemNo;
	}
	
		
}