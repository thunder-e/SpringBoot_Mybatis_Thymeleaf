package com.sw.s1.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration // 설정파일입니다
@EnableWebSecurity //security를 활성화할게요
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(WebSecurity web) throws Exception {
			// Security를 무시(제외) - 정적파일들
		web.ignoring()
		   .antMatchers("/resources/**")
		   .antMatchers("/images/**")
		   .antMatchers("/css/**")
		   .antMatchers("/js/**")
		   .antMatchers("/vendor/**")
		   .antMatchers("/favicon/**")
		   ;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// URL에 따른 로그인, 권한 설정
		http	
			.cors().and()
			.csrf().disable() //csrf란 토큰을 사용하지 않겠다
			.authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/notice/list","/notice/select").permitAll()
				.antMatchers("/notice/**").hasRole("ADMIN")
				.antMatchers("/qna/list").permitAll()
				.antMatchers("/qna/**").hasAnyRole("ADMIN", "MEMBER")
				.antMatchers("/member/join").permitAll()
				.antMatchers("/member/**").hasAnyRole("ADMIN", "MEMBER")
				.anyRequest().authenticated()
				.and() // 한 항목을 알려줌*
			.formLogin()
				// 로그인페이지를 따로 만들지 않아도 기본 내장된 폼으로 이동
				// 개발자가 만든 로그인폼을 사용하려면 다음과 같이 작성
				.loginPage("/member/login")
				.permitAll()
				.and()
			//.logout()
				;
	
	}
	
	
	
}
