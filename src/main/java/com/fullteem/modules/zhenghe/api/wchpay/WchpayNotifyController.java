package com.fullteem.modules.zhenghe.api.wchpay;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.fullteem.common.web.BaseController;
import com.fullteem.common.web.BaseResult;
import com.fullteem.modules.zhenghe.api.alipay.config.AlipayConfig;
import com.fullteem.modules.zhenghe.api.entity.request.RequestId;
import com.fullteem.modules.zhenghe.api.utils.ZhengheConstance;
import com.fullteem.modules.zhenghe.api.wchpay.config.WchpayConfig;
import com.fullteem.modules.zhenghe.api.wchpay.sign.MD5Service;
import com.fullteem.modules.zhenghe.api.wchpay.util.HttpUtil;
import com.fullteem.modules.zhenghe.api.wchpay.util.WchpayCore;
import com.fullteem.modules.zhenghe.api.wchpay.util.XmlUtil;
import com.fullteem.modules.zhenghe.entity.ZhengheOrder;
import com.fullteem.modules.zhenghe.entity.ZhenghePay;
import com.fullteem.modules.zhenghe.service.ZhengheOrderService;
import com.fullteem.modules.zhenghe.service.ZhenghePayService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@Api(value = "WchpayNotifyController", description = "微信支付与通知")
@Controller
@RequestMapping(value = "/api/wchpay/WchpayNotifyController")
public class WchpayNotifyController extends BaseController {

    @Resource
    private ZhengheOrderService orderService;
    @Resource
    private ZhenghePayService payService;
    @Autowired
    private HttpServletRequest request;


    @ApiOperation(value = "微信通知", notes = "该接口处于测试阶段")
    @RequestMapping(value = "/wchpayNotify", method = RequestMethod.POST)
    @Transactional
    public void wchpayNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {

        logger.debug("微信的通知来啦 ~~~~");
        PrintWriter out = response.getWriter();

        //获取微信POST过来反馈信息
        String resultXml = HttpUtil.getResultFromServer(request, WchpayConfig.charset);
        SortedMap<String, Object> resultMap = XmlUtil.createMap(resultXml);
        logger.debug("---------->交易信息");
        logger.debug(JSON.toJSONString(resultMap));
        //返回给微信的信息
        SortedMap<String, Object> postMap = new TreeMap<String, Object>();

        if ("SUCCESS".equals(resultMap.get("return_code"))) {
            logger.debug("@用户支付成功(通信标识)");
            if ("SUCCESS".equals(resultMap.get("result_code"))) {
                logger.debug("@用户支付成功(交易标识)");
                if (!WchpayCore.verify(resultMap)) {
                    logger.debug("@微信返回签名有误,数据可能被篡改");
                    postMap.put("return_code", "FAIL");
                    String postXml = XmlUtil.createXml(postMap);
                    out.write(postXml);
                    return;
                }

                String out_trade_no = (String) resultMap.get("out_trade_no");
                //////////////////////////////////////////////////////////////////////////////////////////
                //在这里加上商户的业务逻辑程序代码
                ZhengheOrder order = null;
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                ZhengheOrder orderTemp = new ZhengheOrder();
                orderTemp.setParentOrderNo(out_trade_no);
                List<ZhengheOrder> orderList = orderService.findList(orderTemp);
                //验证该通知数据中的out_trade_no是否为商户系统中创建的订单号
                if (orderList.size() == 0) {
                    postMap.put("return_code", "FAIL");
                    String postXml2 = XmlUtil.createXml(postMap);
                    out.write(postXml2);
                    logger.debug("out_trade_no不是是为商户系统中创建的订单号");
                    return;
                }
                order = orderList.get(0);
                //如果有做过处理，不执行商户的业务程序
                String status = order.getStatus();
                if (!status.equals("1")) {
                    postMap.put("return_code", "SUCCESS");
                    String postXml = XmlUtil.createXml(postMap);
                    out.write(postXml);
                    logger.debug("订单已做过处理，不执行商户的业务程序");
                    return;
                }
                //判断total_fee是否确实为该订单的实际金额（即商户订单创建时的金额）
                String totalAmount = order.getTotalAmount();
                BigDecimal totalFee = new BigDecimal(totalAmount).multiply(new BigDecimal(100));
                BigDecimal total_fee = new BigDecimal(resultMap.get("total_fee").toString());
                if (totalFee.compareTo(total_fee) != 0) {
                    out.println("fail");
                    logger.debug("total_fee不是该订单的实际金额");
                    return;
                }
                //校验通知中的mch_id是否为out_trade_no这笔单据的对应的操作方
                if (!resultMap.get("mch_id").equals(WchpayConfig.mch_id)) {
                    out.println("fail");
                    logger.debug("mch_id不是out_trade_no这笔单据的对应的操作方");
                    return;
                }

                //记录订单的微信支付订单号
                order.setPayId((String) resultMap.get("transaction_id"));
                order.setStatus("2");
                orderService.save(order);

                ZhenghePay zhenghePay = new ZhenghePay();
                zhenghePay.setPatientId(order.getPatientId());
                zhenghePay.setType("1");
                zhenghePay.setPaySum(order.getTotalAmount());
                zhenghePay.setStatus("1");
                zhenghePay.setCreateDate(new Date());
                payService.save(zhenghePay);

                logger.debug("支付记录保存成功！");

                //////////////////////////////////////////////////////////////////////////////////////////
                return;
            } else {
                logger.debug("@用户支付<交易>失败,原因:" + resultMap.get("return_msg"));
                postMap.put("return_code", "FAIL");
                String postXml = XmlUtil.createXml(postMap);
                out.write(postXml);
                return;
            }
        } else if ("FAIL".equals(resultMap.get("return_code"))) {
            logger.debug("@用户支付失败,原因:" + resultMap.get("return_msg"));
            postMap.put("return_code", "FAIL");
            String postXml = XmlUtil.createXml(postMap);
            out.write(postXml);
            return;
        }

    }


