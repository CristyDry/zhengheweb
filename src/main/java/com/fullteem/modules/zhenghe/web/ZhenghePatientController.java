/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.modules.zhenghe.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
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
import com.fullteem.modules.zhenghe.api.utils.Tool;
import com.fullteem.modules.zhenghe.entity.ZhengheCity;
import com.fullteem.modules.zhenghe.entity.ZhengheDistrict;
import com.fullteem.modules.zhenghe.entity.ZhenghePatient;
import com.fullteem.modules.zhenghe.entity.ZhengheProvincial;
import com.fullteem.modules.zhenghe.service.ZhengheCityService;
import com.fullteem.modules.zhenghe.service.ZhengheDistrictService;
import com.fullteem.modules.zhenghe.service.ZhenghePatientService;
import com.fullteem.modules.zhenghe.service.ZhengheProvincialService;

/**
 * patientController
 * @author 李启华
 * @version 2015-11-16
 */
@Controller
@RequestMapping(value = "${adminPath}/zhenghe/zhenghePatient")
public class ZhenghePatientController extends BaseController {

	@Autowired
	private ZhenghePatientService zhenghePatientService;
	@Resource
	private ZhengheProvincialService provinceService;
	@Resource
	private ZhengheCityService cityService;
	@Resource
	private ZhengheDistrictService districtService;
	
	@ModelAttribute
	public ZhenghePatient get(@RequestParam(required=false) String id) {
		ZhenghePatient entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = zhenghePatientService.get(id);
		}
		if (entity == null){
			entity = new ZhenghePatient();
		}
		return entity;
	}
	
	@RequiresPermissions("zhenghe:zhenghePatient:view")
	@RequestMapping(value = {"list", ""})
	public String list(ZhenghePatient zhenghePatient, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ZhenghePatient> page = zhenghePatientService.findPage(new Page<ZhenghePatient>(request, response), zhenghePatient); 
		List<ZhenghePatient> pList = page.getList();
		for(ZhenghePatient p : pList){
			p.setProvinceName(Tool.strBlank(p.getProvincialId())?"":(provinceService.get(p.getProvincialId())==null)?"":provinceService.get(p.getProvincialId()).getName());
			p.setCityName(Tool.strBlank(p.getCityId())?"":(cityService.get(p.getCityId())==null)?"":cityService.get(p.getCityId()).getCityName());
			p.setDistrictName(Tool.strBlank(p.getDistrictId())?"":(districtService.get(p.getDistrictId()))==null?"":districtService.get(p.getDistrictId()).getDistrictName());
		}
		model.addAttribute("page", page);
		return "modules/zhenghe/zhenghePatientList";
	}

	@RequiresPermissions("zhenghe:zhenghePatient:view")
	@RequestMapping(value = "form")
	public String form(ZhenghePatient zhenghePatient, Model model) {
		// 获取省份列表
		List<ZhengheProvincial> plist = provinceService.findList(null);
		Map<String,String> pMap = new HashMap<String,String>();
		for(ZhengheProvincial p : plist){
			com.fullteem.common.utils.Log.println("-----------------------------"+p.toString());
			pMap.put(p.getId(), p.getName());
		}
		List<ZhengheCity> clist = cityService.findByProvinceId(zhenghePatient.getProvincialId());
		Map<String,String> cMap = new HashMap<String,String>();
		for(ZhengheCity c : clist){
			cMap.put(c.getId(), c.getCityName());
		}
		List<ZhengheDistrict> dlist = districtService.findByCityId(zhenghePatient.getCityId());
		Map<String,String> dMap = new HashMap<String,String>();
		for(ZhengheDistrict d:dlist){
			dMap.put(d.getId(), d.getDistrictName());
		}
		model.addAttribute("provinceMap", pMap);
		model.addAttribute("zhenghePatient", zhenghePatient);
		model.addAttribute("cityMap",cMap);
		model.addAttribute("districtMap",dMap);
		return "modules/zhenghe/zhenghePatientForm";
	}

	@RequiresPermissions("zhenghe:zhenghePatient:edit")
	@RequestMapping(value = "save")
	public String save(ZhenghePatient zhenghePatient, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, zhenghePatient)){
			return form(zhenghePatient, model);
		}		
		if(zhenghePatient.getAvatar().contains("|")){
			zhenghePatient.setAvatar(zhenghePatient.getAvatar().substring(zhenghePatient.getAvatar().lastIndexOf("|")+1));
		}
		zhenghePatientService.save(zhenghePatient);
		addMessage(redirectAttributes, "保存patient成功");
		return "redirect:"+Global.getAdminPath()+"/zhenghe/zhenghePatient/?repage";
	}
	
	@RequiresPermissions("zhenghe:zhenghePatient:edit")
	@RequestMapping(value = "delete")
	public String delete(ZhenghePatient zhenghePatient, RedirectAttributes redirectAttributes) {
		zhenghePatientService.delete(zhenghePatient);
		addMessage(redirectAttributes, "删除patient成功");
		return "redirect:"+Global.getAdminPath()+"/zhenghe/zhenghePatient/?repage";
	}

}