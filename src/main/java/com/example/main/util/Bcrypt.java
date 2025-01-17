package com.example.main.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Bcrypt {
	
	private static int strength = 10;
	
	public static String encodeBcrypt(String planeText) {
		return (new BCryptPasswordEncoder(strength)).encode(planeText);		
	}
	
	public static boolean matchesBcrypt(String planeText, String hashValue) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(strength);
		return passwordEncoder.matches(planeText, hashValue);
	}
}
