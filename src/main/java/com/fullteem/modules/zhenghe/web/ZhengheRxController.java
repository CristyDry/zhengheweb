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
import com.fullteem.modules.zhenghe.entity.ZhengheRx;
import com.fullteem.modules.zhenghe.service.ZhengheRxService;

/**
 * 处方信息Controller
 * @author LeVis
 * @version 2017-09-28
 */
@Controller
@RequestMapping(value = "${adminPath}/zhenghe/zhengheRx")
public class ZhengheRxController extends BaseController {

	@Autowired
	private ZhengheRxService zhengheRxService;
	
	@ModelAttribute
	public ZhengheRx get(@RequestParam(required=false) String id) {
		ZhengheRx entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = zhengheRxService.get(id);
		}
		if (entity == null){
			entity = new ZhengheRx();
		}
		return entity;
	}
	
	@RequiresPermissions("zhenghe:zhengheRx:view")
	@RequestMapping(value = {"list", ""})
	public String list(ZhengheRx zhengheRx, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ZhengheRx> page = zhengheRxService.findPage(new Page<ZhengheRx>(request, response), zhengheRx); 
		model.addAttribute("page", page);
		return "modules/zhenghe/zhengheRxList";
	}

	@RequiresPermissions("zhenghe:zhengheRx:view")
	@RequestMapping(value = "form")
	public String form(ZhengheRx zhengheRx, Model model) {
		model.addAttribute("zhengheRx", zhengheRx);
		return "modules/zhenghe/zhengheRxForm";
	}

	@RequiresPermissions("zhenghe:zhengheRx:view")
	@RequestMapping(value = "print")
	public String print(ZhengheRx zhengheRx, Model model) {
		model.addAttribute("zhengheRx", zhengheRx);
		return "modules/zhenghe/zhengheRxPrint";
	}

	@RequiresPermissions("zhenghe:zhengheRx:edit")
	@RequestMapping(value = "save")
	public String save(ZhengheRx zhengheRx, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, zhengheRx)){
			return form(zhengheRx, model);
		}
		zhengheRxService.save(zhengheRx);
		addMessage(redirectAttributes, "保存处方成功");
		return "redirect:"+Global.getAdminPath()+"/zhenghe/zhengheRx/?repage";
	}
	
	@RequiresPermissions("zhenghe:zhengheRx:edit")
	@RequestMapping(value = "delete")
	public String delete(ZhengheRx zhengheRx, RedirectAttributes redirectAttributes) {
		zhengheRxService.delete(zhengheRx);
		addMessage(redirectAttributes, "删除处方成功");
		return "redirect:"+Global.getAdminPath()+"/zhenghe/zhengheRx/?repage";
	}

}