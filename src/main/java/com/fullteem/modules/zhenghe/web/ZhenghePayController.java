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
import com.fullteem.modules.zhenghe.entity.ZhenghePatient;
import com.fullteem.modules.zhenghe.entity.ZhenghePay;
import com.fullteem.modules.zhenghe.service.ZhenghePatientService;
import com.fullteem.modules.zhenghe.service.ZhenghePayService;

/**
 * 支付日志Controller
 * @author 李启华
 * @version 2015-11-17
 */
@Controller
@RequestMapping(value = "${adminPath}/zhenghe/zhenghePay")
public class ZhenghePayController extends BaseController {

	@Autowired
	private ZhenghePayService zhenghePayService;
	@Autowired
	private ZhenghePatientService zhenghePatientService;
	
	@ModelAttribute
	public ZhenghePay get(@RequestParam(required=false) String id) {
		ZhenghePay entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = zhenghePayService.get(id);
		}
		if (entity == null){
			entity = new ZhenghePay();
		}
		return entity;
	}
	
	@RequiresPermissions("zhenghe:zhenghePay:view")
	@RequestMapping(value = {"list", ""})
	public String list(ZhenghePay zhenghePay, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ZhenghePay> page = zhenghePayService.findPage(new Page<ZhenghePay>(request, response), zhenghePay); 
		if(page.getList()!=null){
			for(ZhenghePay pay:page.getList()){
				if(pay.getPatientId()!=null){
					ZhenghePatient patient = zhenghePatientService.get(pay.getPatientId());
					if(patient!=null){
						pay.setPatientId(patient.getPatientName());
					}
				}
			}
		}
		model.addAttribute("page", page);
		return "modules/zhenghe/zhenghePayList";
	}

	@RequiresPermissions("zhenghe:zhenghePay:view")
	@RequestMapping(value = "form")
	public String form(ZhenghePay zhenghePay, Model model) {
		model.addAttribute("zhenghePay", zhenghePay);
		return "modules/zhenghe/zhenghePayForm";
	}

	@RequiresPermissions("zhenghe:zhenghePay:edit")
	@RequestMapping(value = "save")
	public String save(ZhenghePay zhenghePay, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, zhenghePay)){
			return form(zhenghePay, model);
		}
		zhenghePayService.save(zhenghePay);
		addMessage(redirectAttributes, "保存支付日志成功");
		return "redirect:"+Global.getAdminPath()+"/zhenghe/zhenghePay/?repage";
	}
	
	@RequiresPermissions("zhenghe:zhenghePay:edit")
	@RequestMapping(value = "delete")
	public String delete(ZhenghePay zhenghePay, RedirectAttributes redirectAttributes) {
		zhenghePayService.delete(zhenghePay);
		addMessage(redirectAttributes, "删除支付日志成功");
		return "redirect:"+Global.getAdminPath()+"/zhenghe/zhenghePay/?repage";
	}

}