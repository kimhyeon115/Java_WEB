package com.example.main.login.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.main.common.model.ResponseData;
import com.example.main.login.service.LoginService;

@RestController
@RequestMapping({"/api/login"})
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@PostMapping({"/"})
	public ResponseEntity<ResponseData> login(@RequestBody HashMap<String, Object> paramMap) {
		return loginService.login(paramMap);
	}
	
	@PostMapping({"/resetPassword"})
	public ResponseEntity<ResponseData> resetPassword(@RequestBody HashMap<String, Object> paramMap) {
		return loginService.resetPassword(paramMap);
	}
	
	@PostMapping({"/logout"})
	public ResponseEntity<ResponseData> logout(@RequestBody HashMap<String, Object> paramMap) {
		return loginService.logout(paramMap);
	}
}