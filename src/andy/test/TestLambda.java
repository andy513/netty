package andy.test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSONObject;

/**
 * @author andy<andy_513@163.com>
 */
public class TestLambda {
	
	public static void main(String[] args) {
		List<String> lists = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			lists.add("" + i);
			lists.add("" + i);
		}
		System.out.println(JSONObject.toJSONString(lists));
		List<Integer> is = lists.parallelStream().map(e -> new Integer(e)).distinct().collect(Collectors.toList());
		System.out.println(JSONObject.toJSONString(is));
	}

}
