package andy.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import andy.dao.UserDao;
import andy.dao.test.DBContextHolder;
import andy.entity.User;
import andy.mapper.UserMapper;

@Repository
public class UserDaoImpl implements UserDao{
	
	@Resource
	UserMapper userMapper;

	@Override
	public int addUser(User user) {
		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_RW);
		return userMapper.addUser(user);
	}

	@Override
	public User selUser(String uname) {
//		DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
		return userMapper.selUser(uname);
	}

}
