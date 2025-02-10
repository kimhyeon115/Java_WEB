package com.example.main.auth.session.service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.example.main.auth.session.mapper.SessionMapper;
import com.example.main.auth.session.model.Session;
import com.example.main.system.user.model.User;
import com.example.main.util.Cookie;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SessionServiceImpl implements SessionService {
	
	@Autowired 
	private SessionMapper sessionMapper;
	
	public HttpHeaders insertSession(User user) {
		int duration = 60;
		String sessionId;
		Session session = null;
		
		do {
			sessionId = UUID.randomUUID().toString();
			session = sessionMapper.selectSession(sessionId);
		} while (session != null);
		
		session = new Session();
		session.setSessionId(sessionId);
		session.setUserId(user.getUserId());
		session.setLastLoginDt(user.getLastLoginDt());
		session.setExpiryDt(Integer.toString(duration));		
		sessionMapper.insertSession(session);
		
		String sessionData = sessionId + ":" + user.getUserId();
		HttpHeaders headers = Cookie.createCookie(sessionData, duration);
		
		return headers;
	}
	
	public boolean validateSession(HttpServletRequest request) {
		String sessionCookie = extractSessionCookie(request);
		if (sessionCookie == null) {
			log.warn("*** SessionServiceImpl.validateSession (세션 쿠키 없음) ***");
			return false;
		}
		
		String decodedSessionData = new String(Base64.getUrlDecoder().decode(sessionCookie), StandardCharsets.UTF_8);
		String[] sessionParts = decodedSessionData.split(":");
		if (sessionParts.length != 2) {
			log.warn("*** SessionServiceImpl.validateSession (잘못된 세션 데이터) ***");
			return false;
		}
		
		String sessionId = sessionParts[0];
		String userId = sessionParts[1];
		
		Session session = sessionMapper.selectSession(sessionId);
		if (session == null || isSessionExpired(session)) {
			log.warn("*** SessionServiceImpl.validateSession (세션 만료 또는 존재하지 않음) ***");
			return false;
		}
		
		if (!session.getUserId().equals(userId)) {
			log.warn("*** SessionServiceImpl.validateSession (유효하지 않는 세션 쿠키) ***");
			return false;
		}
		
		return true;
	}
	
	private String extractSessionCookie(HttpServletRequest request) {
		jakarta.servlet.http.Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (jakarta.servlet.http.Cookie cookie : cookies) {
				if ("JAVA_WEB_SESSION".equals(cookie.getName())) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}
	
	private boolean isSessionExpired(Session session) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime expiryDt = LocalDateTime.parse(session.getExpiryDt(), formatter);
		return expiryDt.isBefore(LocalDateTime.now());
	}
}
