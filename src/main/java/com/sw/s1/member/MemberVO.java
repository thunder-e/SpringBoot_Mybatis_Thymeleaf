package com.sw.s1.member;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class MemberVO {
	
	@NotEmpty
	private String username;
	private String password1;
	
	@Length(max = 10, min = 6)
	private String password;
	
	
	@NotEmpty
	private String name;
	
	@Email
	@NotEmpty
	private String email;
	
	@NotEmpty
	private String phone;

	
}
