package com.example.main.system.menu.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.main.system.menu.model.Menu;

@Mapper
public interface MenuMapper {
	
	List<Menu> selectMenuList(HashMap<String, Object> paramHashMap);
	
	List<Menu> selectMenuListOfLevel(HashMap<String, Object> paramHashMap);
	
	int insertMenu(HashMap<String, Object> paramHashMap);
	
	int deleteMenu(HashMap<String, Object> paramHashMap);
	
}
