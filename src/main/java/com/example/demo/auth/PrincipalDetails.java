package com.example.demo.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.vo.EmployeeVo;

import lombok.Data;

// 시큐리티가 /login 주소 요청이 오면 낚아채서 로그인을 진행
// 로그인 진행이 완료가 되면 시큐리티 session을 만들어준다(Security ContextHolder)
// 오브젝트 타입 => Authentication 객체
// Authentication 안에 Employee 정보가 있어야 됨
// Employee 타입 => UserDetails 객체

// Security session => Authentication => UserDetails(PrincipalDetails)

@Data // JwtAuthenticationFilter에서 getEmployeeVo 할때 필요
public class PrincipalDetails implements UserDetails {

	private EmployeeVo employeeVo;

	public PrincipalDetails(EmployeeVo employeeVo) {
		this.employeeVo = employeeVo;
	}

	// 해당 직원의 권한을 리턴하는 곳
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collect = new ArrayList<>();
		
		collect.add(new GrantedAuthority() {
			@Override
			public String getAuthority() {
				return employeeVo.getRole();
			}
		});
		
		return collect;
	}

	@Override
	public String getPassword() {
		return employeeVo.getPassword();
	}

	@Override
	public String getUsername() {
		return employeeVo.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
