package com.sw.s1.member;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
	
	//join
	public int setJoin(MemberVO memberVO) throws Exception;
	//file insert
	public int setJoinFile(MemberFileVO memberFileVO) throws Exception;
	
	//login
	public MemberVO getLogin(MemberVO memberVO)throws Exception;
	


}
