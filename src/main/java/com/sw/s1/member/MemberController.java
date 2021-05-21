package com.sw.s1.member;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@GetMapping("login")
	public String getLogin() throws Exception {
		return "member/memberLogin";
	}
	
	@PostMapping("login")
	public String getLogin(MemberVO memberVO, HttpSession session) throws Exception{
		memberVO = memberService.getLogin(memberVO);
		
		if(memberVO != null) {
		session.setAttribute("member", memberVO);
		}
		return "redirect:/";
	}

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
		 * if(errors.hasErrors()) { return "member/memberJoin"; }
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
