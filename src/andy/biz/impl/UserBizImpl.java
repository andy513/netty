package andy.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import andy.biz.UserBiz;
import andy.dao.UserDao;
import andy.entity.User;

/**
 * @author Andy<andy_513@163.com>
 *
 */
@Service
public class UserBizImpl implements UserBiz {

	@Autowired
	UserDao userDao;

	@Override
	public int addUser(User user) {
		User old_user = userDao.selUser(user.getUname());
		if (old_user == null) {
			return userDao.addUser(user);
		}
		return 0;
	}

	@Override
	public User selUser(String uname, String pwd) {
		User user = userDao.selUser(uname);
		if (user != null && user.getPwd().equals(pwd)) {
			return user;
		}
		return null;
	}

}
