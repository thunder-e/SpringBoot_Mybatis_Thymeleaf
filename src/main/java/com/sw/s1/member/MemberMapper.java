package com.sw.s1.member;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
	
	//join
	public int setJoin(MemberVO memberVO) throws Exception;
	//file insert
	public int setFileInsert(MemberFileVO memberFileVO) throws Exception;
	
	//login
	public MemberVO setLogin(MemberVO memberVO)throws Exception;
	


}