    /*@ApiOperation(value = "微信支付", notes = "统一下单接口")
    @RequestMapping(value = "/weChatPay", method = RequestMethod.POST)
    @ApiResponses({
            @ApiResponse(code = ZhengheConstance.param_fault, message = "参数格式不正确", response = String.class),
            @ApiResponse(code = ZhengheConstance.orderId_not_exist, message = "订单id不存在", response = String.class),
            @ApiResponse(code = ZhengheConstance.BASE_FAIL_CODE, message = "操作失败", response = String.class),
    })
    public ResponseEntity<BaseResult> weChatPay(@ApiParam(name = "id", value = "id填上订单id")
                                                @RequestBody RequestId requestId, HttpServletRequest request) throws Exception {

        String orderId = requestId.getId();

        if (!StringUtils.hasText(orderId)) {
            return buildFailedResultInfo(ZhengheConstance.param_fault);
        }

        ZhengheOrder order = orderService.get(orderId);

        if (order == null) {
            return buildFailedResultInfo(ZhengheConstance.orderId_not_exist);
        }

        //公众账号ID
        String appid = WchpayConfig.appid;
        //商户号
        String mch_id = WchpayConfig.mch_id;
        //随机字符串
        String nonce_str = MD5Service.encryptString(String.valueOf(new Date().getTime() / 1000).toString());
        //签名
        String sign = "";
        //商品描述
        String body = "药品费用支付";
        //商户订单号
        String out_trade_no = order.getParentOrderNo();
        //总金额
        int total_fee = (int) (Float.parseFloat(order.getTotalAmount()) * 100);
        //终端IP
        String spbill_create_ip = request.getRemoteAddr();
        //通知地址
        String notify_url = getBasePath() + request.getContextPath() + WchpayConfig.notify_uri;
        //交易类型
        String trade_type = "APP";

        SortedMap<String, Object> postMap = new TreeMap<String, Object>();
        postMap.put("appid", appid);
        postMap.put("mch_id", mch_id);
        postMap.put("nonce_str", nonce_str);
        postMap.put("body", body);
        postMap.put("out_trade_no", out_trade_no);
        postMap.put("total_fee", total_fee);
        postMap.put("spbill_create_ip", spbill_create_ip);
        postMap.put("notify_url", notify_url);
        postMap.put("trade_type", trade_type);
        sign = MD5Service.createSign(postMap);
        postMap.put("sign", sign);

        String postXml = XmlUtil.createXml(postMap);
        logger.debug("@发送的xml:" + postXml);

        String resultXml = WchpayCore.callUnifiedorder(postXml);
        logger.debug("@调用统一下单返回结果:" + resultXml);

        SortedMap<String, Object> resultMap = XmlUtil.createMap(resultXml);

        if ("SUCCESS".equals(resultMap.get("return_code"))) {
            logger.debug("@调统一下单接口成功(通信标识)");
            if ("SUCCESS".equals(resultMap.get("result_code"))) {
                logger.debug("@调统一下单接口成功(交易标识)");
                if (!WchpayCore.verify(resultMap)) {
                    logger.debug("@微信返回签名有误,支付环境不安全");
                    return buildFailedResultInfo(ZhengheConstance.BASE_FAIL_CODE);
                }
                //统一下单接口返回正常的prepay_id，再按签名规范重新生成签名后，将数据传输给APP。参与签名的字段名为appId，
                //partnerId，prepayId，nonceStr，timeStamp，package。注意：package的值格式为Sign=WXPay
                SortedMap<String, Object> map = new TreeMap<String, Object>();
                map.put("appId", resultMap.get("appid"));
                map.put("partnerId", resultMap.get("mch_id"));
                map.put("prepayId", resultMap.get("prepay_id"));
                map.put("nonceStr", resultMap.get("nonce_str"));
                map.put("timeStamp", System.currentTimeMillis());
                map.put("package", "Sign=WXPay");
                String sign2 = MD5Service.createSign(map);
                map.put("sign", sign2);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("content", XmlUtil.createXml(map));
                return buildSuccessResultInfo(jsonObject);
            } else {
                logger.debug("@调统一下单接口<交易>失败,原因:" + resultMap.get("return_msg"));
                return buildFailedResultInfo(ZhengheConstance.BASE_FAIL_CODE);
            }
        } else if ("FAIL".equals(resultMap.get("return_code"))) {
            logger.debug("@调统一下单接口失败,原因:" + resultMap.get("return_msg"));
            return buildFailedResultInfo(ZhengheConstance.BASE_FAIL_CODE);
        }

        return buildFailedResultInfo(ZhengheConstance.BASE_FAIL_CODE);
    }


    *//*
     * 获取路径(http协议+服务器ip[或域名]+端口号)
     *//*
    private String getBasePath() {
        String basePath = new StringBuilder(request.getScheme()).append("://").append(request.getServerName()).append(":")
                .append(request.getServerPort()).toString();
        return basePath;
    }*/
}
