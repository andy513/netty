package andy.dao;

import andy.entity.User;

/**
 * @author andy<andy_513@163.com>
 *
 */
public interface UserDao {

	int addUser(User user) throws Exception;

	int modifyUser(User user) throws Exception;

	User selUser(String uname) throws Exception;
}
