package andy;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Andy<andy_513@163.com>
 */
public class TestDeadLock implements Runnable {
	public int flag = 1;
	private Object o1;
	private Object o2;

	public TestDeadLock(Object o1, Object o2) {
		this.o1 = o1;
		this.o2 = o2;
	}

	public Object getO1() {
		return o1;
	}

	public void setO1(Object o1) {
		this.o1 = o1;
	}

	public Object getO2() {
		return o2;
	}

	public void setO2(Object o2) {
		this.o2 = o2;
	}
	public static void main(String[] argv) {
		Object object1 = new Object();
		Object object2 = new Object();
		TestDeadLock td1 = new TestDeadLock(object1,object2);
		TestDeadLock td2 = new TestDeadLock(object1,object2);
		td1.flag = 1;
		td2.flag = 0;
		Thread t1 = new Thread(td1);
		Thread t2 = new Thread(td2);
		t1.start();
		t2.start();
		t1.interrupt();
	}
	private static final Lock lock = new ReentrantLock();
	
	public void run() {
		try {
			Thread.sleep(flag * 100);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		System.out.println("flag = " + flag);
		if (flag == 1) {
			try {
				lock.lockInterruptibly();
				synchronized (o1) {
					Thread.sleep(500);
					synchronized (o2) {
						System.out.println("1");
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}
		if (flag == 0) {
			try {
				lock.lockInterruptibly();
				synchronized (o2) {
					Thread.sleep(5000);
					synchronized (o1) {
						System.out.println("0");
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}
	}
}