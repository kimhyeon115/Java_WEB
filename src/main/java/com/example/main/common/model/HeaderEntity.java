package com.example.main.common.model;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class HeaderEntity {
	private int code;
	private String messageCd;
	private String message;
	
	@Builder
	public HeaderEntity(HttpStatus status, int code, String messageCd, String message) {
		this.code = code;
		this.messageCd = messageCd;
		this.message = message;
	}
}
