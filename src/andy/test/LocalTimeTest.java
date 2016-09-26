package andy.test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author andy<andy_513@163.com>
 */
public class LocalTimeTest {

	private static final int SIZE = 29999999;
	private static final List<LocalTime> localTime = new ArrayList<LocalTime>();

	public static void main(String[] args) {
		long time = System.nanoTime();
		List<LocalTime> lists = null;
		for (int i = 0; i < SIZE; i++) {
			if (i / 3333 == 0) {
				lists = new ArrayList<LocalTime>();
				localTime.addAll(lists);
			}
			lists.add(LocalTime.now());
		}
		System.out.println((System.nanoTime() - time) / 1000000);
	}
}
