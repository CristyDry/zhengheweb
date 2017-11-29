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
import com.fullteem.modules.zhenghe.entity.ZhenghePharmacist;
import com.fullteem.modules.zhenghe.service.ZhenghePharmacistService;

/**
 * 药师Controller
 *
 * @author LeVis
 * @version 2017-11-28
 */
@Controller
@RequestMapping(value = "${adminPath}/zhenghe/zhenghePharmacist")
public class ZhenghePharmacistController extends BaseController {

    @Autowired
    private ZhenghePharmacistService zhenghePharmacistService;

    @ModelAttribute
    public ZhenghePharmacist get(@RequestParam(required = false) String id) {
        ZhenghePharmacist entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = zhenghePharmacistService.get(id);
        }
        if (entity == null) {
            entity = new ZhenghePharmacist();
        }
        return entity;
    }

    @RequiresPermissions("zhenghe:zhenghePharmacist:view")
    @RequestMapping(value = {"list", ""})
    public String list(ZhenghePharmacist zhenghePharmacist, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<ZhenghePharmacist> page = zhenghePharmacistService.findPage(new Page<ZhenghePharmacist>(request, response), zhenghePharmacist);
        model.addAttribute("page", page);
        return "modules/zhenghe/zhenghePharmacistList";
    }

    @RequiresPermissions("zhenghe:zhenghePharmacist:view")
    @RequestMapping(value = "form")
    public String form(ZhenghePharmacist zhenghePharmacist, Model model) {
        model.addAttribute("zhenghePharmacist", zhenghePharmacist);
        return "modules/zhenghe/zhenghePharmacistForm";
    }

    @RequiresPermissions("zhenghe:zhenghePharmacist:edit")
    @RequestMapping(value = "save")
    public String save(ZhenghePharmacist zhenghePharmacist, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, zhenghePharmacist)) {
            return form(zhenghePharmacist, model);
        }
        zhenghePharmacistService.save(zhenghePharmacist);
        addMessage(redirectAttributes, "保存药师成功");
        return "redirect:" + Global.getAdminPath() + "/zhenghe/zhenghePharmacist/?repage";
    }

    @RequiresPermissions("zhenghe:zhenghePharmacist:edit")
    @RequestMapping(value = "delete")
    public String delete(ZhenghePharmacist zhenghePharmacist, RedirectAttributes redirectAttributes) {
        zhenghePharmacistService.delete(zhenghePharmacist);
        addMessage(redirectAttributes, "删除药师成功");
        return "redirect:" + Global.getAdminPath() + "/zhenghe/zhenghePharmacist/?repage";
    }

}