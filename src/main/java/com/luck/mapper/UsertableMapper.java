package com.luck.mapper;

import java.util.Map;

public interface UsertableMapper {
	/**
	 * 用户注册
	 * @param map
	 * @return
	 */
	int InsertRegister(Map<String, Object> map);
}
