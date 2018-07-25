package com.luck.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	/**
	 * 测试
	 * @return
	 */
	@RequestMapping("login")
	public String leeluck() {
		return "hello,Spring boot";
	}

}
