package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true); // 내서버가 응답을 할때 json을 자바스크립트에서 처리할 수 있게 할지를 설정
		config.addAllowedOriginPattern("*"); // 모든 ip에 응답 허용
		config.addAllowedHeader("*"); // 모든 header에 응답 허용
		config.addAllowedMethod("*"); // 모든 post,get,put,delete에 응답 허용
		config.addExposedHeader("*"); // 모든 header를 보내준다
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}
}
