package com.sw.s1.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member/**")
public class MemberController {
	
	@GetMapping("login")
	public String setLogin() throws Exception {
		return "member/memberLogin";
	}

	@GetMapping("join")
	public String setJoin() throws Exception{	
		return "member/memberJoin";
	}
}
