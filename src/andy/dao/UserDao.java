package andy.dao;

import andy.entity.User;

/**
 * @author andy<andy_513@163.com>
 *
 */
public interface UserDao {

	int addUser(User user);
	
	User selUser(String uname);
}
