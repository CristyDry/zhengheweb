/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.modules.zhenghe.web;

import java.io.IOException;
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
import com.fullteem.modules.zhenghe.entity.ZhengheCity;
import com.fullteem.modules.zhenghe.entity.ZhengheDistrict;
import com.fullteem.modules.zhenghe.entity.ZhengheHospital;
import com.fullteem.modules.zhenghe.entity.ZhengheProvincial;
import com.fullteem.modules.zhenghe.service.ZhengheCityService;
import com.fullteem.modules.zhenghe.service.ZhengheDistrictService;
import com.fullteem.modules.zhenghe.service.ZhengheHospitalService;
import com.fullteem.modules.zhenghe.service.ZhengheProvincialService;

/**
 * 医院Controller
 * @author 李启华
 * @version 2015-11-12
 */
@Controller
@RequestMapping(value = "${adminPath}/zhenghe/zhengheHospital")
public class ZhengheHospitalController extends BaseController {

	@Autowired
	private ZhengheHospitalService zhengheHospitalService;
	@Resource
	private ZhengheProvincialService provinceService;
	@Resource
	private ZhengheCityService cityService;
	@Resource
	private ZhengheDistrictService districtService;
	
	
	@ModelAttribute
	public ZhengheHospital get(@RequestParam(required=false) String id) {
		ZhengheHospital entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = zhengheHospitalService.get(id);
		}
		if (entity == null){
			entity = new ZhengheHospital();
		}
		return entity;
	}
	
	@RequiresPermissions("zhenghe:zhengheHospital:view")
	@RequestMapping(value = {"list", ""})
	public String list(ZhengheHospital zhengheHospital, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ZhengheHospital> page = zhengheHospitalService.findPage(new Page<ZhengheHospital>(request, response), zhengheHospital); 
		List<ZhengheHospital> hList = page.getList();
		for(ZhengheHospital h : hList){
			String cityName = cityService.get(h.getCityId()) == null ? "" : cityService.get(h.getCityId()).getCityName();
			String districtName = districtService.get(h.getDistrictId()) == null ? "" : districtService.get(h.getDistrictId()).getDistrictName();
			h.setProvinceName(provinceService.get(h.getProvincialId()).getName());
			h.setCityName(cityName);
			h.setDistrictName(districtName);
		}
		model.addAttribute("page", page);
		return "modules/zhenghe/zhengheHospitalList";
	}

	@RequiresPermissions("zhenghe:zhengheHospital:view")
	@RequestMapping(value = "form")
	public String form(ZhengheHospital zhengheHospital, Model model) {
		
		// 获取省份列表
		List<ZhengheProvincial> plist = provinceService.findList(null);
		Map<String,String> pMap = new HashMap<String,String>();
		for(ZhengheProvincial p : plist){
			com.fullteem.common.utils.Log.println("-----------------------------"+p.toString());
			pMap.put(p.getId(), p.getName());
		}
		Map<String,String> cMap;
		if(zhengheHospital.getId()!=null){
			List<ZhengheCity> clist = cityService.findByProvinceId(zhengheHospital.getProvincialId());
			cMap = new HashMap<String,String>();
			for(ZhengheCity c : clist){
				cMap.put(c.getId(), c.getCityName());
			}
		}else{
			List<ZhengheCity> clist = cityService.findByProvinceId("19");
			cMap = new HashMap<String,String>();
			for(ZhengheCity c : clist){
				cMap.put(c.getId(), c.getCityName());
			}
		}
		List<ZhengheDistrict> dlist = districtService.findByCityId(zhengheHospital.getCityId());
		Map<String,String> dMap = new HashMap<String,String>();
		for(ZhengheDistrict d:dlist){
			dMap.put(d.getId(), d.getDistrictName());
		}
		model.addAttribute("provinceMap", pMap);
		model.addAttribute("zhengheHospital", zhengheHospital);
		model.addAttribute("cityMap",cMap);
		model.addAttribute("districtMap",dMap);
		return "modules/zhenghe/zhengheHospitalForm";
	}

	@RequiresPermissions("zhenghe:zhengheHospital:edit")
	@RequestMapping(value = "save")
	public String save(ZhengheHospital zhengheHospital, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, zhengheHospital)){
			return form(zhengheHospital, model);
		}
		zhengheHospitalService.save(zhengheHospital);
		addMessage(redirectAttributes, "保存医院成功");
		return "redirect:"+Global.getAdminPath()+"/zhenghe/zhengheHospital/?repage";
	}
	
	@RequiresPermissions("zhenghe:zhengheHospital:edit")
	@RequestMapping(value = "delete")
	public String delete(ZhengheHospital zhengheHospital, RedirectAttributes redirectAttributes) {
		zhengheHospitalService.delete(zhengheHospital);
		addMessage(redirectAttributes, "删除医院成功");
		return "redirect:"+Global.getAdminPath()+"/zhenghe/zhengheHospital/?repage";
	}
	
	/*@RequiresPermissions("zhenghe:zhengheHospital:view")
	@RequestMapping(value = "/ajaxGetCityByProvinceId")
	public String ajaxGetCityByProvinceId(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String pid = request.getParameter("provinceId");
		com.fullteem.common.utils.Log.println("------------------------------------------------ pid : "+pid);
		response.getWriter().print("123");
		return null;
	}*/

}