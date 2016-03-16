package andy.server;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message.Builder;

import andy.entity.UserProtos.UserProto;

/**
 * @author Andy<andy_513@163.com>
 */
public class LoginServer extends BaseServer {

	public Map<Integer, Builder> login(UserProto userProto) {
		Map<Integer, Builder> map = new HashMap<Integer, Builder>();
		logger.info(userProto);
		UserProto.Builder user_proto_builder = UserProto.newBuilder();
		user_proto_builder.setPassword("password");
		user_proto_builder.setUsesrname("userName");
		map.put(100, user_proto_builder);
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
