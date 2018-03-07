package com.ssm.api.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import com.ssm.api.bean.entity.UserLog;
import com.ssm.api.bean.entity.UserMoney;
import com.ssm.api.bean.request.User;


public interface UserDao {

	User getUserId(int userId);
	List<User> getUser();
	
	int insertUser(User user);
	int insertUserMoney(UserMoney userMoney);
	int insertUseLog(UserLog userLog);
	
	User userLogin(User user);
	
	List<User> stateGetUser(@Param("type")String type, @Param("state")int state);
}
