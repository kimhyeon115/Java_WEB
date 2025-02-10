package com.example.main.auth.session.interceptor;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.main.auth.session.service.SessionService;
import com.example.main.common.model.HeaderEntity;
import com.example.main.common.model.ResponseData;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SessionInterceptor implements HandlerInterceptor {
	
	private final SessionService sessionService;
	private final ObjectMapper objectMapper;
	
	public SessionInterceptor(SessionService sessionService, ObjectMapper objectMapper) {
		this.sessionService = sessionService;
		this.objectMapper = objectMapper;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		if (!sessionService.validateSession(request)) {			
			response.setStatus(HttpServletResponse.SC_OK);
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			response.setCharacterEncoding("UTF-8");
			
			HeaderEntity header = HeaderEntity.builder()
					.code(401)
					.message("로그인이 필요합니다.")
					.build();
			
			ResponseData responseData = ResponseData.builder()
					.header(header)
					.body(null)
					.build();
			
			String jsonResponse = objectMapper.writeValueAsString(responseData);
			response.getWriter().write(jsonResponse);
			
			return false;
		}
		
		return true;
	}
}
