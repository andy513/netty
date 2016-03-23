package andy.commom;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Andy<andy_513@163.com>
 */
public class GlobalUtil {

	private static final AtomicInteger ai = new AtomicInteger();

	private static final int ROTATION = 999;

	public static final String UUID() {
		if (ai.intValue() >= ROTATION) {
			ai.set(0);
		}
		return String.format("%1d%2$03d", System.currentTimeMillis(), ai.getAndIncrement());
	}

	private static final Set<String> sets = new HashSet<String>();

	private static final ExecutorService es = Executors.newCachedThreadPool();
	private static final int size = 10000;

	public static void main(String[] args) {
		// System.out.println(New_UID());
		extracted();
	}

	private static void extracted() {
		CountDownLatch countDownLatch = new CountDownLatch(size);
		try {
			for (int i = 0; i < size; i++) {
				Future<Integer> future = es.submit(new Callable<Integer>() {
					@Override
					public Integer call() throws Exception {
						countDownLatch.countDown();
						for (int k = 0; k < 100; k++) {
							sets.add(UUID());
						}
						return sets.size();
					}
				});
				int num = future.get();
				if (ai.intValue() >= 999) {
					System.out.println(i + "\t" + num + "\t" + ai.intValue());
				}
			}
			countDownLatch.await();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		} finally {
			es.shutdown();
		}
	}

}
