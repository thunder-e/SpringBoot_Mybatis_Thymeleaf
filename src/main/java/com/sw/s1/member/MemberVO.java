package com.sw.s1.member;

import lombok.Data;

@Data
public class MemberVO {
	
	private String username;
	private String password;
	private String name;
	private String email;
	private String phone;
	
	private MemberFileVO file;
	
}
