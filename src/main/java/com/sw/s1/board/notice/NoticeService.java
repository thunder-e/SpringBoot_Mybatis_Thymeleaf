package com.sw.s1.board.notice;

import java.util.List;

import org.apache.ibatis.session.SqlSessionException;
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
//@Transactional(rollbackFor =  Exception.class) //속도가 느려질 가능성이 있음  -> Transaction 필요한 메서드 위에만 주자
public class NoticeService implements BoardService{

	@Autowired
	private NoticeMapper noticeMapper;
	
	@Autowired
	private FileManager fileManager;
	
	@Override
	public List<BoardVO> getList(Pager pager) throws Exception {
		
//		if(pager.getCurPage()%2==0) {
//			throw new SqlSessionException();
//		}
		
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
	@Transactional(rollbackFor =  Exception.class)	// 예외 발생하면 rollback 합시다
	public int setInsert(BoardVO boardVO, MultipartFile [] files) throws Exception {
		int result = noticeMapper.setInsert(boardVO); //먼저 insert해야 AI된 num값을 가져올 수 있음
		
		if(result<1) {
			//예외는 발생하지 않고 결과가 0이 나올경우
			//강제로 예외 발생
			//주로 update에서 이러함
			//insert는 예외발생해서 필요없음
			throw new Exception();
		}	
		
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
