package com.example.main.auth.session.model;

import lombok.Data;

@Data
public class Session {

	private String sessionId;
	private String userId;
	private String regDt;
	private String lastLoginDt;
	private String expiryDt;
	private String data;
}
