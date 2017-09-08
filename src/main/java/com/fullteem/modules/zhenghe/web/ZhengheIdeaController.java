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
import com.fullteem.modules.zhenghe.entity.ZhengheDoctor;
import com.fullteem.modules.zhenghe.entity.ZhengheIdea;
import com.fullteem.modules.zhenghe.entity.ZhenghePatient;
import com.fullteem.modules.zhenghe.service.ZhengheDoctorService;
import com.fullteem.modules.zhenghe.service.ZhengheIdeaService;
import com.fullteem.modules.zhenghe.service.ZhenghePatientService;

/**
 * 用户反馈Controller
 * @author 李启华
 * @version 2015-11-11
 */
@Controller
@RequestMapping(value = "${adminPath}/zhenghe/zhengheIdea")
public class ZhengheIdeaController extends BaseController {

	@Autowired
	private ZhengheIdeaService zhengheIdeaService;
	@Autowired
	private ZhengheDoctorService zhengheDoctorService;
	@Autowired
	private ZhenghePatientService zhenghePatientService;
	
	@ModelAttribute
	public ZhengheIdea get(@RequestParam(required=false) String id) {
		ZhengheIdea entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = zhengheIdeaService.get(id);
		}
		if (entity == null){
			entity = new ZhengheIdea();
		}
		return entity;
	}
	
	@RequiresPermissions("zhenghe:zhengheIdea:view")
	@RequestMapping(value = {"list", ""})
	public String list(ZhengheIdea zhengheIdea, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		Page<ZhengheIdea> page = zhengheIdeaService.findPage(new Page<ZhengheIdea>(request, response), zhengheIdea); 
		for(ZhengheIdea idea:page.getList()){
			if(idea.getType().equals("1")){
				ZhenghePatient patient = zhenghePatientService.get(idea.getPatientId());
				idea.setStandby1(patient.getPatientName());
			}else if(idea.getType().equals("2")){
				ZhengheDoctor doctor = zhengheDoctorService.get(idea.getDoctorId());
				idea.setStandby1(doctor.getDoctorName());
			}
		}
		model.addAttribute("page", page);
		return "modules/zhenghe/zhengheIdeaList";
	}

	@RequiresPermissions("zhenghe:zhengheIdea:view")
	@RequestMapping(value = "form")
	public String form(ZhengheIdea zhengheIdea, Model model) {
		model.addAttribute("zhengheIdea", zhengheIdea);
		return "modules/zhenghe/zhengheIdeaForm";
	}

	@RequiresPermissions("zhenghe:zhengheIdea:edit")
	@RequestMapping(value = "save")
	public String save(ZhengheIdea zhengheIdea, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, zhengheIdea)){
			return form(zhengheIdea, model);
		}
		zhengheIdeaService.save(zhengheIdea);
		addMessage(redirectAttributes, "保存用户反馈成功");
		return "redirect:"+Global.getAdminPath()+"/zhenghe/zhengheIdea/?repage";
	}
	
	@RequiresPermissions("zhenghe:zhengheIdea:edit")
	@RequestMapping(value = "delete")
	public String delete(ZhengheIdea zhengheIdea, RedirectAttributes redirectAttributes) {
		zhengheIdeaService.delete(zhengheIdea);
		addMessage(redirectAttributes, "删除用户反馈成功");
		return "redirect:"+Global.getAdminPath()+"/zhenghe/zhengheIdea/?repage";
	}

}