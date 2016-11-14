package andy.biz.impl;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

	private static Lock lock = new ReentrantLock();

	@Autowired
	UserDao userDao;

	@Override
	public int addUser(User user) throws Exception {
		String uname = user.getUname();
		Lock l = lock;
		l.lock();
		try {
			User old_user = userDao.selUser(uname);
			if (old_user != null) {
				log.debug(uname + ":\t玩家数据已存在");
				return 0;
			}
			return userDao.addUser(user);
		} finally {
			l.unlock();
		}
	}

	private static final Logger log = LogManager.getLogger(UserBizImpl.class);

	@Override
	public User selUser(String uname, String pwd) throws Exception {
		User user = userDao.selUser(uname);
		if (user != null && user.getPwd().equals(pwd)) {
			return user;
		}
		return null;
	}

	@Override
	public int modifyUser(User user) throws Exception {
		int result = userDao.modifyUser(user);
		return result;
	}

	public static void main(String[] args) {
		/*
		 * UserBiz userBiz = SpringBeans.getBean(UserBizImpl.class); String name
		 * = "hello7"; User user = new User(name, "world7"); int result =
		 * userBiz.addUser(user); System.out.println(result + "\t" +
		 * JSONObject.toJSONString(user)); if (result == 0) { user =
		 * userBiz.selUser(name, user.getPwd()); } result =
		 * userBiz.modifyUser(user); System.out.println(result + "\t" +
		 * JSONObject.toJSONString(user));
		 */
		User user = new User();
		System.out.println(user.isUpdate());
		user.setUname("a");
		System.out.println(user.isUpdate());
	}

	@Override
	public int getUserId() throws Exception {
		return userDao.getUserId();
	}

}
