package com.fullteem.modules.zhenghe.api.alipay;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fullteem.common.web.BaseController;
import com.fullteem.modules.zhenghe.api.alipay.config.AlipayConfig;
import com.fullteem.modules.zhenghe.api.alipay.util.AlipayNotify;
import com.fullteem.modules.zhenghe.entity.ZhengheOrder;
import com.fullteem.modules.zhenghe.entity.ZhenghePay;
import com.fullteem.modules.zhenghe.service.ZhengheOrderService;
import com.fullteem.modules.zhenghe.service.ZhenghePayService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Api(value = "AlipayNotifyController", description = "支付宝异步通知")
@Controller
@RequestMapping(value = "/api/alipay/AlipayNotifyController")
public class AlipayNotifyController extends BaseController {

    @Resource
    private ZhengheOrderService orderService;
    @Resource
    private ZhenghePayService payService;

    @ApiOperation(value = "支付宝通知", notes = "")
    @RequestMapping(value = "/alipayNotify", method = RequestMethod.POST)
    @Transactional
    public void alipayNotify(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String encoding = request.getCharacterEncoding();

        logger.debug("---------->支付宝的通知来啦 ~~~~~~~~~");
        PrintWriter out = response.getWriter();

        //获取支付宝POST过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            valueStr = new String(valueStr.getBytes(encoding), "utf-8");
            params.put(name, valueStr);
        }

        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
        //商户订单号
        String out_trade_no = new String(request.getParameter("out_trade_no").getBytes(encoding), "UTF-8");
        logger.debug("---------->商户订单号:" + out_trade_no);
        //支付宝交易号
        String trade_no = new String(request.getParameter("trade_no").getBytes(encoding), "UTF-8");
        logger.debug("---------->支付宝交易号:" + trade_no);
        //交易状态
        String trade_status = new String(request.getParameter("trade_status").getBytes(encoding), "UTF-8");
        logger.debug("---------->交易状态:" + trade_status);
        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//

        //if(AlipayNotify.verify(params)){//验证成功
        try {
            if (AlipaySignature.rsaCheckV1(params, AlipayConfig.ali_public_key, "utf-8", "RSA2")) {//验证成功
                //////////////////////////////////////////////////////////////////////////////////////////
                //请在这里加上商户的业务逻辑程序代码
                //——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
                ZhengheOrder order = null;
                if (trade_status.equals("TRADE_FINISHED")) {
                    //判断该笔订单是否在商户网站中已经做过处理
                    //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序

                    out.println("success");    //请不要修改或删除
                    logger.debug("---------->交易成功！");

                    //注意：
                    //退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
                    //请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
                } else if (trade_status.equals("TRADE_SUCCESS")) {
                    //判断该笔订单是否在商户网站中已经做过处理
                    //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                    //如果有做过处理，不执行商户的业务程序

                    /*----------------------------------------------------------------*/

                    ZhengheOrder orderTemp = new ZhengheOrder();
                    orderTemp.setParentOrderNo(out_trade_no);
                    List<ZhengheOrder> orderList = orderService.findList(orderTemp);
                    //验证该通知数据中的out_trade_no是否为商户系统中创建的订单号
                    if (orderList.size() == 0) {
                        out.println("fail");
                        logger.debug("---------->out_trade_no不是是为商户系统中创建的订单号");
                        return;
                    }
                    order = orderList.get(0);
                    //如果有做过处理，不执行商户的业务程序
                    String status = order.getStatus();
                    if (!status.equals("1")) {
                        out.println("success");
                        logger.debug("---------->订单已做过处理，不执行商户的业务程序");
                        return;
                    }
                    //判断total_fee是否确实为该订单的实际金额（即商户订单创建时的金额）
                    String totalAmount = order.getTotalAmount();
                    double totalFee = Double.valueOf(totalAmount);
                    double total_fee = Double.valueOf(params.get("total_fee"));
                    if (total_fee != totalFee) {
                        out.println("fail");
                        logger.debug("---------->total_fee不是该订单的实际金额");
                        return;
                    }
                    //校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方
                    if (!params.get("seller_id").equals(AlipayConfig.partner)) {
                        out.println("fail");
                        logger.debug("---------->seller_id不是out_trade_no这笔单据的对应的操作方");
                        return;
                    }

                    out.println("success");    //请不要修改或删除
                    logger.debug("---------->交易成功！");

                    //记录订单的买家支付宝用户号
                    order.setPayId(params.get("buyer_id"));
                    order.setStatus("2");
                    orderService.save(order);

                    ZhenghePay zhenghePay = new ZhenghePay();
                    zhenghePay.setPatientId(order.getPatientId());
                    zhenghePay.setType("2");
                    zhenghePay.setPaySum(order.getTotalAmount());
                    zhenghePay.setStatus("1");
                    zhenghePay.setCreateDate(new Date());
                    payService.save(zhenghePay);

                    logger.debug("---------->支付记录保存成功！");

                    /*----------------------------------------------------------------*/
                    //注意：
                    //付款完成后，支付宝系统发送该交易状态通知
                    //请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
                } else if (trade_status.equals("WAIT_BUYER_PAY")) {
                    out.println("success");    //请不要修改或删除
                    logger.debug("---------->交易创建！");
                } else {
                    out.println("success");    //请不要修改或删除
                    logger.debug("---------->default！");
                }

                //——请根据您的业务逻辑来编写程序（以上代码仅作参考）——

                //////////////////////////////////////////////////////////////////////////////////////////
            } else {//验证失败
                out.println("fail");
                logger.debug("---------->验证失败！");
            }
        } catch (AlipayApiException e) {
            logger.error("验证失败", e);
        }
    }

}
