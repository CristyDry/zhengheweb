/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.common.web;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.validation.Validator;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fullteem.common.beanvalidator.BeanValidators;
import com.fullteem.common.mapper.JsonMapper;
import com.fullteem.common.utils.DateUtils;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * 控制器支持类
 * 
 * @author Fullteem
 * @version 2013-3-23
 */
public abstract class BaseController {

	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 管理基础路径
	 */
	@Value("${adminPath}")
	protected String adminPath;

	/**
	 * 前端基础路径
	 */
	@Value("${frontPath}")
	protected String frontPath;

	@Value("${zhenghe.img.server}")
	protected String imageServer;

	/**
	 * 前端URL后缀
	 */
	@Value("${urlSuffix}")
	protected String urlSuffix;

	/**
	 * 验证Bean实例对象
	 */
	@Autowired
	protected Validator validator;

	@Autowired
	protected MessageSource messageSource;

	/**
	 * 服务端参数有效性验证
	 * 
	 * @param object
	 *            验证的实体对象
	 * @param groups
	 *            验证组
	 * @return 验证成功：返回true；严重失败：将错误信息添加到 message 中
	 */
	protected boolean beanValidator(Model model, Object object, Class<?>... groups) {
		try {
			BeanValidators.validateWithException(validator, object, groups);
		} catch (ConstraintViolationException ex) {
			List<String> list = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
			list.add(0, "数据验证失败：");
			addMessage(model, list.toArray(new String[] {}));
			return false;
		}
		return true;
	}

	/**
	 * 服务端参数有效性验证
	 * 
	 * @param object
	 *            验证的实体对象
	 * @param groups
	 *            验证组
	 * @return 验证成功：返回true；严重失败：将错误信息添加到 flash message 中
	 */
	protected boolean beanValidator(RedirectAttributes redirectAttributes, Object object, Class<?>... groups) {
		try {
			BeanValidators.validateWithException(validator, object, groups);
		} catch (ConstraintViolationException ex) {
			List<String> list = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
			list.add(0, "数据验证失败：");
			addMessage(redirectAttributes, list.toArray(new String[] {}));
			return false;
		}
		return true;
	}

	/**
	 * 服务端参数有效性验证
	 * 
	 * @param object
	 *            验证的实体对象
	 * @param groups
	 *            验证组，不传入此参数时，同@Valid注解验证
	 * @return 验证成功：继续执行；验证失败：抛出异常跳转400页面。
	 */
	protected void beanValidator(Object object, Class<?>... groups) {
		BeanValidators.validateWithException(validator, object, groups);
	}

	/**
	 * 添加Model消息
	 * 
	 * @param message
	 */
	protected void addMessage(Model model, String... messages) {
		StringBuilder sb = new StringBuilder();
		for (String message : messages) {
			sb.append(message).append(messages.length > 1 ? "<br/>" : "");
		}
		model.addAttribute("message", sb.toString());
	}

	/**
	 * 添加Flash消息
	 * 
	 * @param message
	 */
	protected void addMessage(RedirectAttributes redirectAttributes, String... messages) {
		StringBuilder sb = new StringBuilder();
		for (String message : messages) {
			sb.append(message).append(messages.length > 1 ? "<br/>" : "");
		}
		redirectAttributes.addFlashAttribute("message", sb.toString());
	}

	/**
	 * 客户端返回JSON字符串
	 * 
	 * @param response
	 * @param object
	 * @return
	 */
	protected String renderString(HttpServletResponse response, Object object) {
		return renderString(response, JsonMapper.toJsonString(object), "application/json");
	}

	/**
	 * 客户端返回字符串
	 * 
	 * @param response
	 * @param string
	 * @return
	 */
	protected String renderString(HttpServletResponse response, String string, String type) {
		try {
			response.reset();
			response.setContentType(type);
			response.setCharacterEncoding("utf-8");
			response.getWriter().print(string);
			return null;
		} catch (IOException e) {
			return null;
		}
	}

