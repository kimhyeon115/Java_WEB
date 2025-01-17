package com.example.main.system.menu.model;

import com.example.main.common.model.BaseVO;

import lombok.Data;

@Data
public class Menu extends BaseVO {

	private String menuCd;
	private String menuNm;
	private String menuDesc;
	private String parentMenuCd;
	private String parentMenuNm;
	private String parentMenuSort;
	private String menuLv;
	private String menuSort;
	private String useYn;
	
}
