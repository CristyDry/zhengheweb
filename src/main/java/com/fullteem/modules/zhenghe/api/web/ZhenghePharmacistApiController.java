package com.fullteem.modules.zhenghe.api.web;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fullteem.common.utils.DateUtils;
import com.fullteem.common.utils.StringUtils;
import com.fullteem.common.web.BaseController;
import com.fullteem.common.web.BaseResult;
import com.fullteem.modules.sys.entity.Office;
import com.fullteem.modules.sys.service.OfficeService;
import com.fullteem.modules.zhenghe.api.entity.request.*;
import com.fullteem.modules.zhenghe.api.utils.ZhengheConstance;
import com.fullteem.modules.zhenghe.entity.*;
import com.fullteem.modules.zhenghe.service.*;
import com.wordnik.swagger.annotations.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

/**
 * 类名: TestApiController</br>
 * 包名：com.fullteem.modules.cosmetic.api.web </br>
 * 描述: 上传测试</br>
 * 发布版本号：</br>
 * 开发人员： 朱恋青</br>
 * 创建时间： 2015年11月2日
 */
@Api(value = "ZhenghePharmacist", description = "药师")
@Controller
@RequestMapping(value = "/api/ZhenghePharmacist")
public class ZhenghePharmacistApiController extends BaseController {

    @Autowired
    ZhenghePharmacistService zhenghePharmacistService;

    @Autowired
    private HttpServletRequest request;

    @ApiOperation(value = "药师列表", notes = "药师", httpMethod = "GET")
    @ApiResponses({
            @ApiResponse(code = ZhengheConstance.param_fault, message = "参数格式不正确", response = String.class),
            @ApiResponse(code = ZhengheConstance.BASE_SUCCESS_CODE, message = "成功", response = String.class),
            @ApiResponse(code = ZhengheConstance.BASE_FAIL_CODE, message = "失败", response = String.class)
    })
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<BaseResult> list() {
        List<ZhenghePharmacist> list = zhenghePharmacistService.findList(null);
        for(ZhenghePharmacist zhenghePharmacist: list){
            if (zhenghePharmacist.getMsg().length() > 0) {
                String[] strings = zhenghePharmacist.getMsg().split("\r\n");
                StringBuilder builder = new StringBuilder();
                for (String s: strings) {
                    builder.append("<s>");
                    builder.append(s);
                    builder.append("</s>\n");
                }
                zhenghePharmacist.setMsg(builder.toString());
            }
        }
        return buildSuccessResultInfo(list);
    }



}