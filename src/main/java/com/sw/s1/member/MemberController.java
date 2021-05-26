package com.sw.s1.member;

import java.util.Enumeration;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/member/**")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("login") //로그인 폼으로 가야하기때문에 살려두고
	public String getLogin() throws Exception {
		return "member/memberLogin";
	}
	
	//로그인 실패시 따로 작업하고 싶을 때
	@GetMapping("loginFail")
	public String loginFail()throws Exception{
		System.out.println("Login Fail");
		
		return "redirect:/member/login";
	}
	
	@GetMapping("memberLoginResult") //success 됐을 때 session에 넣어주는데 이름을 모르니까
	public String memberLoginResult(HttpSession session, Authentication auth2)throws Exception{
		
		//session의 속성명들 꺼내오기
		Enumeration<String> en = session.getAttributeNames();
		
		while(en.hasMoreElements()) {
			System.out.println("Attribute Name : " + en.nextElement());
		}
		
		//로그인 시 session의 속성명 : SPRING_SECURITY_CONTEXT
		Object obj = session.getAttribute("SPRING_SECURITY_CONTEXT");
		
		//System.out.println(obj);
		//SecurityContextImpl : 클래스명		
		//[Authentication=UsernamePasswordAuthenticationToken [Principal(--> 접근주체)=MemberVO(username=id5, password1=null, password=$2a$10$ZEE4HLsV4wSl2vAimhCC4.QFsmFftSgGP5Mji8rjaEYahQNfhlTfe, name=id5, email=id5@gmail.com, phone=01055555555, enabled=true, roles=[RoleVO(id=1, roleName=ROLE_ADMIN), RoleVO(id=2, roleName=ROLE_MEMBER)]), Credentials=[PROTECTED], Authenticated=true, Details=WebAuthenticationDetails [RemoteIpAddress=0:0:0:0:0:0:0:1, SessionId=null], Granted Authorities=[ROLE_ADMIN, ROLE_MEMBER]]]
		
		
		SecurityContextImpl sc = (SecurityContextImpl)obj;
		
		Authentication auth = sc.getAuthentication();
		System.out.println("=====================================");
		System.out.println("Name : " +auth.getName());
		System.out.println("Details : " +auth.getDetails());
		System.out.println("Principal : " +auth.getPrincipal());
		System.out.println("Authorities : "+auth.getAuthorities());
		System.out.println("=====================================");
		
		
		
		System.out.println("=====================================");
		System.out.println("Name : " +auth2.getName());
		System.out.println("Details : " +auth2.getDetails());
		System.out.println("Principal : " +auth2.getPrincipal());
		System.out.println("Authorities : "+auth2.getAuthorities());
		System.out.println("=====================================");
		
		
		
		System.out.println("Login 성공");
		return "redirect:/";
	}
	
	/*
	 * @PostMapping("login") public String getLogin(MemberVO memberVO, HttpSession
	 * session) throws Exception{ memberVO = memberService.getLogin(memberVO);
	 * 
	 * if(memberVO != null) { session.setAttribute("member", memberVO); } 
	 * return "redirect:/"; }
	 */

	@GetMapping("logout")
	public String logout(HttpSession session)throws Exception{
		session.invalidate();
		return "redirect:../";
	}
	
	
	@GetMapping("join")
	public String setJoin(@ModelAttribute MemberVO memberVO) throws Exception{	
		return "member/memberJoin";
	}
	
	@PostMapping("join")
	public String setJoin(@Valid MemberVO memberVO, Errors errors, MultipartFile avatar) throws Exception{
		
		/*
		  if(errors.hasErrors()) { return "member/memberJoin"; }
		 */
		
		
		if(memberService.memberError(memberVO, errors)) {
			return "member/memberJoin";
		}
		
		int result = memberService.setJoin(memberVO, avatar);
		
		return "redirect:../";
	}
	
	
	@GetMapping("page")
	public String getMyPage(HttpSession session)throws Exception{

		return "member/memberPage";
	}
	
}
