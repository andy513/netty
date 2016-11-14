package andy.dao.impl;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import andy.dao.UserDao;
import andy.entity.User;
import andy.mapper.UserMapper;

@Repository
public class UserDaoImpl implements UserDao{
	
	@Resource
	UserMapper userMapper;

	@Override
	public int addUser(User user) throws InterruptedException {
//		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_RW);
		TimeUnit.MILLISECONDS.sleep(50);
		return userMapper.addUser(user);
	}

	@Override
	public int modifyUser(User user) {
		int result = 0;
		if (user.isUpdate()) {
			result = userMapper.modifyUser(user);
			user.setUpdate(false);
		}
		return result;
	}

	@Override
	public User selUser(String uname) {
		// DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		User user = userMapper.selUser(uname);
		return user;
	}

	@Override
	public int getUserId() throws Exception {
		return userMapper.getUserId();
	}

}
