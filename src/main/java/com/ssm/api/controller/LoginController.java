package com.ssm.api.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssm.api.bean.request.User;
import com.ssm.api.service.UserService;


@Controller
//@RequestMapping("api/v1/login")
public class LoginController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="userlogin")
	@ResponseBody
	public String getUserId( User user, HttpSession session){
		User userLogin = userService.userLogin(user);
		if (userLogin == null) {
			return null;
		}
		session.setAttribute("user", user);
		return "/loginOK";
		
	}
	@RequestMapping(value="userloginOut")
	public String userloginOut(HttpSession session){
		session.invalidate();
		return "/login";
		
	}
	
	
}
