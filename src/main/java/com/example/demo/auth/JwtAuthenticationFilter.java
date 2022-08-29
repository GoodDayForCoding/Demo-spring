package com.example.demo.auth;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.demo.vo.EmployeeVo;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

// 시큐리티에 UsernamePasswordAuthenticationFilter 가 있음
// /login 요청해서 email, passowrd 전송하면 (post)
// UsernamePasswordAuthenticationFilter 동작

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authenticationManager;

	public JwtAuthenticationFilter(AuthenticationManager authenticationManager, String url) {
		this.authenticationManager = authenticationManager;
		setFilterProcessesUrl(url);
	}

	// /login 요청을 하면 로그인 시도를 위해서 실행되는 함수
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		// 1. email, password 받아서
		try {
//			BufferedReader br = request.getReader();
//			
//			String input = null;
//			while((input = br.readLine()) != null) {
//				System.out.println(input);
//			}

			ObjectMapper om = new ObjectMapper();
			EmployeeVo vo = om.readValue(request.getInputStream(), EmployeeVo.class);
			// System.out.println(vo);

			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
					vo.getEmail(), vo.getPassword());

			// PrincipalDetailsService가 호출, loadUserByUsername() 함수가 실행
			// email과 password가 일치
			Authentication authentication = authenticationManager.authenticate(authenticationToken);

			// 로그인 완료
			PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();

			// 4. JWT토큰을 만들어서 응답을 해주면 됨
			// authentication 객체가 session영역에 저장
			// 리턴의 이유는 권한관리를 security에서 대신 해주기 때문
			// jwt토큰을 사용하면서 session을 만들 이유는 없음 권한 처리 때문에

			return authentication;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// attemptAuthentication 실행 후 인증이 정상적으로 되었으면 successfulAuthentication 실행
	// jwt 토큰을 만들어서 응답
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();

		// RSA X, Hash암호방식
		// 프로퍼티로 암호화 시키기
		String jwtToken = JWT.create().withSubject("sub")
				.withExpiresAt(new Date(System.currentTimeMillis() + (60000 * 60) * 8)) // 토큰 만료기간 8시간
				.withClaim("email", principalDetails.getEmployeeVo().getEmail()) // 원하는 값을 토큰에 넣을 수 있다
				.withClaim("role", principalDetails.getEmployeeVo().getRole())
				.withClaim("hospitalNo", principalDetails.getEmployeeVo().getHospitalNo())
				.withClaim("no", principalDetails.getEmployeeVo().getNo()).sign(Algorithm.HMAC512("cos"));

		response.addHeader("Authorization", "Bearer " + jwtToken);
	}

	@Override
	public void setFilterProcessesUrl(String filterProcessesUrl) {
		super.setFilterProcessesUrl(filterProcessesUrl);
	}
}
