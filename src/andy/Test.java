package andy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.alibaba.fastjson.JSONObject;

import andy.commom.XMemcache;
import andy.entity.User;
import net.rubyeye.xmemcached.CASOperation;

/**
 * @author Andy<andy_513@163.com>
 */
public class Test {

	private static final Logger log = LogManager.getLogger(Test.class);
	private static final List<User> users = new ArrayList<User>();

	static {
		for (int i = 0; i < 999; i++) {
			users.add(new User("andy" + i, "andy"));
		}
	}

	public static void main(String[] args) throws Exception {
		try {
			test();
			// extracted();
		} finally {
			XMemcache.XMEMCACHED.shutdown();
		}
	}

	private static final void test() throws Exception {
		XMemcache.flushAll();
		List<User> users = new ArrayList<User>();
		new Thread(() -> {
			users.add(new User("test", "test"));
			XMemcache.add("a", users);
		}).start();
		System.out.println(JSONObject.toJSONString(XMemcache.get("a")));
		new Thread(() -> {
			List<User> user = XMemcache.get("a");
			user.get(0).setPwd("1");
			try {
				XMemcache.modifyT(user, "a");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
		System.out.println(JSONObject.toJSONString(XMemcache.get("a")));
		new Thread(() -> {
			List<User> user1 = XMemcache.get("a");
			user1.get(0).setPwd("2");
			try {
				XMemcache.modifyT(user1, "a");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
		for (int i = 20; i > 0; i--) {
			System.out.println(i);
			TimeUnit.SECONDS.sleep(1);
		}
		System.out.println(JSONObject.toJSONString(XMemcache.get("a")));
	}

	private static CyclicBarrier barrier = new CyclicBarrier(3);
	private static final AtomicInteger ai1 = new AtomicInteger();
	private static final AtomicInteger ai2 = new AtomicInteger();
	private static final AtomicInteger ai3 = new AtomicInteger();

	private static void extracted() throws Exception {
		XMemcache.flushAll();
		XMemcache.add("t", users);
		int size = users.size() / 3;
		CountDownLatch countDownLatch = new CountDownLatch(size);
		System.out.println(size);
		for (int i = 0; i < users.size(); i++) {
			List<User> lists = new ArrayList<User>();
			User user = users.get(i);
			user.setPwd("" + i);
			lists.add(user);
			new Thread(() -> {
				try {
					countDownLatch.countDown();
					// XMemcache.modifyT(user, "t");
					XMemcache.XMEMCACHED.cas("t", 0, new CASOperation<List<User>>() {
						@Override
						public int getMaxTries() {
							return Integer.MAX_VALUE;
						}

						@Override
						public List<User> getNewValue(long currentCAS, List<User> currentValue) {
							int max_size = currentValue.size();
							int min_size = lists.size();
							for (int i = 0; i < max_size; i++) {
								User ti = currentValue.get(i);
								for (int j = 0; j < min_size; j++) {
									User tj = lists.get(j);
									if (ti.equals(tj)) {
										currentValue.set(i, tj);
										break;
									}
								}
							}
							return currentValue;
						}
					});
					// barrier.await();
					// TimeUnit.SECONDS.sleep(10);
					// System.out.println("ai2:\t"+ai2.getAndIncrement());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}).start();
			if (i != 0 && (i + 1) % size == 0) {
				System.out.println(i);
				TimeUnit.SECONDS.sleep(10);
				countDownLatch.await();
			}
		}
		System.out.println(JSONObject.toJSONString(XMemcache.get("t")));
	}
}
