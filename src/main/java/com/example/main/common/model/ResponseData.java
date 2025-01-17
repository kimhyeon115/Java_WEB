package com.example.main.common.model;

import java.util.Map;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class ResponseData {
	private HeaderEntity header;
	private Map<String, Object> body;
	private Pagination pagination;
	
	@Builder
	public ResponseData(HeaderEntity header, Map<String, Object> body, Pagination pagination) {
		this.header = header;
		this.body = body;
		this.pagination = pagination;
	}
	
	@Builder
	public ResponseData(HeaderEntity header, Map<String, Object> body) {
		this.header = header;
		this.body = body;
	}
}
