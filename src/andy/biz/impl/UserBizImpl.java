package andy.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import andy.biz.UserBiz;
import andy.dao.UserDao;
import andy.entity.User;

/**
 * @author andy<andy_513@163.com>
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

	@Override
	public int modifyUser(User user) {
		int result = userDao.modifyUser(user);
		return result;
	}

	public static void main(String[] args) {
		/*UserBiz userBiz = SpringBeans.getBean(UserBizImpl.class);
		String name = "hello7";
		User user = new User(name, "world7");
		int result = userBiz.addUser(user);
		System.out.println(result + "\t" + JSONObject.toJSONString(user));
		if (result == 0) {
			user = userBiz.selUser(name, user.getPwd());
		}
		result = userBiz.modifyUser(user);
		System.out.println(result + "\t" + JSONObject.toJSONString(user));*/
		User user = new User();
		System.out.println(user.isUpdate());
		user.setUname("a");
		System.out.println(user.isUpdate());
	}

}
