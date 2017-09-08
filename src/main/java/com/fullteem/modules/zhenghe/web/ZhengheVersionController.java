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
import com.fullteem.modules.zhenghe.entity.ZhengheVersion;
import com.fullteem.modules.zhenghe.service.ZhengheVersionService;

/**
 * 版本管理Controller
 * @author 陈协
 * @version 2016-01-04
 */
@Controller
@RequestMapping(value = "${adminPath}/zhenghe/zhengheVersion")
public class ZhengheVersionController extends BaseController {

	@Autowired
	private ZhengheVersionService zhengheVersionService;
	
	@ModelAttribute
	public ZhengheVersion get(@RequestParam(required=false) String id) {
		ZhengheVersion entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = zhengheVersionService.get(id);
		}
		if (entity == null){
			entity = new ZhengheVersion();
		}
		return entity;
	}
	
	@RequiresPermissions("zhenghe:zhengheVersion:view")
	@RequestMapping(value = {"list", ""})
	public String list(ZhengheVersion zhengheVersion, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ZhengheVersion> page = zhengheVersionService.findPage(new Page<ZhengheVersion>(request, response), zhengheVersion); 
		model.addAttribute("page", page);
		return "modules/zhenghe/zhengheVersionList";
	}

	@RequiresPermissions("zhenghe:zhengheVersion:view")
	@RequestMapping(value = "form")
	public String form(ZhengheVersion zhengheVersion, Model model) {
		model.addAttribute("zhengheVersion", zhengheVersion);
		return "modules/zhenghe/zhengheVersionForm";
	}

	@RequiresPermissions("zhenghe:zhengheVersion:edit")
	@RequestMapping(value = "save")
	public String save(ZhengheVersion zhengheVersion, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, zhengheVersion)){
			return form(zhengheVersion, model);
		}
		zhengheVersionService.save(zhengheVersion);
		addMessage(redirectAttributes, "保存版本管理成功");
		return "redirect:"+Global.getAdminPath()+"/zhenghe/zhengheVersion/?repage";
	}
	
	@RequiresPermissions("zhenghe:zhengheVersion:edit")
	@RequestMapping(value = "delete")
	public String delete(ZhengheVersion zhengheVersion, RedirectAttributes redirectAttributes) {
		zhengheVersionService.delete(zhengheVersion);
		addMessage(redirectAttributes, "删除版本管理成功");
		return "redirect:"+Global.getAdminPath()+"/zhenghe/zhengheVersion/?repage";
	}

}