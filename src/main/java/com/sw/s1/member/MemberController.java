package com.sw.s1.member;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/member/**")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("login")
	public String setLogin() throws Exception {
		return "member/memberLogin";
	}
	
	@PostMapping("login")
	public String setLogin(MemberVO memberVO, HttpSession session) throws Exception{
		memberVO = memberService.setLogin(memberVO);
		System.out.println(memberVO);
		session.setAttribute("member", memberVO);
		return "redirect:../";
	}

	@GetMapping("logout")
	public String setLogout(HttpSession session)throws Exception{
		session.invalidate();
		return "redirect:../";
	}
	
	
	@GetMapping("join")
	public String setJoin() throws Exception{	
		return "member/memberJoin";
	}
	
	@PostMapping("join")
	public String setJoin(MemberVO memberVO, MultipartFile file, HttpSession session, Model model) throws Exception{
		int result = memberService.setJoin(memberVO,file,session);
		
		System.out.println(file.getName());
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getSize());
		System.out.println(file.isEmpty());
		
		return "redirect:../";
	}
	
	
	@GetMapping("memberPage")
	public void getMyPage()throws Exception{
		
	}
	
}
