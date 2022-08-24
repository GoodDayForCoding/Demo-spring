package com.example.demo.auth;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		// 시큐리티가 동작하기 전에 실행되어야 한다 SecurityConfig에 addFilterBefore 설정
		// FilterConfig로 필터를 등록해서 시큐리티 필터 후에 동작한다

		// 토큰: auth를 만들어 줘야함, email, password가 정상적으로 들어와서 로그인이 완료되면 토큰을 만들고 그걸 응답
		// 요청할 때 마다 header에 Authorization에 value값으로 토큰을 가지고 온다
		// 그때 토큰이 넘어오면 이 토큰이 내가 만든 토큰이 맞는지만 검증만 하면 됨(RSA, HS256)
		String headerAuth = req.getHeader("Authorization");
		if (headerAuth != null) {
			chain.doFilter(req, res);
		} else {
			PrintWriter outPrintWriter = res.getWriter();
			outPrintWriter.println("인증 안됨");
		}
	}
}