	/**
	 * 参数绑定异常
	 */
	@ExceptionHandler({ BindException.class, ConstraintViolationException.class, ValidationException.class })
	public String bindException() {
		return "error/400";
	}

	/**
	 * 授权登录异常
	 */
	@ExceptionHandler({ AuthenticationException.class })
	public String authenticationException() {
		return "error/403";
	}

	/**
	 * 初始化数据绑定 1. 将所有传递进来的String进行HTML编码，防止XSS攻击 2. 将字段中Date类型转换为String类型
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		// String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
		binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				setValue(text == null ? null : StringEscapeUtils.escapeHtml4(text.trim()));
			}

			@Override
			public String getAsText() {
				Object value = getValue();
				return value != null ? value.toString() : "";
			}
		});
		// Date 类型转换
		binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				setValue(DateUtils.parseDate(text));
			}
			// @Override
			// public String getAsText() {
			// Object value = getValue();
			// return value != null ? DateUtils.formatDateTime((Date)value) :
			// "";
			// }
		});
	}

	/**
	 * 构造成功返回结果
	 * 
	 * @param resultData
	 *            需要返回的数据，可选
	 * @return
	 */
	protected ResponseEntity<BaseResult> buildSuccessResultInfo(Object resultData) {
		logger.debug(String.format("enter function, %s", resultData));
		BaseResult resultVo = new BaseResult();
		resultVo.setResultData(resultData);
		resultVo.setResultMessage("success");
		return new ResponseEntity<BaseResult>(resultVo, HttpStatus.OK);
	}

	/**
	 * 构造失败返回结果
	 * 
	 * @param resultCode
	 *            : 任意非0数字，代表失败
	 * @param failedMsg
	 *            : 失败信息
	 * @return
	 */
	protected ResponseEntity<BaseResult> buildFailedResultInfo(int resultCode) {
		return buildFailedResultInfo(resultCode, null);
	}

	protected ResponseEntity<BaseResult> buildFailedResultInfo(int resultCode, Object[] obj) {
		BaseResult resultVo = new BaseResult(resultCode, getMessgae(resultCode, obj));
		return new ResponseEntity<BaseResult>(resultVo, HttpStatus.OK);
	}

	protected String getMessgae(int code) {
		return getMessgae(code, null);
	}

	protected String getMessgae(int code, Object[] obj) {
		return messageSource.getMessage(code + "", obj, Locale.CHINA);
	}

	/**
	 * 验证字段是否为空 add by yuan.pan 20150929
	 * @param obj
	 * @return
	 */
	protected ResponseEntity<BaseResult> beanValidatorIsNull(Object obj) {

		if (obj == null) {
			return buildFailedResultInfo(-1);
		}

		Class<?> cls = obj.getClass();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(cls);
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();
				if (!key.equals("class")) {
					ApiModelProperty api = cls.getDeclaredField(key).getAnnotation(ApiModelProperty.class);
					if (api.required()) {
						Method getter = property.getReadMethod();
						Object value = getter.invoke(obj);
						if (value == null) {
							return buildFailedResultInfo(9999, new Object[] { api.value() }); // 不允许为空
						}

						if (value instanceof String && !StringUtils.hasLength((String) value)) {
							return buildFailedResultInfo(9999, new Object[] { api.value() }); // 不允许为空
						}
						// 其他类型可以自行添加
					}
				}
			}
		} catch (Exception e) {
			return buildFailedResultInfo(-1);
		}
		return buildFailedResultInfo(0);
	}

	/*
	 * 获取路径(http协议+服务器ip[或域名]+端口号)
	 */
	protected String getBasePath() {
		/*	HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String basePath = new StringBuilder(request.getScheme()).append("://").append(request.getServerName()).append(":")
				.append(request.getServerPort()).toString();
		return basePath;*/
		return imageServer;
	}

	protected String getBaseContextPath(){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return imageServer + request.getContextPath();
		/*HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String basePath = new StringBuilder(request.getScheme()).append("://").append(request.getServerName()).append(":")
				.append(request.getServerPort()).append(request.getContextPath()).toString();
		return basePath;*/
	}
}
