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
import com.fullteem.modules.zhenghe.entity.ZhengheProductClassify;
import com.fullteem.modules.zhenghe.service.ZhengheProductClassifyService;

/**
 * 药品分类Controller
 * @author 李启华
 * @version 2015-11-11
 */
@Controller
@RequestMapping(value = "${adminPath}/zhenghe/zhengheProductClassify")
public class ZhengheProductClassifyController extends BaseController {

	@Autowired
	private ZhengheProductClassifyService zhengheProductClassifyService;
	
	@ModelAttribute
	public ZhengheProductClassify get(@RequestParam(required=false) String id) {
		ZhengheProductClassify entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = zhengheProductClassifyService.get(id);
		}
		if (entity == null){
			entity = new ZhengheProductClassify();
		}
		return entity;
	}
	
	@RequiresPermissions("zhenghe:zhengheProductClassify:view")
	@RequestMapping(value = {"list", ""})
	public String list(ZhengheProductClassify zhengheProductClassify, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ZhengheProductClassify> page = zhengheProductClassifyService.findPage(new Page<ZhengheProductClassify>(request, response), zhengheProductClassify); 
		model.addAttribute("page", page);
		return "modules/zhenghe/zhengheProductClassifyList";
	}

	@RequiresPermissions("zhenghe:zhengheProductClassify:view")
	@RequestMapping(value = "form")
	public String form(ZhengheProductClassify zhengheProductClassify, Model model) {
		model.addAttribute("zhengheProductClassify", zhengheProductClassify);
		return "modules/zhenghe/zhengheProductClassifyForm";
	}

	@RequiresPermissions("zhenghe:zhengheProductClassify:edit")
	@RequestMapping(value = "save")
	public String save(ZhengheProductClassify zhengheProductClassify, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, zhengheProductClassify)){
			return form(zhengheProductClassify, model);
		}
		if(zhengheProductClassify.getAvatar().contains("|")){
			zhengheProductClassify.setAvatar(zhengheProductClassify.getAvatar().substring(zhengheProductClassify.getAvatar().lastIndexOf("|")+1));
		}
		zhengheProductClassifyService.save(zhengheProductClassify);
		addMessage(redirectAttributes, "保存药品分类成功");
		return "redirect:"+Global.getAdminPath()+"/zhenghe/zhengheProductClassify/?repage";
	}
	
	@RequiresPermissions("zhenghe:zhengheProductClassify:edit")
	@RequestMapping(value = "delete")
	public String delete(ZhengheProductClassify zhengheProductClassify, RedirectAttributes redirectAttributes) {
		zhengheProductClassifyService.delete(zhengheProductClassify);
		addMessage(redirectAttributes, "删除药品分类成功");
		return "redirect:"+Global.getAdminPath()+"/zhenghe/zhengheProductClassify/?repage";
	}

}