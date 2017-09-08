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
import com.fullteem.modules.zhenghe.entity.ZhengheArticle;
import com.fullteem.modules.zhenghe.entity.ZhengheArticleClassify;
import com.fullteem.modules.zhenghe.service.ZhengheArticleClassifyService;
import com.fullteem.modules.zhenghe.service.ZhengheArticleService;

/**
 * 知识文章Controller
 * @author 李启华
 * @version 2015-11-12
 */
@Controller
@RequestMapping(value = "${adminPath}/zhenghe/zhengheArticle")
public class ZhengheArticleController extends BaseController {

	@Autowired
	private ZhengheArticleService zhengheArticleService;
	@Resource
	private ZhengheArticleClassifyService articleClassifyService;
	
	@ModelAttribute
	public ZhengheArticle get(@RequestParam(required=false) String id) {
		ZhengheArticle entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = zhengheArticleService.get(id);
		}
		if (entity == null){
			entity = new ZhengheArticle();
		}
		return entity;
	}
	
	@RequiresPermissions("zhenghe:zhengheArticle:view")
	@RequestMapping(value = {"list", ""})
	public String list(ZhengheArticle zhengheArticle, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ZhengheArticle> page = zhengheArticleService.findPage(new Page<ZhengheArticle>(request, response), zhengheArticle); 
		List<ZhengheArticle> aList = page.getList();
		for(ZhengheArticle a : aList){
			if(StringUtils.isNotBlank(a.getClassifyId())){
				a.setClassifyName(articleClassifyService.get(a.getClassifyId()).getClassifyName());
			}
		}
		List<ZhengheArticleClassify> cList = articleClassifyService.findList(null);
		Map<String,String> cMap = new HashMap<String,String>();
		for(ZhengheArticleClassify c : cList){
			cMap.put(c.getId(), c.getClassifyName());
		}
		model.addAttribute("classifyMap",cMap);
		model.addAttribute("page", page);
		return "modules/zhenghe/zhengheArticleList";
	}

	@RequiresPermissions("zhenghe:zhengheArticle:view")
	@RequestMapping(value = "form")
	public String form(ZhengheArticle zhengheArticle, Model model) {
		List<ZhengheArticleClassify> cList = articleClassifyService.findList(null);
		Map<String,String> cMap = new HashMap<String,String>();
		for(ZhengheArticleClassify c : cList){
			cMap.put(c.getId(), c.getClassifyName());
		}
		model.addAttribute("classifyMap",cMap);
		model.addAttribute("zhengheArticle", zhengheArticle);
		return "modules/zhenghe/zhengheArticleForm";
	}

	@RequiresPermissions("zhenghe:zhengheArticle:edit")
	@RequestMapping(value = "save")
	public String save(ZhengheArticle zhengheArticle, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, zhengheArticle)){
			return form(zhengheArticle, model);
		}
		if(zhengheArticle.getAvatar().contains("|")){
			zhengheArticle.setAvatar(zhengheArticle.getAvatar().substring(zhengheArticle.getAvatar().lastIndexOf("|")+1));
		}
		zhengheArticleService.save(zhengheArticle);
		addMessage(redirectAttributes, "保存知识文章成功");
		return "redirect:"+Global.getAdminPath()+"/zhenghe/zhengheArticle/?repage";
	}
	
	@RequiresPermissions("zhenghe:zhengheArticle:edit")
	@RequestMapping(value = "delete")
	public String delete(ZhengheArticle zhengheArticle, RedirectAttributes redirectAttributes) {
		zhengheArticleService.delete(zhengheArticle);
		addMessage(redirectAttributes, "删除知识文章成功");
		return "redirect:"+Global.getAdminPath()+"/zhenghe/zhengheArticle/?repage";
	}

}