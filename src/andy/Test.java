package andy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.alibaba.fastjson.JSONObject;

import andy.commom.XMemcache;
import andy.entity.User;

/**
 * @author Andy<andy_513@163.com>
 */
public class Test {

	private static final Logger log = LogManager.getLogger(Test.class);
	private static final List<User> lists = new ArrayList<User>();

	static {
		for (int i = 0; i < 99; i++) {
			lists.add(new User("andy" + i, "andy"));
		}
	}

	public static void main(String[] args) throws Exception {
		try {
			/*XMemcache.flushAll();
			extracted();
			test();*/
			log.debug(JSONObject.toJSONString(XMemcache.get("t")));
		} finally {
//			XMemcache.XMEMCACHED.shutdown();
		}
	}

	private static final void test() throws Exception {
		/*List<User> users = new ArrayList<User>();
		new Thread(() -> {
			users.add(new User("test", "test"));
			XMemcache.add("a", users);
		}).start();
		log.debug(JSONObject.toJSONString(XMemcache.get("a")));*/
		CountDownLatch countDownLatch = new CountDownLatch(2);
		new Thread(() -> {
			countDownLatch.countDown();
			List<User> user = XMemcache.get("t");
			User u = user.get(0);
			u.setPwd("1");
			u.setAge(u.getAge() - 2);
			try {
				XMemcache.modifyT(u, "t");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
		log.debug(JSONObject.toJSONString(XMemcache.get("t")));
		new Thread(() -> {
			countDownLatch.countDown();
			List<User> user1 = XMemcache.get("t");
			User u = user1.get(0);
			u.setPwd("2");
			u.setAge(u.getAge() - 1);
			try {
				XMemcache.modifyT(u, "t");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
		countDownLatch.await();
		log.debug(JSONObject.toJSONString(XMemcache.get("t")));
	}

	private static CyclicBarrier barrier = new CyclicBarrier(3);
	private static final AtomicInteger ai1 = new AtomicInteger();
	private static final AtomicInteger ai2 = new AtomicInteger();
	private static final AtomicInteger ai3 = new AtomicInteger();

	private static void extracted() throws Exception {
		XMemcache.add("t", lists);
		int size = lists.size() / 3;
		CountDownLatch countDownLatch = new CountDownLatch(size);
		for (int i = 0; i < lists.size(); i++) {
			List<User> users = new ArrayList<User>();
			User user = lists.get(i);
			user.setPwd("" + i);
			users.add(user);
			new Thread(() -> {
				try {
					countDownLatch.countDown();
					 XMemcache.modifyT(user, "t");
					/*XMemcache.XMEMCACHED.cas("t", 0, new CASOperation<List<User>>() {
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
					});*/
					// barrier.await();
					// TimeUnit.SECONDS.sleep(10);
					// System.out.println("ai2:\t"+ai2.getAndIncrement());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}).start();
			if (i != 0 && (i + 1) % size == 0) {
				System.out.println(i);
				countDownLatch.await();
			}
		}
		log.debug(JSONObject.toJSONString(XMemcache.get("t")));
	}
}
