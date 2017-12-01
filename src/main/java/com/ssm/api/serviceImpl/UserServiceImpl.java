package com.ssm.api.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.api.bean.request.User;
import com.ssm.api.dao.UserDao;
import com.ssm.api.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	public User getUserId(int userId) {
		
		return userDao.getUserId(userId);
	}

	public List<User> getUser() {
		
		return userDao.getUser();
	}

}
