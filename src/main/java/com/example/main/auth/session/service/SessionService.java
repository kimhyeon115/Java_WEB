package com.example.main.auth.session.service;

import org.springframework.http.HttpHeaders;

import com.example.main.system.user.model.User;

import jakarta.servlet.http.HttpServletRequest;

public interface SessionService {
	
	HttpHeaders insertSession(User user);
	
	boolean validateSession(HttpServletRequest request);

}
