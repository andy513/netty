package andy.server;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.google.protobuf.Message.Builder;

import andy.entity.UserProtos.UserProto;

/**
 * @author Andy<andy_513@163.com>
 */
public class LoginServer extends BaseServer {

	public Map<Integer,Builder> login(JSONObject jsonObject) {
		Map<Integer,Builder> map = new HashMap<Integer,Builder>();
		UserProto.Builder userProto = UserProto.newBuilder();
		userProto.setPassword("password");
		userProto.setUsesrname("userName");
		map.put(100, userProto);
		return map;
	}

	public String exit(JSONObject jsonObject) {
		logger.debug(jsonObject);
		int i = Integer.parseInt("a");
		return "退出成功";
	}

	public String t1(JSONObject jsonObject) {
		logger.debug(jsonObject);
		int i = Integer.parseInt("a");
		return "退出成功";
	}

	public String t2(JSONObject jsonObject) {
		logger.debug(jsonObject);
		int i = Integer.parseInt("a");
		return "退出成功";
	}

}
