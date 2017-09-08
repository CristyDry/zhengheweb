/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.modules.zhenghe.web;

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
import com.fullteem.modules.zhenghe.entity.ZhengheDistrict;
import com.fullteem.modules.zhenghe.service.ZhengheDistrictService;

/**
 * 地区Controller
 * @author 李启华
 * @version 2015-11-11
 */
@Controller
@RequestMapping(value = "${adminPath}/zhenghe/zhengheDistrict")
public class ZhengheDistrictController extends BaseController {

	@Autowired
	private ZhengheDistrictService zhengheDistrictService;
	
	@ModelAttribute
	public ZhengheDistrict get(@RequestParam(required=false) String id) {
		ZhengheDistrict entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = zhengheDistrictService.get(id);
		}
		if (entity == null){
			entity = new ZhengheDistrict();
		}
		return entity;
	}
	
	@RequiresPermissions("zhenghe:zhengheDistrict:view")
	@RequestMapping(value = {"list", ""})
	public String list(ZhengheDistrict zhengheDistrict, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ZhengheDistrict> page = zhengheDistrictService.findPage(new Page<ZhengheDistrict>(request, response), zhengheDistrict); 
		model.addAttribute("page", page);
		return "modules/zhenghe/zhengheDistrictList";
	}

	@RequiresPermissions("zhenghe:zhengheDistrict:view")
	@RequestMapping(value = "form")
	public String form(ZhengheDistrict zhengheDistrict, Model model) {
		model.addAttribute("zhengheDistrict", zhengheDistrict);
		return "modules/zhenghe/zhengheDistrictForm";
	}

	@RequiresPermissions("zhenghe:zhengheDistrict:edit")
	@RequestMapping(value = "save")
	public String save(ZhengheDistrict zhengheDistrict, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, zhengheDistrict)){
			return form(zhengheDistrict, model);
		}
		zhengheDistrictService.save(zhengheDistrict);
		addMessage(redirectAttributes, "保存地区成功");
		return "redirect:"+Global.getAdminPath()+"/zhenghe/zhengheDistrict/?repage";
	}
	
	@RequiresPermissions("zhenghe:zhengheDistrict:edit")
	@RequestMapping(value = "delete")
	public String delete(ZhengheDistrict zhengheDistrict, RedirectAttributes redirectAttributes) {
		zhengheDistrictService.delete(zhengheDistrict);
		addMessage(redirectAttributes, "删除地区成功");
		return "redirect:"+Global.getAdminPath()+"/zhenghe/zhengheDistrict/?repage";
	}

}