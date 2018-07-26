package com.luck.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luck.service.UsertableService;
import com.luck.tool.JsonResult;
import com.luck.tool.MD5Util;

@RestController
@RequestMapping(value = "/usertable")
public class UsertableController {

	@Autowired
	private UsertableService usertableserice;

	/**
	 * 注册
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/register")
	public JsonResult registerInsert(HttpServletRequest request) {
		JsonResult json = JsonResult.newInstance();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String salt = UUID.randomUUID().toString();
		MD5Util md5 = new MD5Util(salt, "sha-256");
		String newpasword = md5.encode(password);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", username);
		map.put("password", newpasword);
		map.put("salt", salt);
		int count = usertableserice.InsertRegister(map);
		if (count > 0) {
			json.setDate(200);
			json.setMessage("新增成功");
			json.success();
		} else {
			json.setMessage("操作失败");
			json.failed("FAIL");
			json.setDate(500);
		}
		return json;
	}

	@RequestMapping("/login")
	public JsonResult login() {
		JsonResult json = JsonResult.newInstance();
		
		return json;
	}
}
