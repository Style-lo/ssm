package com.ssm.api.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.formula.functions.T;

import tk.mybatis.mapper.common.Mapper;

import com.ssm.api.bean.entity.UserLog;
import com.ssm.api.bean.entity.UserMoney;
import com.ssm.api.bean.request.User;


public interface UserDao extends Mapper<T>{

	User getUserId(int userId);
	List<User> getUser();
	
	int insertUser(User user);
	int insertUserMoney(UserMoney userMoney);
	int insertUseLog(UserLog userLog);
	
	User userLogin(User user);
	
	List<User> stateGetUser(@Param("type")String type, @Param("state")int state);
}
