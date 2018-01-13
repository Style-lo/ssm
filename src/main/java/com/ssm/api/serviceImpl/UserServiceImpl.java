package com.ssm.api.serviceImpl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.api.bean.entity.UserLog;
import com.ssm.api.bean.entity.UserMoney;
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

	@Transactional
	@Override
	public int insertUser(User user, UserLog userLog, UserMoney userMoney) {
		try {
			userDao.insertUser(user);
			userLog.setUser_id(user.getId());
			userLog.setDate(new Date());
			userMoney.setUser_id(user.getId());
			userDao.insertUseLog(userLog);
			userDao.insertUserMoney(userMoney);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return 0;
	}
	/*public int test(User user, UserLog userLog, UserMoney userMoney) {
		DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
	      TransactionStatus status = transactionManager.getTransaction(definition);transactionManager.commit(status);
		userDao.insertUser(user);
		userLog.setUser_id(user.getId());
		userLog.setDate(new Date());
		userMoney.setUser_id(user.getId());
		userDao.insertUseLog(userLog);
		userDao.insertUserMoney(userMoney);
		return 0;
	}*/

	

}
