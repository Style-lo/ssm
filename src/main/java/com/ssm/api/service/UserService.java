package com.ssm.api.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ssm.api.bean.entity.UserLog;
import com.ssm.api.bean.entity.UserMoney;
import com.ssm.api.bean.request.User;


public interface UserService {

	User getUserId(int userId);
	List<User> getUser();
	
	int insertUser(User user, UserLog userLog, UserMoney userMoney);

	User userLogin(User user);
	
	List<User> stateGetUser(String type, int state);
	String readExcelFile(MultipartFile file);
}