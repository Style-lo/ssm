package com.ssm.api.dao;

import java.util.List;

import com.ssm.api.bean.request.User;


public interface UserDao {

	User getUserId(int userId);
	List<User> getUser();
}
