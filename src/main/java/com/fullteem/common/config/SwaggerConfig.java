package com.fullteem.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;

/**
 * 
 * @项目名称:apidoc
 * @文件名:SwaggerConfig.java
 * @包名:com.fullteem.common.config
 * @时间:2015年7月20日下午5:08:19
 * @描述:
 * @作者:Yuan.pan
 * @Copyright (c) 2015, www.fullteem.com All Rights Reserved.
 *
 */
@Configuration
@EnableSwagger
@EnableWebMvc
@ComponentScan(basePackages ={"com.fullteem.modules.*.api"})
public class SwaggerConfig {

	private SpringSwaggerConfig springSwaggerConfig;

	/**
	 * 自动装配SpringSwaggerConfig
	 */
	@Autowired
	public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) {
		this.springSwaggerConfig = springSwaggerConfig;
	}

	/**
	 * Every SwaggerSpringMvcPlugin bean is picked up by the swagger-mvc
	 * framework - allowing for multiple swagger groups i.e. same code base
	 * multiple swagger resource listings.
	 */
	@Bean
	public SwaggerSpringMvcPlugin customImplementation() {
		return new SwaggerSpringMvcPlugin(this.springSwaggerConfig).apiInfo(apiInfo()).includePatterns(".*?");
	}

	@Value("${api.title}")
	private String apiTitle;
	@Value("${api.description}")
	private String apiDescription;
	@Value("${api.contact}")
	private String apiContact;
	
	private ApiInfo apiInfo() {
		
		ApiInfo apiInfo = new ApiInfo(apiTitle, apiDescription, null, apiContact, null, null);
		return apiInfo;
	}
}