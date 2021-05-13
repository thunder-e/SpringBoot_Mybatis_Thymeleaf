package com.sw.s1.board.notice;

import java.util.List;

import com.sw.s1.board.BoardFileVO;
import com.sw.s1.board.BoardVO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticeVO extends BoardVO {
	
	private List<BoardFileVO> files;
	
}
