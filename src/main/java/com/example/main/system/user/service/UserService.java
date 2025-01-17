package com.example.main.system.user.service;

import java.util.HashMap;

import org.springframework.http.ResponseEntity;

import com.example.main.common.model.ResponseData;

public interface UserService {
	
	ResponseEntity<ResponseData> selectUserList(HashMap<String, Object> paramHashMap);
	
	ResponseEntity<ResponseData> selectUser(HashMap<String, Object> paramHashMap);

	ResponseEntity<ResponseData> insertUser(HashMap<String, Object> paramHashMap);
	
	ResponseEntity<ResponseData> deleteUser(HashMap<String, Object> paramHashMap);
	
}
