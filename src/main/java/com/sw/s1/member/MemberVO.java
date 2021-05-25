package com.sw.s1.member;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
// Spring Security에서 사용하는 VO가 존재
// UserDetails 라는 클래스
// 따로 생성(해서 구현) 또는 MemberVO에서 구현
public class MemberVO implements UserDetails{
	
	@NotEmpty
	private String username;
	private String password1;
	
	@Length(max = 10, min = 2)
	private String password;
	
	
	@NotEmpty
	private String name;
	
	@Email
	@NotEmpty
	private String email;
	
	@NotEmpty
	private String phone;

	private boolean	enabled;
	//Role
	private List<RoleVO> roles; //멤버 하나에 롤 여러개
	
	
	//Role(권한) 저장 
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		//Collection을 상속받는 것들 : List, Set
		//List를 상속받는 ArrayList를 사용할 것임
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		for(RoleVO roleVO:this.roles) {
			authorities.add(new SimpleGrantedAuthority(roleVO.getRoleName()));
		}
		
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
}
