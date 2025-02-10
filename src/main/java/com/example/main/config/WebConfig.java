package com.example.main.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.main.auth.session.interceptor.SessionInterceptor;

/**
 * 요청 도메인과 서버 도메인이 다를경우 
 * @Configuration으로 클래스 생성하여
 * 글로벌로 설정해주고 CORS에러 해결
 **/

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	private final SessionInterceptor sessionInterceptor;
	
	public WebConfig(SessionInterceptor sessionInterceptor) {
		this.sessionInterceptor = sessionInterceptor;
	}
	
	@Override
    public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("*")
				.allowedMethods("GET", "POST", "PATCH", "DELETE")
                .maxAge(5000);
    }
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(sessionInterceptor)
				.addPathPatterns("/**")
				.excludePathPatterns("/api/login/");
	}
}
