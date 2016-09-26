package andy.biz;

import andy.entity.User;

/**
 * @author andy<andy_513@163.com>
 *
 */
public interface UserBiz {
	
	int addUser(User user);

	User selUser(String uname,String pwd);
}
