package andy.commom;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.code.yanf4j.util.ConcurrentHashSet;

/**
 * @author Andy<andy_513@163.com>
 */
public class GlobalUtil {

	private static final AtomicInteger ai = new AtomicInteger();

	private static final int ROTATION = 1;

	public static final String UUID() {
		if (ai.intValue() >= ROTATION) {
			ai.set(0);
		}
		return String.format("%1d%2$04d", System.currentTimeMillis(), ai.getAndIncrement());
	}

	private static final ConcurrentHashSet<String> concurrent_sets = new ConcurrentHashSet<String>();
	private static final ConcurrentHashSet<String> sets = new ConcurrentHashSet<String>();

	private static final ExecutorService es = Executors.newCachedThreadPool();
	private static final int size = 1;

	public static void main(String[] args) {
//		 System.out.println(UUID());
		extracted();
	}

	private static void extracted() {
		try {
			for (int i = 0; i < size; i++) {
				Future<Integer> future = es.submit(new Callable<Integer>() {
					@Override
					public Integer call() throws Exception {
						for (int k = 0; k < 100; k++) {
							sets.add(UUID());
						}
						Thread.sleep(5000);
						return sets.size();
					}
				});
				int num = future.get();
				if (ai.intValue() == ROTATION) {
					System.out.println(i + "\t" + num + "\t" + ai.intValue());
				}
			}
			System.out.println(sets.size() + "\t" + ai.intValue());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		} finally {
			es.shutdown();
		}
	}

}
