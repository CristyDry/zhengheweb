package com.fullteem.modules.zhenghe.api.web;

import com.fullteem.common.web.BaseController;
import com.fullteem.common.web.BaseResult;
import com.fullteem.modules.zhenghe.api.utils.ZhengheConstance;
import com.google.common.collect.ImmutableMap;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by LeWis on 2018/1/17.
 */
@Api(value = "ZhengheView", description = "显示")
@Controller
@RequestMapping(value = "/api/ZhengheView")
public class ZhengheViewController extends BaseController {
    @ApiOperation(value = "开具处方", notes = "创建处方", httpMethod = "POST")
    @ApiResponses({
            @ApiResponse(code = ZhengheConstance.param_fault, message = "参数格式不正确", response = String.class),
            @ApiResponse(code = ZhengheConstance.BASE_SUCCESS_CODE, message = "成功", response = String.class),
            @ApiResponse(code = ZhengheConstance.BASE_FAIL_CODE, message = "失败", response = String.class)
    })
    @RequestMapping(value = "/view", method = RequestMethod.POST)
    public ResponseEntity<BaseResult> view() throws IOException {
        Properties prop = new Properties();
        InputStream ins = this.getClass().getResourceAsStream("/fullteem.properties");
        prop.load(ins);
        String view = prop.getProperty("zhenghe.view", "0");
        return buildSuccessResultInfo(ImmutableMap.of("view", view));
    }
}
