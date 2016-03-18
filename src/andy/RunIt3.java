package andy;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Andy<andy_513@163.com>
 */
class RunIt3 implements Runnable {
	private static Lock lock = new ReentrantLock();

	public void run() {
		try {
			// ---------------------------------a
			 lock.lock();
//			lock.lockInterruptibly();

			System.out.println(Thread.currentThread().getName() + " running");
			TimeUnit.SECONDS.sleep(3);
			System.out.println(Thread.currentThread().getName() + " finished");
		} catch (InterruptedException e) {
			System.out.println(Thread.currentThread().getName() + " interrupted");
		} finally {
			lock.unlock();
		}

	}
}
