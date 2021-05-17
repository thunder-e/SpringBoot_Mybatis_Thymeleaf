package com.sw.s1.member;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sw.s1.board.BoardFileVO;
import com.sw.s1.util.FileManager;

@Service
public class MemberService {
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private FileManager fileManager;
	
	//join
	public int setJoin(MemberVO memberVO, MultipartFile multipartFile) throws Exception{
		//1. Member Table 저장
		int result = memberMapper.setJoin(memberVO);
		//2. HDD에 저장
		String filePath="upload/member/";
		if(multipartFile.getSize() != 0) {
			String fileName = fileManager.save(multipartFile, filePath); //파일네임을 리턴해줌
			System.out.println(fileName);
			MemberFileVO memberFileVO = new MemberFileVO();
			memberFileVO.setFileName(fileName); //하드디스크에 저장된 fileName
			memberFileVO.setOriName(multipartFile.getOriginalFilename());
			memberFileVO.setUsername(memberVO.getUsername());
		//3. MemberFiles Table 저장
			result = memberMapper.setJoinFile(memberFileVO);
		}
		
		
		return result;
	}
	
	
	//login
	public MemberVO getLogin(MemberVO memberVO) throws Exception {
		return memberMapper.getLogin(memberVO);
	}
	


}
