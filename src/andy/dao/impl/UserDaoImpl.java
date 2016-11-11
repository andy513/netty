package andy.dao.impl;

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
	public int addUser(User user) {
//		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_RW);
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

}
