package com.sw.s1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.sw.s1.interceptor.TestInterceptor;

//xml을 대신해서 하는 설정파일입니다(root-context, servlet-context) => Configuration
@Configuration
public class InterceptorConfig implements WebMvcConfigurer { //mvc = model view controller
	
	@Autowired
	private TestInterceptor testInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 적용할 Interceptor bean을 등록
		registry.addInterceptor(testInterceptor)
		//add -> Interceptor 적용할 URL 등록
		//.addPathPatterns("/notice/**")
		.addPathPatterns("/qna/**")
		//exclude -> Interceptor에서 제외할 URL 등록
		.excludePathPatterns("/notice/select");
		// method chaining 계속 연결해서 쓸 수 있음
		// 어떤 URL이 왔을 때 Interceptor를 발동 시킬것인지(URL 설정)
		
		//WebMvcConfigurer.super.addInterceptors(registry);
	}

}
