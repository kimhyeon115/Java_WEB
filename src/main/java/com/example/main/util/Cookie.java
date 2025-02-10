package com.example.main.util;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Base64;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;

public class Cookie {
	
	public static HttpHeaders createCookie(String sessionData, int duration) {
		
		String encodedSessionData = Base64.getUrlEncoder().encodeToString(sessionData.getBytes(StandardCharsets.UTF_8));
		
		ResponseCookie cookie = ResponseCookie.from("JAVA_WEB_SESSION", encodedSessionData)
				.path("/")
				.httpOnly(false)
				.maxAge(Duration.ofMinutes(duration))
				.build();
		
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.SET_COOKIE, cookie.toString());
		
		return headers;
	}
}
