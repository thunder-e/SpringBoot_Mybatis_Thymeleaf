package com.sw.s1.member;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartFile;


import com.sw.s1.board.BoardFileVO;
import com.sw.s1.util.FileManager;

@Service
// Spring Security에서 사용하는 Service (역시 interface형태, 메서드 오버라이딩)
// UserDetailsService 구현
public class MemberService implements UserDetailsService{
	
	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private FileManager fileManager;
	@Autowired
	private PasswordEncoder passwordEncoder; //password 암호화시켜주는 클래스
		
	@Value("${member.filePath}")
	private String filePath;
	
	//login 메서드 --> Controller는 Spring Security가 알아서 해줌, 따로 호출X
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberVO memberVO = new MemberVO();
		memberVO.setUsername(username);
		memberVO = memberMapper.getLogin(memberVO);
		return memberVO;
	}
	
	
	
	
	//검증메서드
		public boolean memberError(MemberVO memberVO, Errors errors)throws Exception {
			boolean result = false;
			
			//기본 제공 검증 결과
//			if(errors.hasErrors()) {
//				result = true;
//			}
			result = errors.hasErrors();
			
			//password 일치 여부 검증
			if(!memberVO.getPassword().equals(memberVO.getPassword1())) {
				errors.rejectValue("password1", "memberVO.password.notEqual");
							//(form path, field명, properties의 code(key));
				result=true;
			}
			
			
			//UserName 중복여부
			MemberVO checkMember = memberMapper.getUsername(memberVO);
			//checkMember가 null이면 중복X
			//checkMember가 null이 아니면 중복
			if(checkMember!=null) {
				errors.rejectValue("username", "memberVO.username.duplicate");
				result=true;
			}
			
			//admin, administrator, root
			/*
			String username = checkMember.getUsername();
			if(username.equals("admin")||username.equals("administrator")||username.equals("root")) {
				errors.rejectValue("username", "memberVO.username.admin");
				result=true;
			}
			*/
			
			return result;
		}

	
		//join
		public int setJoin(MemberVO memberVO, MultipartFile multipartFile) throws Exception{
			//0. 사전작업 (Security 사용)
			//a. password 암호화
			memberVO.setPassword(passwordEncoder.encode(memberVO.getPassword()));
			//b. 사용자 계정 활성화
			memberVO.setEnabled(true);
			
			System.out.println(memberVO.getUsername());
			System.out.println(memberVO.getPassword());
			
			
			//1. Member Table 저장
			int result = memberMapper.setJoin(memberVO);
			//2. HDD에 저장
			String filePath=this.filePath;
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
		/*
		 * public MemberVO getLogin(MemberVO memberVO) throws Exception { return
		 * memberMapper.getLogin(memberVO); }
		 */


		
		
		


}
