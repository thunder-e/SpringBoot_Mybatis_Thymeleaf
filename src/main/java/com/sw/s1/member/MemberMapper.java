package com.sw.s1.member;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
	
	//join
	public int setJoin(MemberVO memberVO)throws Exception;
	//role setting
	public int setMemberRole(Map<String, String> map)throws Exception;
	//file insert
	public int setJoinFile(MemberFileVO memberFileVO)throws Exception;
	
	//login
	public MemberVO getLogin(MemberVO memberVO);
	
	//username 중복확인
	public MemberVO getUsername(MemberVO memberVO)throws Exception;
	

}
