/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.modules.zhenghe.web;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fullteem.modules.zhenghe.api.utils.ProductPicUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fullteem.common.config.Global;
import com.fullteem.common.persistence.Page;
import com.fullteem.common.web.BaseController;
import com.fullteem.common.utils.StringUtils;
import com.fullteem.modules.zhenghe.api.utils.ZhengheConstance;
import com.fullteem.modules.zhenghe.api.utils.ZhengheUpload;
import com.fullteem.modules.zhenghe.entity.ZhengheCommonImage;
import com.fullteem.modules.zhenghe.entity.ZhengheProduct;
import com.fullteem.modules.zhenghe.entity.ZhengheProductClassify;
import com.fullteem.modules.zhenghe.service.ZhengheCommonImageService;
import com.fullteem.modules.zhenghe.service.ZhengheProductClassifyService;
import com.fullteem.modules.zhenghe.service.ZhengheProductService;

/**
 * 药品Controller
 * @author 李启华
 * @version 2015-11-11
 */
@Controller
@RequestMapping(value = "${adminPath}/zhenghe/zhengheProduct")
public class ZhengheProductController extends BaseController {

	@Autowired
	private ZhengheProductService zhengheProductService;
	@Resource
	private ZhengheProductClassifyService classifyService;
	@Resource 
	ZhengheCommonImageService imageService;
	@Autowired
	private HttpServletRequest request;
	
	@ModelAttribute
	public ZhengheProduct get(@RequestParam(required=false) String id) {
		ZhengheProduct entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = zhengheProductService.get(id);
		}
		if (entity == null){
			entity = new ZhengheProduct();
		}
		return entity;
	}
	
	@RequiresPermissions("zhenghe:zhengheProduct:view")
	@RequestMapping(value = {"list", ""})
	public String list(ZhengheProduct zhengheProduct, HttpServletRequest request, HttpServletResponse response, Model model) {
		String aa = Global.getUserfilesBaseDir();
		com.fullteem.common.utils.Log.println("-------------------------------- path : "+aa);
		Page<ZhengheProduct> page = zhengheProductService.findPage(new Page<ZhengheProduct>(request, response), zhengheProduct);
		ZhengheCommonImage image = imageService.findByRelevanceId(zhengheProduct.getId());
		/*if(image!=null){
			for(ZhengheProduct product:page.getList()){
				product.setProductPic(image.getImage1());
			}
		}*/
		List<ZhengheProductClassify> cList = classifyService.findList(null);
		Map<String,String> cMap = new HashMap<String,String>();
		for(ZhengheProductClassify c : cList){
			cMap.put(c.getId(), c.getClassifyName());
		}
		model.addAttribute("cList",cMap);
		model.addAttribute("page", page);
		return "modules/zhenghe/zhengheProductList";
	}

	@RequiresPermissions("zhenghe:zhengheProduct:view")
	@RequestMapping(value = "form")
	public String form(ZhengheProduct zhengheProduct, Model model) {
		List<ZhengheProductClassify> cList = classifyService.findList(null);
		Map<String,String> cMap = new HashMap<String,String>();
		for(ZhengheProductClassify c : cList){
			cMap.put(c.getId(), c.getClassifyName());
		}
		List<String> images = ProductPicUtils.getImage(zhengheProduct.getId());
		zhengheProduct.setImages(images);
		
		model.addAttribute("cList",cMap);
		model.addAttribute("zhengheProduct", zhengheProduct);
		return "modules/zhenghe/zhengheProductForm";
	}

	@RequiresPermissions("zhenghe:zhengheProduct:edit")
	@RequestMapping(value = "save")
	public String save(ZhengheProduct zhengheProduct, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, zhengheProduct)){
			return form(zhengheProduct, model);
		}
		imageService.delByRelevanceId(zhengheProduct.getId());
		zhengheProduct.setUpdateDate(new Date());
		zhengheProductService.save(zhengheProduct);
		if(StringUtils.isBlank(zhengheProduct.getSalesNum())){
			zhengheProduct.setSalesNum("0");
		}
		
		String pic = zhengheProduct.getProductPic();
		if(StringUtils.isNotBlank(pic)){
			saveImage("7",zhengheProduct.getId(),pic);
		}
		
		addMessage(redirectAttributes, "保存药品成功");
		return "redirect:"+Global.getAdminPath()+"/zhenghe/zhengheProduct/?repage";
	}
	
	
	/**
	 * 
	 * 方法名: </br>
	 * 详述: </br>
	 * 开发人员：chenx</br>
	 * 创建时间：2016年1月14日</br>
	 * @param type
	 * @param id
	 * @param result
	 * @return
	 */
	private int saveImage(String type,String id,String result){
		
		ZhengheCommonImage image = new ZhengheCommonImage();
		image.setRelevanceId(id);
		image.setType(type);
		
		String [] paths = result.split("\\|");
		
		Class<? extends ZhengheCommonImage> clazz = image.getClass();
		try {
			for(int i=1;i<paths.length;i++){
				String str = "setImage"+i;
				Method method = clazz.getDeclaredMethod(str, String.class);
				method.invoke(image, paths[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		imageService.save(image);
		
		return 0;
	}
	
	@RequiresPermissions("zhenghe:zhengheProduct:edit")
	@RequestMapping(value = "delete")
	public String delete(ZhengheProduct zhengheProduct, RedirectAttributes redirectAttributes) {
		zhengheProductService.delete(zhengheProduct);
		addMessage(redirectAttributes, "删除药品成功");
		return "redirect:"+Global.getAdminPath()+"/zhenghe/zhengheProduct/?repage";
	}

}