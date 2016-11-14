package andy.biz;

import andy.entity.User;

/**
 * @author andy<andy_513@163.com>
 *
 */
public interface UserBiz {
	
	int getUserId() throws Exception;

	int addUser(User user) throws Exception;

	int modifyUser(User user) throws Exception;

	User selUser(String uname, String pwd) throws Exception;
}
