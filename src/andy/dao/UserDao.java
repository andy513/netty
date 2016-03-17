package andy.dao;

import andy.entity.User;

/**
 * @author Andy<andy_513@163.com>
 *
 */
public interface UserDao {

	int addUser(User user);
	
	User selUser(String uname);
}
