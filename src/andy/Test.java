package andy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import com.alibaba.fastjson.JSONObject;

import andy.commom.XMemcache;
import andy.entity.User;

/**
 * @author Andy<andy_513@163.com>
 */
public class Test {

	public static void main(String[] args) {
//		extracted();
		System.out.println(JSONObject.toJSONString(XMemcache.get("t")));
	}

	private static void extracted() {
		XMemcache.flushAll();
		List<User> users = new ArrayList<User>();
		users.add(new User("andy1", "andy1"));
		users.add(new User("andy2", "andy2"));
		XMemcache.add("t", users);
		List<User> lists = XMemcache.get("t");
		List<User> lists1 = XMemcache.get("t");
		User user1 = lists.get(0);
		user1.setPwd("1");
		User user2 = lists1.get(1);
		user2.setPwd("2");
		System.out.println(JSONObject.toJSONString(lists1));
		System.out.println(JSONObject.toJSONString(lists));
		CountDownLatch countDownLatch = new CountDownLatch(2);
		new Thread(() -> {
			try {
				countDownLatch.countDown();
				XMemcache.modifyT(user1, "t");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
		new Thread(() -> {
			try {
				countDownLatch.countDown();
				XMemcache.modifyT(user2, "t");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
