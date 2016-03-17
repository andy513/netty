package andy.server;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message.Builder;

import andy.commom.Cache;
import andy.entity.User;
import andy.entity.proto.UserProtos.LoginProto;
import andy.entity.proto.UserProtos.UserProto;

/**
 * @author Andy<andy_513@163.com>
 */
public class LoginServer extends BaseServer {
	
	public Map<Integer, Builder> joinGame(ByteString byteString) {
		Map<Integer, Builder> map = new HashMap<Integer, Builder>();
		UserProto userProto = null;
		try {
			userProto = UserProto.parseFrom(byteString);
		} catch (InvalidProtocolBufferException e) {
			e.printStackTrace();
		}
		User user = new User(userProto.getUsername(),userProto.getPassword());
		userBiz.addUser(user);
		map.put(100, userProto.toBuilder());
		return map;
	}
	
	public Map<Integer, Builder> login(ByteString byteString) {
		Map<Integer, Builder> map = new HashMap<Integer, Builder>();
		try {
			LoginProto loginProto = LoginProto.parseFrom(byteString);
			UserProto userProto = loginProto.getUserProto();
			String userName = userProto.getUsername();
			String password = userProto.getPassword();
			User user = userBiz.selUser(userName, password);
			if(user == null){
				logger.debug("用户名或密码错误!");
			}
			Cache.session.put(user.getId(), user);
		} catch (InvalidProtocolBufferException e) {
			e.printStackTrace();
		}
		UserProto.Builder user_proto_builder = UserProto.newBuilder();
		user_proto_builder.setPassword("password");
		user_proto_builder.setUsername("userName");
		map.put(100, user_proto_builder);
		return map;
	}

	public String exit(JSONObject jsonObject) {
		logger.debug(jsonObject);
		int i = Integer.parseInt("a");
		return "退出成功";
	}

}
