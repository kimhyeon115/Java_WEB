package com.example.main.system.user.model;

import lombok.Data;
import com.example.main.common.model.BaseVO;

@Data
public class User extends BaseVO {
	
	private String userId;
	private String userPwd;
	private String userNm;
	private String department;
	private String email;
	private String phone;
	private String remark;
	private String loginStat;
	private String lastLoginDt;
	private String lastPwdChgDt;
	private String pwdExpDays;
	private String initYn;
	private String useYn;
}
