package com.sw.s1.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.sw.s1.board.BoardVO;
import com.sw.s1.member.MemberVO;

@Component
public class WriterInterceptor implements HandlerInterceptor{

	//Controller 종료 후
	//작성자를 출력
	//로그인 유저네임 출력
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	
		//0. Method 형식 
		String method = request.getMethod();

		if(method.equals("GET")) {
			this.check(request, modelAndView);
		}


		//4. 로그인을 하지 않았으면 
		//   common/result 로 이동
		//   alert 로그인 필요, 로그인 이동


	}

	
	private void check(HttpServletRequest request, ModelAndView modelAndView) {
		//1. 로그인 한 username 출력
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		//	System.out.println("Username : " + memberVO.getUsername());

			//2. 작성자 출력
			BoardVO boardVO = (BoardVO)modelAndView.getModel().get("vo");
		//	System.out.println("Writer : "+ boardVO.getWriter());

			//3. 유저와 작성자가 일치하지 않으면 
			//   common/result 로 이동
			//   alert 작성자가 아닙니다, list로 이동
			if(memberVO != null && boardVO != null) {
			if(!memberVO.getUsername().equals(boardVO.getWriter())) {
				modelAndView.setViewName("/common/result.html");
				modelAndView.addObject("msg", "작성자가 아닙니다");
				modelAndView.addObject("path", "./list");

			} 
		} else {
			modelAndView.setViewName("/common/result.html");
			modelAndView.addObject("msg", "로그인이 필요합니다");
			modelAndView.addObject("path", "/member/login");
		}
	}

	//WriterInterceptorConfig
	// /qna/update





}
