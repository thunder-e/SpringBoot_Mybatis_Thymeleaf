package com.sw.s1.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.sw.s1.member.MemberVO;

@Component
public class AdminInterceptor implements HandlerInterceptor{

	
	//controller 진입 전 admin 판별
	//admin이면 진행 
	//아니면
	//1. 로그인 폼으로 redirect
	//2. 권한이 없습니다 alert, 확인 누르면 index로 이동
	
	// AdminInterceptorConfig
	// /notice/insert, update, delete
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
			// 4가지 내장객체											생명주기
			// page			: 현재 페이지 실행되면 생성, 벗어나면 사라짐		*****
			// request		: 서버실행 후 요청이 오면 생성, 응답이 나가면 사라짐	*********
			// session		: 서버실행 후 웹브라우저에 접속, 일정시간 후 사라짐	*************
			// application	: 서버시작, 서버종료							********************
			// 생명주기가 짧은것 -> 긴것은 가능 
			// 반대는 만들어질수도 안만들어질수도 있으니까 불가능
		
		
		
		System.out.println("PreHandle 시작");
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		boolean result = false;
		
		
		if(memberVO != null && memberVO.getUsername().equals("admin")) {
				result = true;
		} else {
			//1. redirect login
			response.sendRedirect("/member/login");
			//2. forward
//			request.setAttribute("msg", "관리자가 아닙니다");
//			request.setAttribute("path", "../member/login");
//			RequestDispatcher view = request.getRequestDispatcher("/common/result.html");
//			view.forward(request, response);
		}
		
		

		return result;
	}
	
	
	
	
}
