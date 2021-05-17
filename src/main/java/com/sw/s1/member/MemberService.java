package com.sw.s1.member;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sw.s1.util.FileManager;

@Service
public class MemberService {
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private FileManager fileManager;
	
	//join
	public int setJoin(MemberVO memberVO, MultipartFile file, HttpSession session) throws Exception{
		int result = memberMapper.setJoin(memberVO);
		
		String filePath="upload/member/";
		
		String fileName=fileManager.save(file, filePath);
		System.out.println(fileName);
		MemberFileVO memberFileVO = new MemberFileVO();
		memberFileVO.setFileName(fileName);
		memberFileVO.setOriName(file.getOriginalFilename());
		memberFileVO.setUsername(memberVO.getUsername());
		
		memberMapper.setFileInsert(memberFileVO);
		
		return result;
	}
	//login
	public MemberVO setLogin(MemberVO memberVO) throws Exception {
		return memberMapper.setLogin(memberVO);
	}
	


}
