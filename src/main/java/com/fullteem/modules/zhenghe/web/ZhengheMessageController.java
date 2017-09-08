/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.modules.zhenghe.web;

import java.util.Date;

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
import com.fullteem.modules.zhenghe.entity.ZhengheMessage;
import com.fullteem.modules.zhenghe.service.ZhengheMessageService;

/**
 * 系统消息Controller
 * @author asd
 * @version 2016-01-13
 */
@Controller
@RequestMapping(value = "${adminPath}/zhenghe/zhengheMessage")
public class ZhengheMessageController extends BaseController {

	@Autowired
	private ZhengheMessageService zhengheMessageService;
	
	@ModelAttribute
	public ZhengheMessage get(@RequestParam(required=false) String id) {
		ZhengheMessage entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = zhengheMessageService.get(id);
		}
		if (entity == null){
			entity = new ZhengheMessage();
		}
		return entity;
	}
	
	@RequiresPermissions("zhenghe:zhengheMessage:view")
	@RequestMapping(value = {"list", ""})
	public String list(ZhengheMessage zhengheMessage, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ZhengheMessage> page = zhengheMessageService.findPage(new Page<ZhengheMessage>(request, response), zhengheMessage); 
		model.addAttribute("page", page);
		return "modules/zhenghe/zhengheMessageList";
	}

	@RequiresPermissions("zhenghe:zhengheMessage:view")
	@RequestMapping(value = "form")
	public String form(ZhengheMessage zhengheMessage, Model model) {
		model.addAttribute("zhengheMessage", zhengheMessage);
		return "modules/zhenghe/zhengheMessageForm";
	}

	@RequiresPermissions("zhenghe:zhengheMessage:edit")
	@RequestMapping(value = "save")
	public String save(ZhengheMessage zhengheMessage, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, zhengheMessage)){
			return form(zhengheMessage, model);
		}
		zhengheMessage.setStatus("0");
		zhengheMessage.setCreateDate(new Date());
		zhengheMessageService.save(zhengheMessage);
		addMessage(redirectAttributes, "保存系统消息成功");
		return "redirect:"+Global.getAdminPath()+"/zhenghe/zhengheMessage/?repage";
	}
	
	@RequiresPermissions("zhenghe:zhengheMessage:edit")
	@RequestMapping(value = "delete")
	public String delete(ZhengheMessage zhengheMessage, RedirectAttributes redirectAttributes) {
		zhengheMessageService.delete(zhengheMessage);
		addMessage(redirectAttributes, "删除系统消息成功");
		return "redirect:"+Global.getAdminPath()+"/zhenghe/zhengheMessage/?repage";
	}

}