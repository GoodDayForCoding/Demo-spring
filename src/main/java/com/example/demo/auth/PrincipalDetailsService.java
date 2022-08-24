package com.example.demo.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.vo.EmployeeVo;

// 시큐리티 설정에서 loginProcessingUrl("/login");
// login 요청이 오면 자동으로 UserDetailsService 타입으로 IoC되어 있는 loadUserByUsername 함수가 실행
// http://localhost:8080/login 으로 동작(시큐리티의 기본 url) => 동작 X, .formLogin().disable() 때문에
@Service
public class PrincipalDetailsService implements UserDetailsService{

	@Autowired
	private SecurityRepository securityRepository;
	
	// 시큐리티 session(내부 Authentication(내부 UserDetails))
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		EmployeeVo empolyee = securityRepository.findByEmail(email);
		if(empolyee != null) {
			return new PrincipalDetails(empolyee);
		}
		return null;
	}
}
