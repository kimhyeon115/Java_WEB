package com.example.main.system.user.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.main.system.user.model.User;

@Mapper
public interface UserMapper {
	
	List<User> selectUserList(HashMap<String, Object> paramHashMap);
	
	User selectUser(HashMap<String, Object> paramHashMap);
	
	User selectUserByAll(HashMap<String, Object> paramHashMap);
	
	int insertUser(HashMap<String, Object> paramHashMap);
	
	int deleteUser(HashMap<String, Object> paramHashMap);
	
	int resetPassword(HashMap<String, Object> paramHashMap);
	
	int updateLastLoginDt(HashMap<String, Object> paramHashMap);
	
	int insertLoginHi(HashMap<String, Object> paramMap);
	
	int logout(HashMap<String, Object> paramHashMap);
	
	int cntUserByAll(HashMap<String, Object> paramMap);
	
}
