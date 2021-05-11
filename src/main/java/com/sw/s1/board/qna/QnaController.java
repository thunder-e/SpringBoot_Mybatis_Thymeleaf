package com.sw.s1.board.qna;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/qna/**")
public class QnaController {
	
	@ModelAttribute("board")	//Controller 내의 모든 메서드가 실행되기 전에 먼저 실행되어 일일이 다 집어넣어 주는 효과 board라는 속성으로 밸류 qna를 보내줌
	public String getBoard() {
		return "qna";			// model.addAttribute("board", "qna") 효과
		//return new NoticeDTO();  ===> board라는 이름으로 DTO를 하나씩 보낼수도 있음
	}
	
	
	@GetMapping("list")
	public String getList() throws Exception {
		return "board/list";
	}

}
