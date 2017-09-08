/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.modules.zhenghe.web;

import java.text.ParseException;
import java.util.List;

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
import com.fullteem.modules.zhenghe.entity.BigDataZhenghe;
import com.fullteem.modules.zhenghe.entity.ZhengheOrderItem;
import com.fullteem.modules.zhenghe.service.ZhengheOrderItemService;

/**
 * 订单项Controller
 * @author 李启华
 * @version 2015-11-17
 */
@Controller
@RequestMapping(value = "${adminPath}/zhenghe/zhengheOrderItem")
public class ZhengheOrderItemController extends BaseController {

	@Autowired
	private ZhengheOrderItemService zhengheOrderItemService;
	
	
	@RequiresPermissions("zhenghe:zhengheOrderItem:view")
	@RequestMapping(value = "getSum")
	public String getSum(Model model) throws ParseException {
		List<BigDataZhenghe> list = zhengheOrderItemService.getSum();
		model.addAttribute("bigDataZhenghe2",list);
		return "modules/zhenghe/bigDataZhenghe2";
	}
	
	@RequiresPermissions("zhenghe:zhengheOrderItem:view")
	@RequestMapping(value = "getHot")
	public String getHot(Model model) {
		model.addAttribute("bigDataZhenghe", zhengheOrderItemService.getHot());
		return "modules/zhenghe/bigDataZhenghe";
	}
	
	@RequiresPermissions("zhenghe:zhengheOrderItem:view")
	@RequestMapping(value = "getSales")
	public String getSales(Model model) {
		model.addAttribute("bigDataZhenghe", zhengheOrderItemService.getSales());
		return "modules/zhenghe/bigDataZhenghe";
	}
	
	@ModelAttribute
	public ZhengheOrderItem get(@RequestParam(required=false) String id) {
		ZhengheOrderItem entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = zhengheOrderItemService.get(id);
		}
		if (entity == null){
			entity = new ZhengheOrderItem();
		}
		return entity;
	}
	
	@RequiresPermissions("zhenghe:zhengheOrderItem:view")
	@RequestMapping(value = {"list", ""})
	public String list(ZhengheOrderItem zhengheOrderItem, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ZhengheOrderItem> page = zhengheOrderItemService.findPage(new Page<ZhengheOrderItem>(request, response), zhengheOrderItem); 
		model.addAttribute("page", page);
		return "modules/zhenghe/zhengheOrderItemList";
	}

	@RequiresPermissions("zhenghe:zhengheOrderItem:view")
	@RequestMapping(value = "form")
	public String form(ZhengheOrderItem zhengheOrderItem, Model model) {
		model.addAttribute("zhengheOrderItem", zhengheOrderItem);
		return "modules/zhenghe/zhengheOrderItemForm";
	}

	@RequiresPermissions("zhenghe:zhengheOrderItem:edit")
	@RequestMapping(value = "save")
	public String save(ZhengheOrderItem zhengheOrderItem, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, zhengheOrderItem)){
			return form(zhengheOrderItem, model);
		}
		zhengheOrderItemService.save(zhengheOrderItem);
		addMessage(redirectAttributes, "保存订单项成功");
		return "redirect:"+Global.getAdminPath()+"/zhenghe/zhengheOrderItem/?repage";
	}
	
	@RequiresPermissions("zhenghe:zhengheOrderItem:edit")
	@RequestMapping(value = "delete")
	public String delete(ZhengheOrderItem zhengheOrderItem, RedirectAttributes redirectAttributes) {
		zhengheOrderItemService.delete(zhengheOrderItem);
		addMessage(redirectAttributes, "删除订单项成功");
		return "redirect:"+Global.getAdminPath()+"/zhenghe/zhengheOrderItem/?repage";
	}

}