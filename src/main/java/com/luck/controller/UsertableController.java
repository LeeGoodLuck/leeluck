package com.luck.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luck.entity.Usertable;
import com.luck.service.UsertableService;
import com.luck.tool.JsonResult;
import com.luck.tool.MD5Util;

import com.fundebug.Fundebug;

@RestController
@RequestMapping(value = "/usertable")
public class UsertableController {

	private static final Logger LOGGER = Logger.getLogger(UsertableController.class);

	Fundebug fundebug = new Fundebug("869d90cec893b8638042043ac9b563a661ec619b424c24ed9406125c31b58f89");
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

	/**
	 * 登录
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping("/login")
	public JsonResult login(String username, String password) {
		JsonResult json = JsonResult.newInstance();
		Usertable usertable = usertableserice.Loginusertable(username);
		String userpassword = usertable.getPassword();
		String salt = usertable.getSalt();
		MD5Util md5 = new MD5Util(salt, "sha-256");
		String newpassword = md5.encode(password);
		if (userpassword.equals(newpassword)) {
			json.setDate(200);
			json.setMessage("登录成功");
			json.success();
		} else {
			json.setDate(500);
			json.setMessage("登录失败");
			json.failed("FAIL");
		}
		return json;
	}

	/**
	 * 修改密码
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("updatepassword")
	public JsonResult updatepassword(HttpServletRequest request) {
		JsonResult json = JsonResult.newInstance();
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String onepassword = request.getParameter("newpassword");
		String twopassword = request.getParameter("twopassword");
		Usertable idquery = usertableserice.IDQuery(id);
		String salt = UUID.randomUUID().toString();
		MD5Util md5 = new MD5Util(salt, "SHA-256");
		String saltpassword = md5.encode(password);
		String saltonepassword = md5.encode(onepassword);
		String salttwopassword = md5.encode(twopassword);
		if (saltpassword.equals(idquery.getPassword())) {
			if (saltonepassword.equals(salttwopassword)) {
				// 两次密码一致
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", id);
				map.put("salt", salt);
				map.put("salttwopassword", salttwopassword);
				int count = usertableserice.updatePassword(map);
				if (count > 0) {
					json.setDate(200);
					json.setMessage("修改成功");
					json.success();
				}
			} else {
				json.setDate(500);
				json.setMessage("两次密码不一致");
				json.failed("FAIL");
			}
		} else {
			json.setDate(500);
			json.setMessage("初始密码错误");
			json.failed("FAIL");
		}
		return json;
	}
}
