package com.example.main.system.user.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import com.example.main.system.user.mapper.UserMapper;
import com.example.main.common.model.ResponseData;
import com.example.main.system.user.model.User;
import com.example.main.common.model.HeaderEntity;
import com.example.main.util.Bcrypt;
import com.example.main.util.Const;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	
	/** USER 전체 조회 **/
	public ResponseEntity<ResponseData> selectUserList(HashMap<String, Object> paramMap) {
		HeaderEntity header;
		Map<String, Object> result = new HashMap<>();
		
		try {
			List<User> userList = userMapper.selectUserList(paramMap);
			result.put("userList", userList);
			header = HeaderEntity.builder().code(Const.SUCCESS_CODE).message(Const.SUCCESS).build();
		} catch (Exception e) {
			header = HeaderEntity.builder().code(Const.FAIL_CODE).message(Const.FAIL).build();
		}
		
		ResponseData commRes = ResponseData.builder().header(header).body(result).build();
		ResponseEntity<ResponseData> response = new ResponseEntity(commRes, HttpStatus.OK);
		return response;
	}
	
	
	/** USER 개별 조회 **/
	public ResponseEntity<ResponseData> selectUser(HashMap<String, Object> paramMap) {
		HeaderEntity header;
		Map<String, Object> result = new HashMap<>();
		
		try {
			result.put("user", userMapper.selectUser(paramMap));
			header = HeaderEntity.builder().code(Const.SUCCESS_CODE).message(Const.SUCCESS).build();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			header = HeaderEntity.builder().code(Const.FAIL_CODE).message(Const.FAIL).build();
		} 
		
		ResponseData commRes = ResponseData.builder().header(header).body(result).build();
		ResponseEntity<ResponseData> response = new ResponseEntity(commRes, HttpStatus.OK);
		return response;
	}
	
	
	/** USER 저장 **/
	public ResponseEntity<ResponseData> insertUser(HashMap<String, Object> paramMap) {
		HeaderEntity header;
		Map<String, Object> result = new HashMap<>();
		
		int res = 0;
		int err = 0;
		
		ArrayList<Map<String, Object>> userList = (ArrayList<Map<String, Object>>)paramMap.get("list");
		
		int dupliCnt = 0;
		for (int i = userList.size() - 1; i >= 0; i--) {
			User user = userMapper.selectUserByAll((HashMap)userList.get(i));
			userList.get(i).put("userPwd", Bcrypt.encodeBcrypt(userList.get(i).get("userId").toString()));
			
			dupliCnt = userMapper.cntUserByAll((HashMap)userList.get(i));
			if(dupliCnt > 0) {
				err += 1;
			}
		}
		try {
			if (userList.size() > 0 && err == 0) {
				res = this.userMapper.insertUser(paramMap);
			}
			result.put("updateRows", res);
			if (err > 0) {
				header = HeaderEntity.builder().code(Const.CONFLICT_CODE).message(Const.DUPLICATE_ID).build();
			} else {
				header = HeaderEntity.builder().code(Const.SUCCESS_CODE).message(Const.SUCCESS).build();
			}
		} catch (Exception e) {
			header = HeaderEntity.builder().code(Const.FAIL_CODE).message(Const.FAIL).build();
		}
		
		ResponseData commRes = ResponseData.builder().header(header).body(result).build();
		ResponseEntity<ResponseData> response = new ResponseEntity(commRes, HttpStatus.OK);
		return response;
	}
	
	
	/** USER 삭제 **/
	public ResponseEntity<ResponseData> deleteUser(HashMap<String, Object> paramMap) {
		HeaderEntity header;
		Map<String, Object> result = new HashMap<>();
		
		try {
			result.put("updateRows", userMapper.deleteUser(paramMap));
			header = HeaderEntity.builder().code(Const.SUCCESS_CODE).message(Const.SUCCESS).build();
		} catch (Exception e) {
			header = HeaderEntity.builder().code(Const.FAIL_CODE).message(Const.FAIL).build();
		}
		
		ResponseData commRes = ResponseData.builder().header(header).body(result).build();
		ResponseEntity<ResponseData> response = new ResponseEntity(commRes, HttpStatus.OK);
		return response;
	}
}
