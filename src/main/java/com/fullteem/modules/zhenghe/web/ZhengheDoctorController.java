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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fullteem.common.config.Global;
import com.fullteem.common.persistence.Page;
import com.fullteem.common.web.BaseController;
import com.fullteem.common.utils.StringUtils;
import com.fullteem.modules.zhenghe.entity.ZhengheDepartments;
import com.fullteem.modules.zhenghe.entity.ZhengheDoctor;
import com.fullteem.modules.zhenghe.entity.ZhengheGroup;
import com.fullteem.modules.zhenghe.entity.ZhengheHospital;
import com.fullteem.modules.zhenghe.entity.ZhengheProfession;
import com.fullteem.modules.zhenghe.service.ZhengheDepartmentsService;
import com.fullteem.modules.zhenghe.service.ZhengheDoctorService;
import com.fullteem.modules.zhenghe.service.ZhengheGroupService;
import com.fullteem.modules.zhenghe.service.ZhengheHospitalService;
import com.fullteem.modules.zhenghe.service.ZhengheProfessionService;

/**
 * 医生管理Controller
 * @author 李启华
 * @version 2015-11-11
 */
@Controller
@RequestMapping(value = "${adminPath}/zhenghe/zhengheDoctor")
public class ZhengheDoctorController extends BaseController {

	@Autowired
	private ZhengheDoctorService zhengheDoctorService;
	@Resource
	private ZhengheHospitalService hospitalService;
	@Resource
	private ZhengheDepartmentsService departmentService;
	@Resource
	private ZhengheProfessionService professionService;
	@Autowired
	private ZhengheGroupService zhengheGroupService;
	
	@ModelAttribute
	public ZhengheDoctor get(@RequestParam(required=false) String id) {
		ZhengheDoctor entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = zhengheDoctorService.get(id);
		}
		if (entity == null){
			entity = new ZhengheDoctor();
		}
		return entity;
	}
	
	@RequiresPermissions("zhenghe:zhengheDoctor:view")
	@RequestMapping(value = {"list", ""})
	public String list(ZhengheDoctor zhengheDoctor, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ZhengheDoctor> page = zhengheDoctorService.findPage(new Page<ZhengheDoctor>(request, response), zhengheDoctor); 
		List<ZhengheDoctor> dList = page.getList();
		for(ZhengheDoctor d : dList){
			d.setHospitalName(hospitalService.get(d.getHospitalId()).getHospitalName());	//所属医院
			d.setDepartmentName(departmentService.get(d.getDepartmentsId()).getDepartmentsName());//所属科室
			d.setJobTitleName(professionService.get(d.getJobTitle()).getProfession());		//职称
			System.out.println("---------------------------------------"+d.toString());
		}
		page.setList(dList);
		model.addAttribute("page", page);
		return "modules/zhenghe/zhengheDoctorList";
	}

	@RequiresPermissions("zhenghe:zhengheDoctor:view")
	@RequestMapping(value = "form")
	public String form(ZhengheDoctor zhengheDoctor, Model model) {
		List<ZhengheHospital> hList = hospitalService.findList(null);
		ZhengheDepartments dep = new ZhengheDepartments();
		dep.setType("1");
		List<ZhengheDepartments> dList = departmentService.findList(dep);
		
		if(zhengheDoctor.getDepartmentsId()!=null){
			String suprDepId = departmentService.get(zhengheDoctor.getDepartmentsId()).getSDepartmentsId();
			zhengheDoctor.setStandby1(suprDepId);
			List<ZhengheDepartments> subDList = departmentService.findBySDepartmentsId(suprDepId);
			
			Map<String,String> subMap = new HashMap<String,String>();
			
			for(ZhengheDepartments d : subDList){
				subMap.put(d.getId(), d.getDepartmentsName());
				System.out.println("---------------------------"+d.toString());
			}
			
			model.addAttribute("subMap", subMap);
		}
				
		List<ZhengheProfession> pList = professionService.findList(null);
		Map<String,String> hMap = new HashMap<String,String>();
		Map<String,String> dMap = new HashMap<String,String>();
		Map<String,String> pMap = new HashMap<String,String>();
		for(ZhengheHospital h : hList){
			hMap.put(h.getId(), h.getHospitalName());
		}
		for(ZhengheDepartments d : dList){
			dMap.put(d.getId(), d.getDepartmentsName());
			System.out.println("---------------------------"+d.toString());
		}
		for(ZhengheProfession p : pList){
			pMap.put(p.getId(), p.getProfession());
		}
		model.addAttribute("hospitalMap",hMap);
		model.addAttribute("depMap",dMap);
		model.addAttribute("professionMap",pMap);
		model.addAttribute("zhengheDoctor", zhengheDoctor);
		return "modules/zhenghe/zhengheDoctorForm";
	}

	@RequiresPermissions("zhenghe:zhengheDoctor:edit")
	@RequestMapping(value = "save")
	@Transactional
	public String save(ZhengheDoctor zhengheDoctor, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, zhengheDoctor)){
			return form(zhengheDoctor, model);
		}

		ZhengheDoctor doctor = zhengheDoctorService.findDoctorByPhone(zhengheDoctor.getPhone());
		if(zhengheDoctor.getId()==null){
			if(doctor!=null){
				addMessage(model, "手机号已被注册，请重新填写手机号");
				return form(zhengheDoctor, model);
			}
		}else{
			if(doctor!=null){
				if(!doctor.getId().equals(zhengheDoctor.getId())){
					addMessage(model, "手机号已被注册，请重新填写手机号");
					return form(zhengheDoctor, model);
				}
			}
		}
		
		if(zhengheDoctor.getAvatar().contains("|")){
			zhengheDoctor.setAvatar(zhengheDoctor.getAvatar().substring(zhengheDoctor.getAvatar().lastIndexOf("|")+1));
		}
		
		if(StringUtils.isBlank(zhengheDoctor.getId())){
			/*
			 * 医生默认登录密码:123456
			 */
			zhengheDoctor.setPassword("e10adc3949ba59abbe56e057f20f883e");
			
			zhengheDoctorService.save(zhengheDoctor);
			/*
			 * 给医生创建一个默认分组
			 */
			ZhengheGroup group = new ZhengheGroup();
			group.setGroupName("我的患者");
			group.setDoctorId(zhengheDoctor.getId());
			group.setCount("0");
			zhengheGroupService.save(group);
		}else{
			zhengheDoctorService.save(zhengheDoctor);
		}

		addMessage(redirectAttributes, "保存医生管理成功");
		return "redirect:"+Global.getAdminPath()+"/zhenghe/zhengheDoctor/?repage";
	}
	
	@RequiresPermissions("zhenghe:zhengheDoctor:edit")
	@RequestMapping(value = "delete")
	public String delete(ZhengheDoctor zhengheDoctor, RedirectAttributes redirectAttributes) {
		zhengheDoctorService.delete(zhengheDoctor);
		addMessage(redirectAttributes, "删除医生管理成功");
		return "redirect:"+Global.getAdminPath()+"/zhenghe/zhengheDoctor/?repage";
	}

}