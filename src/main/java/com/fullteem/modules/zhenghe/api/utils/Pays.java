package com.fullteem.modules.zhenghe.api.utils;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.fullteem.common.utils.StringUtils;
import com.fullteem.modules.zhenghe.api.alipay.config.AlipayConfig;
import com.fullteem.modules.zhenghe.api.wchpay.config.WchpayConfig;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.DigestUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 支付
 *
 * @author yelo
 * @date 2015/10/26
 */
public class Pays {

    //日志生成在weixin.log下
    static Log log = LogFactory.getLog("com.tencent");

    private Pays() {
    }

    private static final Random random = new Random();

    /**
     * 微信支付
     *
     * @return
     */
    public static Map<String, String> payByWeiXin(String orderNo, BigDecimal totalAmount) {
        Map<String, String> params = Maps.newHashMap();
        params = weiXinParams();//APP支付

        params.put("body", orderNo);
        params.put("out_trade_no", orderNo);
        params.put("total_fee", totalAmount.multiply(new BigDecimal(100)).intValue() + "");
        params.put("sign", sign(params));
        try {
            Map<String, String> map = parseXml(Https.post(WchpayConfig.pay_url, toXml(params)));
            //公众号支付返回
            String timeStamp = getTimeStamp();
            //params.put("timeStamp", timeStamp);//时间戳
            String prepayId = map.get("prepay_id");//获取预支付交易会话标识
            String nonceStr = params.get("nonce_str");//随机数
            String mchId = map.get("mch_id");//商户号
            String paySign = getPaySign(nonceStr,WchpayConfig.mch_id,prepayId, timeStamp, WchpayConfig.appid);//公众号支付签名

            Map<String,String> result = new HashMap<String, String>();
            result.put("appId",WchpayConfig.appid);
            result.put("partnerId",WchpayConfig.mch_id);
            result.put("prepayId",prepayId);
            result.put("nonceStr",nonceStr);
            result.put("timeStamp",timeStamp);
            result.put("packageValue","Sign=WXPay");
            result.put("sign",paySign);
            if (!"SUCCESS".equals(map.get("result_code"))) {
                //创建微信订单失败
                log.error("微信生成订单失败:" + JSON.toJSONString(map));
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return params;
    }

    /**
     * 获取公众号支付签名
     *
     * @param nonceStr
     * @param timeStamp
     */
    private static String getPaySign(String nonceStr,String partnerid,String prepayid, String timeStamp, String appId) {
        StringBuilder builder = new StringBuilder();
        builder.append("appid=").append(appId)
                .append("&noncestr=").append(nonceStr)
                .append("&package=Sign=WXPay")
                .append("&partnerid=").append(partnerid)
                .append("&prepayid=").append(prepayid)
                .append("&timestamp=").append(timeStamp)
                .append("&key=").append(WchpayConfig.key);
        return DigestUtils.md5DigestAsHex(builder.toString().getBytes()).toUpperCase();
    }

    //app支付
    private static Map<String, String> weiXinParams() {
        Map<String, String> params = Maps.newHashMap();
        params.put("appid", WchpayConfig.appid);
        params.put("mch_id", WchpayConfig.mch_id);
        params.put("nonce_str", String.valueOf(random.nextInt()));
        params.put("spbill_create_ip", WchpayConfig.create_ip);
        params.put("notify_url", WchpayConfig.notify_uri);
        params.put("trade_type", WchpayConfig.trade_type);
        return params;
    }

    private static String sign(Map<String, String> params) {
        List<String> keys = Ordering.usingToString().sortedCopy(params.keySet());//key排序
        StringBuilder sb = new StringBuilder();
        for (String key : keys) {
            sb.append(key).append("=").append(params.get(key)).append("&");
        }
        sb.append("key").append("=").append(WchpayConfig.key);
        return DigestUtils.md5DigestAsHex(sb.toString().getBytes()).toUpperCase();
    }

    private static String toXml(Map<String, String> params) {

        Iterator<String> iterator = params.keySet().iterator();
        StringBuilder sb = new StringBuilder("<xml>");
        while (iterator.hasNext()) {
            String key = iterator.next();
            sb.append("<").append(key).append(">").append(params.get(key)).append("</").append(key).append(">");
        }
        sb.append("</xml>");
        return sb.toString();
    }

    private static final String parseXmlName = "\\<(\\w+)\\>";

    public static Map<String, String> parseXml(String xml) {
        Map<String, String> result = Maps.newHashMap();
        xml = xml.substring(5, xml.length() - 6);
        Matcher matcher = Pattern.compile(parseXmlName).matcher(xml);
        while (matcher.find()) {

            String name = matcher.group(1);
            String regex = "<" + name + "><![CDATA[";
            String value = null;
            if (xml.contains(regex)) {
                value = xml.substring(xml.indexOf(regex) + regex.length(), xml.indexOf("]]></" + name + ">"));
            } else {
                regex = "<" + name + ">";
                value = xml.substring(xml.indexOf(regex) + regex.length(), xml.indexOf("</" + name + ">"));
            }
            result.put(name, value);
        }
        return result;
    }

    //获取时间戳
    private static String getTimeStamp() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }


    /**
     * 支付宝支付
     * @param outTradeNo
     * @param totalAmount
     * @return
     */
    public static Object appAliPay(String outTradeNo, BigDecimal totalAmount){
        /*StringBuilder stringBuilder =new StringBuilder();
        stringBuilder.append("partner=\"");
        stringBuilder.append(AlipayConfig.partner);
        stringBuilder.append('\"');
        stringBuilder.append("&seller_id=\"");
        stringBuilder.append(AlipayConfig.seller_email);
        stringBuilder.append('\"');
        stringBuilder.append("&out_trade_no=\"");
        stringBuilder.append(outTradeNo);
        stringBuilder.append( '\"');
        stringBuilder.append("&subject=\"");
        stringBuilder.append("若干商品");
        stringBuilder.append('\"');
        stringBuilder.append("&body=\"");
        stringBuilder.append("暂无详细描述");
        stringBuilder.append('\"');
        stringBuilder.append("&total_fee=\"" );
        stringBuilder.append(String.valueOf(totalAmount));
        stringBuilder.append('\"');
        stringBuilder.append("&notify_url=\"");
        stringBuilder.append(AlipayConfig.uri);
        stringBuilder.append('\"');
        stringBuilder.append("&service=\"mobile.securitypay.pay\"");
        stringBuilder.append("&payment_type=\"1\"");
        stringBuilder.append("&_input_charset=\"utf-8\"");
        stringBuilder.append("&it_b_pay=\"30m\"");
        stringBuilder.append("&return_url=\"m.alipay.com\"");

        String orderInfo = stringBuilder.toString();
        try {
            String sign = AlipaySignature.rsaSign(orderInfo, AlipayConfig.private_key, "utf-8");
            stringBuilder.append("&sign=\"");
            stringBuilder.append(URLEncoder.encode(sign, "utf-8"));
            stringBuilder.append('\"');
            stringBuilder.append("&sign_type=\"RSA\"");
            return stringBuilder.toString();
        } catch (AlipayApiException e) {
            log.error("签名失败",e);
        } catch (UnsupportedEncodingException e) {
            log.error("签名失败",e);
        }*/
        //实例化客户端
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", AlipayConfig.partner, AlipayConfig.private_key, "json", "utf-8", AlipayConfig.ali_public_key, "RSA2");
        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody("若干商品");
        model.setSubject("暂无详细描述");
        model.setOutTradeNo(outTradeNo);
        model.setTimeoutExpress("30m");
        model.setTotalAmount(totalAmount.toString());
        model.setProductCode("QUICK_MSECURITY_PAY");
        request.setBizModel(model);
        request.setNotifyUrl(AlipayConfig.uri);
        try {
            //这里和普通的接口调用不同，使用的是sdkExecute
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
            //就是orderString 可以直接给客户端请求，无需再做处理。
            return response.getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return null;
    }



    public static void main(String[] a) {
    }

}
