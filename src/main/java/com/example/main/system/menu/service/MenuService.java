package com.example.main.system.menu.service;

import java.util.HashMap;

import org.springframework.http.ResponseEntity;

import com.example.main.common.model.ResponseData;

public interface MenuService {
	
	ResponseEntity<ResponseData> selectMenuList(HashMap<String, Object> paramMap);
	
	ResponseEntity<ResponseData> selectMenuListOfLevel(HashMap<String, Object> paramMap);
	
	ResponseEntity<ResponseData> insertMenu(HashMap<String, Object> paramMap);
	
	ResponseEntity<ResponseData> deleteMenu(HashMap<String, Object> paramMap);

}
