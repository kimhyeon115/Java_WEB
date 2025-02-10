package com.example.main.auth.session.mapper;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.example.main.auth.session.model.Session;

@Mapper
public interface SessionMapper {
	
	Session selectSession(String sessinoId);
	
	int insertSession(Session session);
	
	int deleteSession(HashMap<String, Object> paramHashMap);
	
	int updateSession(HashMap<String, Object> paramHashMap);
}
