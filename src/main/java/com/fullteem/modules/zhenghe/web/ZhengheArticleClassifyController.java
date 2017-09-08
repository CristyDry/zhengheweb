/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.modules.zhenghe.web;

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
import com.fullteem.modules.zhenghe.entity.ZhengheArticle;
import com.fullteem.modules.zhenghe.entity.ZhengheArticleClassify;
import com.fullteem.modules.zhenghe.service.ZhengheArticleClassifyService;
import com.fullteem.modules.zhenghe.service.ZhengheArticleService;

/**
 * 知识频道Controller
 * @author 李启华
 * @version 2015-11-12
 */
@Controller
@RequestMapping(value = "${adminPath}/zhenghe/zhengheArticleClassify")
public class ZhengheArticleClassifyController extends BaseController {

	@Autowired
	private ZhengheArticleClassifyService zhengheArticleClassifyService;
	@Autowired
	private ZhengheArticleService zhengheArticleService;
	
	@ModelAttribute
	public ZhengheArticleClassify get(@RequestParam(required=false) String id) {
		ZhengheArticleClassify entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = zhengheArticleClassifyService.get(id);
		}
		if (entity == null){
			entity = new ZhengheArticleClassify();
		}
		return entity;
	}
	
	@RequiresPermissions("zhenghe:zhengheArticleClassify:view")
	@RequestMapping(value = {"list", ""})
	public String list(ZhengheArticleClassify zhengheArticleClassify, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ZhengheArticleClassify> page = zhengheArticleClassifyService.findPage(new Page<ZhengheArticleClassify>(request, response), zhengheArticleClassify); 
		model.addAttribute("page", page);
		return "modules/zhenghe/zhengheArticleClassifyList";
	}

	@RequiresPermissions("zhenghe:zhengheArticleClassify:view")
	@RequestMapping(value = "form")
	public String form(ZhengheArticleClassify zhengheArticleClassify, Model model) {
		model.addAttribute("zhengheArticleClassify", zhengheArticleClassify);
		return "modules/zhenghe/zhengheArticleClassifyForm";
	}

	@RequiresPermissions("zhenghe:zhengheArticleClassify:edit")
	@RequestMapping(value = "save")
	public String save(ZhengheArticleClassify zhengheArticleClassify, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, zhengheArticleClassify)){
			return form(zhengheArticleClassify, model);
		}
		zhengheArticleClassifyService.save(zhengheArticleClassify);
		addMessage(redirectAttributes, "保存知识频道成功");
		return "redirect:"+Global.getAdminPath()+"/zhenghe/zhengheArticleClassify/?repage";
	}
	
	@RequiresPermissions("zhenghe:zhengheArticleClassify:edit")
	@RequestMapping(value = "delete")
	public String delete(ZhengheArticleClassify zhengheArticleClassify, RedirectAttributes redirectAttributes) {

		List<ZhengheArticle> articleList = zhengheArticleService.findArticleByClassifyId(zhengheArticleClassify.getId());
		if(articleList.size()!=0){
			addMessage(redirectAttributes, "删除失败,请先删除"+zhengheArticleClassify.getClassifyName()+"下的文章");
			return "redirect:"+Global.getAdminPath()+"/zhenghe/zhengheArticleClassify/?repage";
		}
		zhengheArticleClassifyService.delete(zhengheArticleClassify);
		addMessage(redirectAttributes, "删除知识频道成功");
		return "redirect:"+Global.getAdminPath()+"/zhenghe/zhengheArticleClassify/?repage";
	}

}