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
import com.fullteem.modules.zhenghe.entity.ZhengheProfession;
import com.fullteem.modules.zhenghe.service.ZhengheProfessionService;

/**
 * 医生职称Controller
 * @author 李启华
 * @version 2015-11-11
 */
@Controller
@RequestMapping(value = "${adminPath}/zhenghe/zhengheProfession")
public class ZhengheProfessionController extends BaseController {

	@Autowired
	private ZhengheProfessionService zhengheProfessionService;
	
	@ModelAttribute
	public ZhengheProfession get(@RequestParam(required=false) String id) {
		ZhengheProfession entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = zhengheProfessionService.get(id);
		}
		if (entity == null){
			entity = new ZhengheProfession();
		}
		return entity;
	}
	
	@RequiresPermissions("zhenghe:zhengheProfession:view")
	@RequestMapping(value = {"list", ""})
	public String list(ZhengheProfession zhengheProfession, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ZhengheProfession> page = zhengheProfessionService.findPage(new Page<ZhengheProfession>(request, response), zhengheProfession); 
		model.addAttribute("page", page);
		return "modules/zhenghe/zhengheProfessionList";
	}

	@RequiresPermissions("zhenghe:zhengheProfession:view")
	@RequestMapping(value = "form")
	public String form(ZhengheProfession zhengheProfession, Model model) {
		model.addAttribute("zhengheProfession", zhengheProfession);
		return "modules/zhenghe/zhengheProfessionForm";
	}

	@RequiresPermissions("zhenghe:zhengheProfession:edit")
	@RequestMapping(value = "save")
	public String save(ZhengheProfession zhengheProfession, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, zhengheProfession)){
			return form(zhengheProfession, model);
		}
		zhengheProfessionService.save(zhengheProfession);
		addMessage(redirectAttributes, "保存医生职称成功");
		return "redirect:"+Global.getAdminPath()+"/zhenghe/zhengheProfession/?repage";
	}
	
	@RequiresPermissions("zhenghe:zhengheProfession:edit")
	@RequestMapping(value = "delete")
	public String delete(ZhengheProfession zhengheProfession, RedirectAttributes redirectAttributes) {
		zhengheProfessionService.delete(zhengheProfession);
		addMessage(redirectAttributes, "删除医生职称成功");
		return "redirect:"+Global.getAdminPath()+"/zhenghe/zhengheProfession/?repage";
	}

}