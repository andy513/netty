package andy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import andy.dao.DataSource;
import andy.entity.User;

/**
 * @author andy<andy_513@163.com>
 *
 */
public interface UserMapper {
	
	int getUserId();
	
	@DataSource("master")
	int addUsers(List<User> users);
	
	@DataSource("master")
	int modifyUsers(List<User> users);
	
	@DataSource("master")
	int addUser(User user);
	
	@DataSource("master")
	int modifyUser(User user);
	
	@DataSource("slave")
	User selUser(User user);
	
	@DataSource("slave")
	User selUser(@Param(value="uname")String uname);
	
	@DataSource("slave")
	List<User> selUsers();

}
