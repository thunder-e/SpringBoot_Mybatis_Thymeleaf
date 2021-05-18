package com.sw.s1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.sw.s1.interceptor.FirstInterceptor;

@Configuration
public class FirstInterceptorConfig implements WebMvcConfigurer{

	@Autowired
	private FirstInterceptor firstInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(firstInterceptor)
				.addPathPatterns("/notice/**")
				.excludePathPatterns("/notice/select")
				.order(1); 
				// interceptor 순서 결정
				// 숫자가 낮을 수록 우선순위(먼저 실행)
	}
	
	
}
