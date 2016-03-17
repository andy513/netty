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
		return userMapper.addUser(user);
	}

	@Override
	public User selUser(String uname) {
		return userMapper.selUser(uname);
	}

}
