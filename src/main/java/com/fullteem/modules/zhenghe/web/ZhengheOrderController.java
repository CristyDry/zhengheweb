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
import com.fullteem.modules.zhenghe.entity.ZhengheOrder;
import com.fullteem.modules.zhenghe.service.ZhengheOrderService;

/**
 * 订单表Controller
 * @author 李启华
 * @version 2016-01-05
 */
@Controller
@RequestMapping(value = "${adminPath}/zhenghe/zhengheOrder")
public class ZhengheOrderController extends BaseController {

	@Autowired
	private ZhengheOrderService zhengheOrderService;
	
	@ModelAttribute
	public ZhengheOrder get(@RequestParam(required=false) String id) {
		ZhengheOrder entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = zhengheOrderService.get(id);
		}
		if (entity == null){
			entity = new ZhengheOrder();
		}
		return entity;
	}
	
	@RequiresPermissions("zhenghe:zhengheOrder:view")
	@RequestMapping(value = {"list", ""})
	public String list(ZhengheOrder zhengheOrder, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ZhengheOrder> page = zhengheOrderService.findPage(new Page<ZhengheOrder>(request, response), zhengheOrder); 
		model.addAttribute("page", page);
		return "modules/zhenghe/zhengheOrderList";
	}

	@RequiresPermissions("zhenghe:zhengheOrder:view")
	@RequestMapping(value = "form")
	public String form(ZhengheOrder zhengheOrder, Model model) {
		model.addAttribute("zhengheOrder", zhengheOrder);
		model.addAttribute("orderStatus","3");
		return "modules/zhenghe/zhengheOrderForm";
	}

	@RequiresPermissions("zhenghe:zhengheOrder:edit")
	@RequestMapping(value = "save")
	public String save(ZhengheOrder zhengheOrder, Model model, RedirectAttributes redirectAttributes) {
		if(!zhengheOrder.getStatus().equals("2,3")){
			addMessage(redirectAttributes, "订单表状态不允许修改");
			return "redirect:"+Global.getAdminPath()+"/zhenghe/zhengheOrder/?repage";
		}
		zhengheOrder.setStatus("3");
		zhengheOrderService.save(zhengheOrder);
		addMessage(redirectAttributes, "保存订单表成功");
		return "redirect:"+Global.getAdminPath()+"/zhenghe/zhengheOrder/?repage";
	}
	
	@RequiresPermissions("zhenghe:zhengheOrder:edit")
	@RequestMapping(value = "delete")
	public String delete(ZhengheOrder zhengheOrder, RedirectAttributes redirectAttributes) {
		zhengheOrderService.delete(zhengheOrder);
		addMessage(redirectAttributes, "删除订单表成功");
		return "redirect:"+Global.getAdminPath()+"/zhenghe/zhengheOrder/?repage";
	}

}