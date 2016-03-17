package andy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import andy.entity.User;

/**
 * @author Andy<andy_513@163.com>
 *
 */
public interface UserMapper {
	
	int addUsers(List<User> users);
	
	int modifyUsers(List<User> users);
	
	int addUser(User user);
	
	int modifyUser(User user);
	
	User selUser(User user);
	
	User selUser(@Param(value="uname")String uname);
	
	List<User> selUsers();

}
