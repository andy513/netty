package andy.test;

import java.util.ArrayList;
import java.util.List;

import com.google.code.yanf4j.util.ConcurrentHashSet;

/**
 * @author andy<andy_513@163.com>
 */
public class TestList {
	
	private static final int size = 10000 * 10;
	
	public static void main(String[] args) {
		List<Integer> lists = new ArrayList<Integer>();
		for(int i = 0;i<size;i++){
			lists.add(i);
			lists.add(i);
		}
		long time = System.nanoTime();
		ConcurrentHashSet<Integer> sets = new ConcurrentHashSet<Integer>();
		for (int i = 0; i < lists.size(); i++) {
			sets.add(lists.get(i));
			System.out.print("");
		}
		System.out.println(System.nanoTime() - time);
		
		time = System.nanoTime();
		lists.parallelStream().distinct().forEach(list -> System.out.print(""));
		System.out.println(System.nanoTime() - time);
	}

}
