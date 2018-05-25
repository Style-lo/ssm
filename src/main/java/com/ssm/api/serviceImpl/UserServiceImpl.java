package com.ssm.api.serviceImpl;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ssm.api.bean.entity.UserLog;
import com.ssm.api.bean.entity.UserMoney;
import com.ssm.api.bean.request.User;
import com.ssm.api.dao.UserDao;
import com.ssm.api.service.UserService;
import com.ssm.api.utils.ReadExcel;

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
			throw new RuntimeException();//手动添加运行时异常报错，事物回滚(运行时异常才会回滚事物)
		} catch (Exception e) {
			e.printStackTrace();
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

	@Override
	public User userLogin(User user) {
		return userDao.userLogin(user);
	}

	@Override
	public List<User> stateGetUser(String type, int state) {
		
		return userDao.stateGetUser(type, state);
	}

	@Override
	public String readExcelFile(MultipartFile file) {
		String result ="";  
        //创建处理EXCEL的类  
        ReadExcel readExcel=new ReadExcel();  
        //解析excel，获取上传的事件单  
        List<User> useList = readExcel.getExcelInfo(file);  
        //至此已经将excel中的数据转换到list里面了,接下来就可以操作list,可以进行保存到数据库,或者其他操作,  
        //和你具体业务有关,这里不做具体的示范  
        if(useList != null && !useList.isEmpty()){  
        	int excelAddUser = userDao.excelAddUser(useList);
        	result=excelAddUser>0 ? "插入成功":"插入失败";
        }else{  
            result = "上传失败";  
        }  
        return result;  
	}

	/** 
     * 批量增加操作 
     * @param users 
     */  
    public static void ss(List<User> users){  
          
    }  
	

}
