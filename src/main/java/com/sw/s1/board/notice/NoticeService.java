package com.sw.s1.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sw.s1.board.BoardFileVO;
import com.sw.s1.board.BoardService;
import com.sw.s1.board.BoardVO;
import com.sw.s1.util.FileManager;
import com.sw.s1.util.Pager;

@Service
public class NoticeService implements BoardService{

	@Autowired
	private NoticeMapper noticeMapper;
	
	@Autowired
	private FileManager fileManager;
	
	@Override
	public List<BoardVO> getList(Pager pager) throws Exception {
		pager.makeRow();
		Long totalCount = noticeMapper.getTotalCount(pager);
		pager.makeNum(totalCount);
		return noticeMapper.getList(pager);
	}

	@Override
	public BoardVO getSelect(BoardVO boardVO) throws Exception {
		noticeMapper.setHitUpdate(boardVO);
		return noticeMapper.getSelect(boardVO);
	}

	@Override
	public int setInsert(BoardVO boardVO, MultipartFile [] files) throws Exception {
		int result = noticeMapper.setInsert(boardVO); //먼저 insert해야 AI된 num값을 가져올 수 있음
		
		String filePath="upload/notice/";
		
		for(MultipartFile multipartFile : files) {		
			if(multipartFile.getSize()==0) {
				continue;
			}
			String fileName = fileManager.save(multipartFile, filePath); //파일네임을 리턴해줌
			System.out.println(fileName);
			BoardFileVO boardFileVO = new BoardFileVO();
			boardFileVO.setFileName(fileName); //하드디스크에 저장된 fileName
			boardFileVO.setOriName(multipartFile.getOriginalFilename());
			boardFileVO.setNum(boardVO.getNum());
			//db에 저장
			noticeMapper.setFileInsert(boardFileVO);
		}
		
		return result; 
	}

	@Override
	public int setUpdate(BoardVO boardVO) throws Exception {
		return noticeMapper.setUpdate(boardVO);
	}

	@Override
	public int setDelete(BoardVO boardVO) throws Exception {
		return noticeMapper.setDelete(boardVO);
	}

}
