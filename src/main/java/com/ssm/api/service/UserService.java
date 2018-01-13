package com.ssm.api.service;

import java.util.List;

import com.ssm.api.bean.entity.UserLog;
import com.ssm.api.bean.entity.UserMoney;
import com.ssm.api.bean.request.User;


public interface UserService {

	User getUserId(int userId);
	List<User> getUser();
	
	int insertUser(User user, UserLog userLog, UserMoney userMoney);

}