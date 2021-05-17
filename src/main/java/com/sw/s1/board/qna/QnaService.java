package com.sw.s1.board.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.sw.s1.board.BoardFileVO;
import com.sw.s1.board.BoardService;
import com.sw.s1.board.BoardVO;
import com.sw.s1.util.FileManager;
import com.sw.s1.util.Pager;

@Service
public class QnaService implements BoardService {

	@Autowired
	private QnaMapper qnaMapper;
	
	@Autowired
	private FileManager fileManager;
	
	@Override
	public List<BoardVO> getList(Pager pager) throws Exception {
		pager.makeRow();//10개 가지고옴
		pager.makeNum(qnaMapper.getTotalCount(pager));
		return qnaMapper.getList(pager);
	}

	@Override
	public BoardVO getSelect(BoardVO boardVO) throws Exception {
		qnaMapper.setHitUpdate(boardVO);
		return qnaMapper.getSelect(boardVO);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int setInsert(BoardVO boardVO, MultipartFile[] files) throws Exception {
		//1. qna table insert
		int result = qnaMapper.setInsert(boardVO);
		
		//2. qna Ref Update
		result = qnaMapper.setRefUpdate(boardVO);//autoGenerate한 값을 boardVO num에 넣어 놨으니까		
		
		//3. File Save
		String filePath="upload/qna/";
		
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
			qnaMapper.setFileInsert(boardFileVO);
		}
		
		return result;
	}

	@Override
	public int setUpdate(BoardVO boardVO) throws Exception {
		return qnaMapper.setUpdate(boardVO);
	}

	@Override
	public int setDelete(BoardVO boardVO) throws Exception {
		//1. files table의 fileName 조회
		//2. qna table에서 글 삭제 
		//3. HDD에 파일들을 삭제
		
		return qnaMapper.setDelete(boardVO);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public int setReplyInsert(BoardVO boardVO, MultipartFile [] files)throws Exception{
		//boardVO.num = 부모의 글번호
		
		//1. step update
		int result = qnaMapper.setReplyUpdate(boardVO);
		
		
		//2. reply insert
		result = qnaMapper.setReplyInsert(boardVO);
		
		//3. File HDD에 저장
		String filePath="upload/qna/";
		
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
			qnaMapper.setFileInsert(boardFileVO);
		}
		return result;
	}
	
	
	
	
	
	

}
