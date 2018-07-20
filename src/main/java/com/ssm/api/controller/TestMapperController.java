package com.ssm.api.controller;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssm.api.bean.request.User;
import com.ssm.api.dao.TestMapperDao;

@RestController
public class TestMapperController {

	@Autowired
	TestMapperDao testMapperDao;
	
	/**
	 * 测试统一异常处理，测试通用mapper
	 * @return
	 */
	@RequestMapping(value="testMapper", produces="text/html; charset=UTF-8")
	public String test(){
		User user = new User();
		user.setId(2);
		System.out.println("controller报错前");
		if(user.getId()==2) throw new RuntimeException("运行时异常了");
		System.out.println("controller报错后");
		User selectByPrimaryKey = testMapperDao.selectByPrimaryKey(2);
		System.out.println(selectByPrimaryKey.getUser_name());
		return "ok";
	}
}
