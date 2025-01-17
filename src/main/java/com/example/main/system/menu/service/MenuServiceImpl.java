package com.example.main.system.menu.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.main.common.model.HeaderEntity;
import com.example.main.common.model.ResponseData;
import com.example.main.system.menu.mapper.MenuMapper;
import com.example.main.system.menu.model.Menu;
import com.example.main.util.Const;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MenuServiceImpl implements MenuService {
	
	@Autowired
	private MenuMapper menuMapper;
	
	/** MENU 조회 **/
	public ResponseEntity<ResponseData> selectMenuList(HashMap<String, Object> paramMap) {
		HeaderEntity header;
		Map<String, Object> result = new HashMap<>();
		
		try {
			List<Menu> menuList = menuMapper.selectMenuList(paramMap);
			result.put("menuList", menuList);
			header = HeaderEntity.builder().code(Const.SUCCESS_CODE).message(Const.SUCCESS).build();
		} catch (Exception e) {
			System.out.println(e);
			header = HeaderEntity.builder().code(Const.FAIL_CODE).message(Const.FAIL).build();
		}
		
		ResponseData commRes = ResponseData.builder().header(header).body(result).build();
		ResponseEntity<ResponseData> response = new ResponseEntity(commRes, HttpStatus.OK);
		return response;
	}
	
	/** MENU 레벨 조회 **/
	public ResponseEntity<ResponseData> selectMenuListOfLevel(HashMap<String, Object> paramMap) {
		HeaderEntity header;
		Map<String, Object> result = new HashMap<>();
		
		try {
			List<Menu> menuList = menuMapper.selectMenuListOfLevel(paramMap);
			result.put("menuList", menuList);
			header = HeaderEntity.builder().code(Const.SUCCESS_CODE).message(Const.SUCCESS).build();
		} catch (Exception e) {
			header = HeaderEntity.builder().code(Const.FAIL_CODE).message(Const.FAIL).build();
		} 
		
		ResponseData commRes = ResponseData.builder().header(header).body(result).build();
		ResponseEntity<ResponseData> response = new ResponseEntity(commRes, HttpStatus.OK);
		return response;
	}
	
	/** MENU 저장 **/
	public ResponseEntity<ResponseData> insertMenu(HashMap<String, Object> paramMap) {
		HeaderEntity header;
		Map<String, Object> result = new HashMap<>();
		
		try {
			result.put("updateRows", menuMapper.insertMenu(paramMap));
			header = HeaderEntity.builder().code(Const.SUCCESS_CODE).message(Const.SUCCESS).build();
		} catch (Exception e) {
			header = HeaderEntity.builder().code(Const.FAIL_CODE).message(Const.FAIL).build();
		} 
		
		ResponseData commRes = ResponseData.builder().header(header).body(result).build();
		ResponseEntity<ResponseData> response = new ResponseEntity(commRes, HttpStatus.OK);
		return response;
	}
	
	/** MENU 삭제 **/
	public ResponseEntity<ResponseData> deleteMenu(HashMap<String, Object> paramMap) {
		HeaderEntity header;
		Map<String, Object> result = new HashMap<>();
		
		try {
			result.put("updateRows", menuMapper.deleteMenu(paramMap));
			header = HeaderEntity.builder().code(Const.SUCCESS_CODE).message(Const.SUCCESS).build();
		} catch (Exception e) {
			header = HeaderEntity.builder().code(Const.FAIL_CODE).message(Const.FAIL).build();
		} 
		
		ResponseData commRes = ResponseData.builder().header(header).body(result).build();
		ResponseEntity<ResponseData> response = new ResponseEntity(commRes, HttpStatus.OK);
		return response;
	}

}
