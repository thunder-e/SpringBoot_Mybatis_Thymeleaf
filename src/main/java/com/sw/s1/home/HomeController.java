package com.sw.s1.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


/**
 *  =====================================
 * 	         Thymeleaf Project 
 *  =====================================
 **/

@Controller //RestController : 모든 메서드 내에 ResponseBody 내장
public class HomeController {
	
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("message", "Thymeleaf Project");
		return "index";
	}
	

}
