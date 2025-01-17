package com.example.main.login.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import com.example.main.common.model.HeaderEntity;
import com.example.main.common.model.ResponseData;
import com.example.main.system.user.mapper.UserMapper;
import com.example.main.system.user.model.User;
import com.example.main.util.Bcrypt;
import com.example.main.util.Const;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

	@Autowired
	private UserMapper userMapper;
	
	/** USER 로그인 **/
	public ResponseEntity<ResponseData> login(HashMap<String, Object> paramMap) {
		HeaderEntity header;
		Map<String, Object> result = new HashMap<>();
		
		try {
			User user = userMapper.selectUser(paramMap);
			if(user == null) {
				header = HeaderEntity.builder().code(Const.UNAUTHORIZED_CODE).message(Const.ACCOUNT_IS_NOT_EXIST).build();
			} else if (!Bcrypt.matchesBcrypt((String)paramMap.get("userPwd"), user.getUserPwd())) {
				header = HeaderEntity.builder().code(Const.UNAUTHORIZED_CODE).message(Const.PASSWORD_IS_NOT_MATCH).build();
				paramMap.put("successYn", "N");
				userMapper.insertLoginHi(paramMap);
			} else {
				header = HeaderEntity.builder().code(Const.SUCCESS_CODE).message(Const.SUCCESS).build();
				result.put("user", userMapper.selectUser(paramMap));
				userMapper.updateLastLoginDt(paramMap);
				paramMap.put("successYn", "Y");
				userMapper.insertLoginHi(paramMap);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			header = HeaderEntity.builder().code(Const.FAIL_CODE).message(Const.FAIL).build();
		}
		
		ResponseData commRes = ResponseData.builder().header(header).body(result).build();
		ResponseEntity<ResponseData> response = new ResponseEntity<ResponseData>(commRes, HttpStatus.OK);
		return response;
	}
	
	/** USER 비밀변호 변경 **/
	public ResponseEntity<ResponseData> resetPassword(HashMap<String, Object> paramMap) {
		HeaderEntity header;
		Map<String, Object> result = new HashMap<>();
		
		try {
			User user = userMapper.selectUser(paramMap);
			if(!Bcrypt.matchesBcrypt((String)paramMap.get("userPwd"), user.getUserPwd())) {
				header = HeaderEntity.builder().code(Const.UNAUTHORIZED_CODE).message(Const.PASSWORD_IS_NOT_MATCH).build();
			} else if(!paramMap.get("newPwd").equals(paramMap.get("newPwdConfirm"))) {
				header = HeaderEntity.builder().code(Const.UNAUTHORIZED_CODE).message(Const.NEW_PASSWORD_IS_NOT_MATCH).build();
			} else {
				paramMap.put("newPwd", Bcrypt.encodeBcrypt((String)paramMap.get("newPwd")));
				result.put("updateRows", Integer.valueOf(userMapper.resetPassword(paramMap)));
				header = HeaderEntity.builder().code(Const.SUCCESS_CODE).message(Const.SUCCESS).build();
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			header = HeaderEntity.builder().code(Const.FAIL_CODE).message(Const.FAIL).build();
		}
		
		ResponseData commRes = ResponseData.builder().header(header).body(result).build();
		ResponseEntity<ResponseData> response = new ResponseEntity<ResponseData>(commRes, HttpStatus.OK);
		return response;
	}
	
	/** USER 로그아웃 **/
	public ResponseEntity<ResponseData> logout(HashMap<String, Object> paramMap) {
		HeaderEntity header;
		Map<String, Object> result = new HashMap<>();
		
		try {
			userMapper.logout(paramMap);
			header = HeaderEntity.builder().code(Const.SUCCESS_CODE).message(Const.SUCCESS).build();
		} catch (Exception e) {
			log.error(e.getMessage());
			header = HeaderEntity.builder().code(Const.FAIL_CODE).message(Const.FAIL).build();
		}
		
		ResponseData commRes = ResponseData.builder().header(header).body(result).build();
		ResponseEntity<ResponseData> response = new ResponseEntity<ResponseData>(commRes, HttpStatus.OK);
		return response;
	}
}
