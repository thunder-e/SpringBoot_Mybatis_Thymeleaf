package com.sw.s1.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.sw.s1.member.MemberMapper;

@Component
public class MemberIdInterceptor implements HandlerInterceptor {

	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String username = request.getParameter("username");
		//Interceptor 에서도 Mapper 호출 가능
		//memberMapper.setJoin(null)
		
		return true;
	}
}
