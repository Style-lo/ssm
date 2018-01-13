package com.ssm.api.controller;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssm.api.bean.request.User;
import com.ssm.api.dao.TestMapperDao;

@Controller
public class TestMapperController {

	@Autowired
	TestMapperDao testMapperDao;
	
	@RequestMapping("testMapper")
	public String test(){
		User user = new User();
		user.setId(2);
		User selectByPrimaryKey = testMapperDao.selectByPrimaryKey(user);
		System.out.println(selectByPrimaryKey.getUser_name());
		return "ok";
	}
}
