/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.modules.zhenghe.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.fullteem.modules.zhenghe.entity.ZhengheDepartments;
import com.fullteem.modules.zhenghe.entity.ZhengheDoctor;
import com.fullteem.modules.zhenghe.service.ZhengheDepartmentsService;
import com.fullteem.modules.zhenghe.service.ZhengheDoctorService;

/**
 * 科室Controller
 * @author 李启华
 * @version 2015-11-11
 */
@Controller
@RequestMapping(value = "${adminPath}/zhenghe/zhengheDepartments")
public class ZhengheDepartmentsController extends BaseController {

	@Autowired
	private ZhengheDepartmentsService zhengheDepartmentsService;
	@Autowired
	private ZhengheDoctorService zhengheDoctorService;
	
	@ModelAttribute
	public ZhengheDepartments get(@RequestParam(required=false) String id) {
		ZhengheDepartments entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = zhengheDepartmentsService.get(id);
		}
		if (entity == null){
			entity = new ZhengheDepartments();
		}
		return entity;
	}
	
	@RequiresPermissions("zhenghe:zhengheDepartments:view")
	@RequestMapping(value = {"list", ""})
	public String list(ZhengheDepartments zhengheDepartments, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ZhengheDepartments> page = zhengheDepartmentsService.findPage(new Page<ZhengheDepartments>(request, response), zhengheDepartments); 
		List<ZhengheDepartments> list = page.getList();
		for(ZhengheDepartments d : list){
			if(StringUtils.isNotBlank(d.getSDepartmentsId())){
				d.setsDepartmentName(zhengheDepartmentsService.get(d.getSDepartmentsId()).getDepartmentsName());
			}
		}
		model.addAttribute("page", page);
		return "modules/zhenghe/zhengheDepartmentsList";
	}

	@RequiresPermissions("zhenghe:zhengheDepartments:view")
	@RequestMapping(value = "form")
	public String form(ZhengheDepartments zhengheDepartments, Model model) {
		List<ZhengheDepartments> list = zhengheDepartmentsService.findByType("1");
		Map<String,String> depMap = new HashMap<String, String>();
		for(ZhengheDepartments d : list){
			depMap.put(d.getId(), d.getDepartmentsName());
		}
		model.addAttribute("topDeps", depMap);
		model.addAttribute("zhengheDepartments", zhengheDepartments);
		return "modules/zhenghe/zhengheDepartmentsForm";
	}

	@RequiresPermissions("zhenghe:zhengheDepartments:edit")
	@RequestMapping(value = "save")
	public String save(ZhengheDepartments zhengheDepartments, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, zhengheDepartments)){
			return form(zhengheDepartments, model);
		}
		if(zhengheDepartments.getAvatar() != null && zhengheDepartments.getAvatar().contains("|")){
			System.out.println(" ------------------- has | : "+zhengheDepartments.getAvatar().lastIndexOf("|"));
			zhengheDepartments.setAvatar(zhengheDepartments.getAvatar().substring(zhengheDepartments.getAvatar().lastIndexOf("|")+1));
		}
		zhengheDepartmentsService.save(zhengheDepartments);
		addMessage(redirectAttributes, "保存科室成功");
		return "redirect:"+Global.getAdminPath()+"/zhenghe/zhengheDepartments/?repage";
	}
	
	@RequiresPermissions("zhenghe:zhengheDepartments:edit")
	@RequestMapping(value = "delete")
	public String delete(ZhengheDepartments zhengheDepartments, RedirectAttributes redirectAttributes) {
		String deptId = zhengheDepartments.getId();
		if("1".equals(zhengheDepartments.getType())){
			List<ZhengheDepartments> deptList = zhengheDepartmentsService.findBySDepartmentsId(deptId);
			if(deptList.size()!=0){
				addMessage(redirectAttributes, "删除失败,请先删除"+zhengheDepartments.getDepartmentsName()+"下的二级科室");
				return "redirect:"+Global.getAdminPath()+"/zhenghe/zhengheDepartments/?repage";
			}
		}else if("2".equals(zhengheDepartments.getType())){
			List<ZhengheDoctor> doctorList = zhengheDoctorService.findDoctorByDepartmentsId(deptId);
			if(doctorList.size()!=0){
				addMessage(redirectAttributes, "删除失败,请先删除"+zhengheDepartments.getDepartmentsName()+"下的医生");
				return "redirect:"+Global.getAdminPath()+"/zhenghe/zhengheDepartments/?repage";
			}
		}
		zhengheDepartmentsService.delete(zhengheDepartments);
		addMessage(redirectAttributes, "删除科室成功");
		return "redirect:"+Global.getAdminPath()+"/zhenghe/zhengheDepartments/?repage";
	}

}