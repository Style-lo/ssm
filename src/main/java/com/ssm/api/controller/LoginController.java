package com.ssm.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssm.api.bean.request.User;
import com.ssm.api.service.UserService;


@Controller
//@RequestMapping("api/v1/login")
public class LoginController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="userlogin")
	public String getUserId(User user){
		User userLogin = userService.userLogin(user);
		if (userLogin == null) {
			return "您输入的账号密码有误！";
		}
		return "/loginOK";
		
	}
	
	
}
