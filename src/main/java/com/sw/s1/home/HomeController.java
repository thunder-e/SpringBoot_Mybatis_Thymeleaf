package com.sw.s1.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sw.s1.board.BoardVO;


/**
 *  =====================================
 * 	         Thymeleaf Project 
 *  =====================================
 **/

@Controller //RestController : 모든 메서드 내에 ResponseBody 내장
public class HomeController {
	
	@GetMapping("/")
	public String home(Model model) {
		//model.addAttribute("message", "Thymeleaf Project");
		BoardVO boardVO = new BoardVO();
		boardVO.setNum(1L);
		boardVO.setTitle("title");
		boardVO.setWriter("writer");
		
		model.addAttribute("user", "선우");
		model.addAttribute("msg", "test");
		
		System.out.println(boardVO.toString());
		return "index";
	}
	

}
