/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.modules.zhenghe.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fullteem.common.config.Global;
import com.fullteem.common.persistence.Page;
import com.fullteem.common.web.BaseController;
import com.fullteem.common.utils.StringUtils;
import com.fullteem.modules.zhenghe.entity.ZhengheArticle;
import com.fullteem.modules.zhenghe.entity.ZhengheCarousel;
import com.fullteem.modules.zhenghe.entity.ZhengheProduct;
import com.fullteem.modules.zhenghe.service.ZhengheArticleService;
import com.fullteem.modules.zhenghe.service.ZhengheCarouselService;
import com.fullteem.modules.zhenghe.service.ZhengheProductService;

/**
 * 轮播图Controller
 * @author 李启华
 * @version 2015-11-12
 */
@Controller
@RequestMapping(value = "${adminPath}/zhenghe/zhengheCarousel")
public class ZhengheCarouselController extends BaseController {

	@Autowired
	private ZhengheCarouselService zhengheCarouselService;
	@Autowired
	private ZhengheArticleService articleService;
	@Resource
	private ZhengheProductService productService;
	
	@ModelAttribute
	public ZhengheCarousel get(@RequestParam(required=false) String id) {
		ZhengheCarousel entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = zhengheCarouselService.get(id);
		}
		if (entity == null){
			entity = new ZhengheCarousel();
		}
		return entity;
	}
	
	@RequiresPermissions("zhenghe:zhengheCarousel:view")
	@RequestMapping(value = {"list", ""})
	public String list(ZhengheCarousel zhengheCarousel, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ZhengheCarousel> page = zhengheCarouselService.findPage(new Page<ZhengheCarousel>(request, response), zhengheCarousel); 
		List<ZhengheCarousel> cList = page.getList();
		for(ZhengheCarousel c : cList){
			if(StringUtils.isNotBlank(c.getArticleId())){
				c.setArticleName((articleService.get(c.getArticleId())==null) ? "" : articleService.get(c.getArticleId()).getTitle());
			}
			if(StringUtils.isNotBlank(c.getProductId())){
				c.setProductName((productService.get(c.getProductId())==null) ? "" : productService.get(c.getProductId()).getProductName());
			}
		}
		model.addAttribute("page", page);
		return "modules/zhenghe/zhengheCarouselList";
	}

	@RequiresPermissions("zhenghe:zhengheCarousel:view")
	@RequestMapping(value = "form")
	public String form(ZhengheCarousel zhengheCarousel, Model model) {
		List<ZhengheArticle> aList = articleService.findList(null);
		List<ZhengheProduct> pList = productService.findList(null);
		Map<String,String> aMap = new HashMap<String,String>();
		Map<String,String> pMap = new HashMap<String,String>();
		for(ZhengheArticle a : aList){
			aMap.put(a.getId(), a.getTitle());
		}
		for(ZhengheProduct p : pList){
			pMap.put(p.getId(), p.getProductName());
		}
		model.addAttribute("articleMap",aMap);
		model.addAttribute("productMap",pMap);
		model.addAttribute("zhengheCarousel", zhengheCarousel);
		return "modules/zhenghe/zhengheCarouselForm";
	}

	@RequiresPermissions("zhenghe:zhengheCarousel:edit")
	@RequestMapping(value = "save")
	public String save(ZhengheCarousel zhengheCarousel, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, zhengheCarousel)){
			return form(zhengheCarousel, model);
		}
		com.fullteem.common.utils.Log.println("----------------------------------- avatar : "+zhengheCarousel.getAvatar());
		if(zhengheCarousel.getAvatar().contains("|")){
			com.fullteem.common.utils.Log.println(" ------------------- has | : "+zhengheCarousel.getAvatar().lastIndexOf("|"));
			zhengheCarousel.setAvatar(zhengheCarousel.getAvatar().substring(zhengheCarousel.getAvatar().lastIndexOf("|")+1));
		}
		zhengheCarousel.setUpdateDate(new Date());
		zhengheCarouselService.save(zhengheCarousel);
		addMessage(redirectAttributes, "保存轮播图成功");
		return "redirect:"+Global.getAdminPath()+"/zhenghe/zhengheCarousel/?repage";
	}
	
	@RequiresPermissions("zhenghe:zhengheCarousel:edit")
	@RequestMapping(value = "delete")
	public String delete(ZhengheCarousel zhengheCarousel, RedirectAttributes redirectAttributes) {
		zhengheCarouselService.delete(zhengheCarousel);
		addMessage(redirectAttributes, "删除轮播图成功");
		return "redirect:"+Global.getAdminPath()+"/zhenghe/zhengheCarousel/?repage";
	}

}