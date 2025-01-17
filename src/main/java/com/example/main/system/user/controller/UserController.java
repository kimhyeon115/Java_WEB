package com.example.main.system.user.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.main.common.model.ResponseData;
import com.example.main.system.user.service.UserService;

@RestController
@RequestMapping({"/api/system/user"})
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping({"/status/0"})
	public ResponseEntity<ResponseData> selectUserList(
		@RequestParam(value = "userNm", required = false) String userNm
		, @RequestParam(value = "department", required = false) String department
		, @RequestParam(value = "useYn", required = false) String useYn
	) {
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("userNm", userNm);
		paramMap.put("department", department);
		paramMap.put("useYn", useYn);
		
		return userService.selectUserList(paramMap);
	}
	
	@GetMapping({"/status/0/{userId}"})
	public ResponseEntity<ResponseData> selectUserList(@PathVariable("userId") String userId) {
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("userId", userId);
		return userService.selectUser(paramMap);
	}

	@PostMapping({"/status/1"})
	public ResponseEntity<ResponseData> insertSample(@RequestBody HashMap<String, Object> paramMap) {
		return userService.insertUser(paramMap);
	}
	
	@PostMapping({"/status/3"})
	public ResponseEntity<ResponseData> deleteSample(@RequestBody HashMap<String, Object> paramMap) {
		return userService.deleteUser(paramMap);
	}

}
