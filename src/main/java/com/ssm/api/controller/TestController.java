package com.ssm.api.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssm.api.bean.request.User;
import com.ssm.api.service.UserService;


@Controller
@RequestMapping("api/v1/test")
public class TestController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="ok")
	public String userloginOut(HttpSession session){
		System.out.println("登陆成功页面点击跳转");
		return "登陆成功页面点击跳转";
		
	}
	
	
}
