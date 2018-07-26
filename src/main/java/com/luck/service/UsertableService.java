package com.luck.service;

import java.util.Map;

import com.luck.entity.Usertable;

public interface UsertableService {
	/**
	 * 用户注册
	 * 
	 * @param map
	 * @return
	 */
	int InsertRegister(Map<String, Object> map);

	/**
	 * 登录
	 * 
	 * @param usertable
	 * @return
	 */
	Usertable Loginusertable(String username);

	/**
	 * 通过id查询账户的密码、盐
	 * 
	 * @param username
	 * @return
	 */
	Usertable IDQuery(String id);

	/**
	 * 修改密码
	 * 
	 * @param map
	 * @return
	 */
	int updatePassword(Map<String, Object> map);
}
