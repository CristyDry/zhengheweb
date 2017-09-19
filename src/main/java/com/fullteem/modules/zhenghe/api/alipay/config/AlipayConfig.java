package com.fullteem.modules.zhenghe.api.alipay.config;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.3
 *日期：2012-08-10
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
	
 *提示：如何获取安全校验码和合作身份者ID
 *1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 *3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”

 *安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？
 *解决方法：
 *1、检查浏览器配置，不让浏览器做弹框屏蔽设置
 *2、更换浏览器或电脑，重新登录查询。
 */

public class AlipayConfig {
	
	//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	public static String partner = "2017091408721013";
	// 商户的私钥
	public static String private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCOwax/Ow+Cdik3AVG7MHMul/jkN4V9eOEED2jowyaBICplfjEmi6ZpDXhiNIAi0CBoHRF4Q6r+zn5YQ0q2DpI0XbPeL4T/q26O5GVCI/fM6shPfy3QLXgE8BYWsqt4l0p4mA/NxQmHLoDYdTZvT0Nw5a0dgBmxFVHSqSBVFt+A8srTYOzuNTVrpL1GGU92i3lGHwes0RsJ/2SPBGTrv5h0xBnIrQtRgh52Y0IG3rw8yJfhbu+M4ha2vSh6XIU1lnfEr1So5kCHdvahDtyhSsv6DY/bH/FI+KFPKYURZ8E8cM+QvXGd44M3suAHlZ5q77JEYoEyaxyAaw51ZygqeqYRAgMBAAECggEAPx+umdfQxNxg3xyrOj1zQRAcEzXlxhSUUwpJVlsshH5noNdK/oNTwzIcoDgczMgA/GiWF/q7+s3uGjMvLw+EYLlqVn3mrnLsgSzHBZcH9MmAZ36SEHJ6lpxe+AxrO/iSCn8axd8xll/ew1gZCOEgriQxB1FkiuQleSQl0to5XDfT0KNfXbpdwxS7LK1ppnxattR+2wzC8WmoFYhbSV/7XEUUldYTMe/OWFaiITJCm/0QT1mscJxb2/XEnrO/+i7bbtLlPdExujfVDH9Z1cbr5y4aa9NCf6b30ee594gXXByzK9ZrPHEbpxsJJIPt43aJsUT+glJTYm1PP6GOaRroAQKBgQDlRcPTiKoAzknKDeQ5sFjfftpZ+Ax9NeBJ5ZkvQYjxoG25IyxmhH5IoXyEkpTgJaHj+d/JGqknfEEBLL/O2O/BDU45ufRjdP4sBncVmTQNGc2x3etyerEiOpgjnUs2kqWWR4ToVcv8ajLU+GxPaeTz7n8DqWXm8s0tWFFgLVOykQKBgQCfZf3ZVOurFVEAMW0v0VQUaP16uq5s9RxzFppZb6IKN+a+ZvzHUu3oJuzQTrbR3giVxuivOGEW8XdwnLwg+tfDbmYdDlrFaT48x7sjEBp588yNRgtILV05TUS2L9VHZAOAqOxKHGwnwYqtV+0efkKJnbGrLXmpTptLOlqSMWJ7gQKBgQCXsHC/o8vWfKWVaVt2T5Z9SBBvNg4NSXf7bEzNVy6jruDpfLwDl+V1aGJZ1CFrq3pQuyG0jE8eMLdRb6n30M/Pil4XE1D9/Imt1xky0Qn/IPNWmt0MAODxJJND/O2mTaAArZDhpuIvPpQjE7mVURqJyWF/qrQg+MOHHMe6OkMGAQKBgQCQDmAN84aNdtVexEy2g4tmasBzE5YiGJ+ej5XtMEuUypK6D19yUMt8HPm3BPyKzQybOEoAWYa9cnsMhkor0CgBkpustNVqRkQeMDA7bwlOn/yEzQHTOFRhPijUMoShYY0c0Q3DqM5CW9MV+DnvbhJ6Rs9UMeQbFFSa92D1cLZdgQKBgBQZAHUZlS0jueBM4oaxWmG63e4eSZE3Zb9v9owJZW7iBGa7Zsys11nDimn5Gp3f0RYqy6vnHqmeOY7uqOk5lM/B8SHzEs7Gf4lp5ZOYdD62xt+xMwcXlBfKPiPpFqo48lhLm0upONguSzAxJLH6hUSxMwiwV1bCNCdmpLXBeL+8";

	//商户收款账号
	public static String seller_email = "541005379@qq.com";

	public static String seller_id = "2088821094339519";

	// 支付宝的公钥，无需修改该值
	public static String ali_public_key  = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjaLwxeUObt5bvdCT6YUqyPJq/VCh1nSegWJPJluu+6CDKBXu3ermcANfXXiYkz83gcu/SfB9qyeUHXiGGd007N9WH3rBdMZrW6Z5Lun5lIPM0VbTkr8o3YkeWpq2Tg28AymbN7wt4vkZgKth6MDS77OLjvPWwmiR1ObD6nUeYpMvxFHw1knqdCKKURfvur8prLgQ3jVfYB/72COjHNnV659lofF7vgwmtSiOEQNlEWLZT5vsUN+drPOJs70v58WZ6VubIA5kYC4bcgSHsvyuSgC2ZKMNHhbFBmEBdWx91cSITbNrw0YiDi0pugrujr1mbOCvsTV0hmLAFFOB2LqsqwIDAQAB";

	//支付宝通知uri
	public static String uri = "/api/alipay/AlipayNotifyController/alipayNotify";
	//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	

	// 调试用，创建TXT日志文件夹路径
	public static String log_path = "D:\\";

	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";
	
	// 签名方式 不需修改
	public static String sign_type = "RSA";

}
