package andy.test;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

/**
 * @author Andy<andy_513@163.com>
 */
public class LocalDateTest {
	
	public static void main(String[] args) {
		LocalDate localDate = LocalDate.parse("2016-03-31");
		//获取本月第一天
		localDate = localDate.with(TemporalAdjusters.firstDayOfMonth());
		System.out.println(localDate);
		//获取本月第二天
		localDate = localDate.withDayOfMonth(2);
		System.out.println(localDate);
		//获取本月最后一天
		localDate = localDate.with(TemporalAdjusters.lastDayOfMonth());
		System.out.println(localDate);
	}

}
