package andy;

import java.util.Properties;
import java.util.concurrent.CountDownLatch;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import andy.biz.UserBiz;
import andy.commom.PropertiesUtil;
import andy.commom.SpringBeans;
import andy.entity.User;

/**
 * @author andy<andy_513@163.com>
 */
public class Test {

	private static final Logger log = LogManager.getLogger(Test.class);

	public static void main(String[] args) throws Exception {
		// log.debug(PropertiesUtil.getString("tafang.host"));
		UserBiz userBiz = SpringBeans.getBean(UserBiz.class);
		for (int i = 0; i < 10; i++) {
			int size = 4;
			final int a = i;
			final CountDownLatch cdl = new CountDownLatch(size);
			for (int x = 0; x < size; x++) {
				new Thread(() -> {
					try {
						cdl.countDown();
						userBiz.addUser(new User("emma" + a, "1"));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}).start();
			}
			cdl.await();
		}
	}

}
