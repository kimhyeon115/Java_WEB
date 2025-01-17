package com.example.main.system.menu.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.main.common.model.ResponseData;
import com.example.main.system.menu.service.MenuService;

@RestController
@RequestMapping({"/api/system/menu"})
public class MenuController {
	
	@Autowired
	private MenuService menuService;
	
	@GetMapping({"/status/0"})
	public ResponseEntity<ResponseData> selectMenuList(
		@RequestParam(value = "menuNm", required = false) String menuMn
		, @RequestParam(value = "useYn", required = false) String useYn
	) {
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("menuNm", menuMn);
		paramMap.put("useYn", useYn);
		
		return menuService.selectMenuList(paramMap);
	}
	
	@GetMapping({"/status/0/level/2"})
	public ResponseEntity<ResponseData> selectMenuListOfLevel() {
		HashMap<String, Object> paramMap = new HashMap<>();
		return menuService.selectMenuListOfLevel(paramMap);
	}
	
	@PostMapping({"/status/1"})
	public ResponseEntity<ResponseData> insertMenu(@RequestBody HashMap<String, Object> paramMap) {
		return menuService.insertMenu(paramMap);
	}
	
	@PostMapping({"/status/3"})
	public ResponseEntity<ResponseData> deleteMenu(@RequestBody HashMap<String, Object> paramMap) {
		return menuService.deleteMenu(paramMap);
	}

}
