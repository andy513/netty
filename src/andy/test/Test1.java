package andy.test;

import java.util.concurrent.ThreadLocalRandom;

import com.alibaba.fastjson.JSONObject;

import andy.biz.UserBiz;
import andy.biz.impl.UserBizImpl;
import andy.commom.SpringBeans;
import andy.entity.User;

/**
 * @author Andy<andy_513@163.com>
 */
public class Test1 {
	public static void main(String[] args) {
		UserBiz userBiz = SpringBeans.getBean(UserBizImpl.class);
		int num = ThreadLocalRandom.current().nextInt(1000);
		User user = new User("a" + num, "test");
		int result = userBiz.addUser(user);
		System.out.println(result);
		User new_user = userBiz.selUser(user.getUname(), user.getPwd());
		System.out.println(JSONObject.toJSONString(new_user));
	}

}