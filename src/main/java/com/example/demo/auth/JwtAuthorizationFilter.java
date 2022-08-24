package com.example.demo.auth;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.demo.vo.EmployeeVo;

// 시큐리티 filter 중 BasicAuthenticationFilter가 있다
// 권한이나 인증이 필요한 특정 주소를 요청했을 때 위 필터를 무조건 탄다
// 만약에 권한이나 인증이 필요 없으면 안탄다
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

	private SecurityRepository securityRepository;
	
	public JwtAuthorizationFilter(AuthenticationManager authenticationManager, SecurityRepository securityRepository) {
		super(authenticationManager);
		this.securityRepository = securityRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String jwtHeader = request.getHeader("Authorization");

		// header가 있는지 확인
		if(jwtHeader == null || !jwtHeader.startsWith("Bearer")) {
			chain.doFilter(request, response);
			return;
		}
		
		// JWT 토큰을 검증을 해서 정상적인 사용자인지 확인
		String jwtToken = request.getHeader("Authorization").replace("Bearer ", "");
		String email = 
				JWT.require(Algorithm.HMAC512("cos")).build().verify(jwtToken).getClaim("email").asString();
		
		// 서명이 정상적으로 됨(복호화)
		if(email != null) {
			EmployeeVo empolyee = securityRepository.findByEmail(email);
			
			PrincipalDetails principalDetails = new PrincipalDetails(empolyee);
			
			// jwt토큰 서명을 통해서 서명이 정상이면 authentication객체를 만들어 준다
			Authentication authentication = 
					new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());

			// 강제로 시큐리티 세션에 접근하여 authentication 객체를 저장
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			chain.doFilter(request, response);
		}
	}
}
