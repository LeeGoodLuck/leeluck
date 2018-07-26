package com.luck.service.Impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luck.entity.Usertable;
import com.luck.mapper.UsertableMapper;
import com.luck.service.UsertableService;

@Service
public class UsertableServiceImpl implements UsertableService {

	@Autowired
	private UsertableMapper usertablemapper;

	@Override
	public int InsertRegister(Map<String, Object> map) {
		return usertablemapper.InsertRegister(map);
	}

	@Override
	public Usertable Loginusertable(String username) {
		return usertablemapper.LoginUsertable(username);
	}

	@Override
	public Usertable IDQuery(String id) {
		return usertablemapper.IDQuery(id);
	}

	@Override
	public int updatePassword(Map<String, Object> map) {
		return usertablemapper.updatePassword(map);
	}

}
