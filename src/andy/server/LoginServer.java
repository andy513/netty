package andy.server;

import java.time.Instant;
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
 * @author andy<andy_513@163.com>
 */
public class LoginServer extends BaseServer {

	public Map<Integer, Builder> joinGame(ByteString byteString) {
		Map<Integer, Builder> map = new HashMap<Integer, Builder>();
		UserProto userProto = null;
		try {
			userProto = UserProto.parseFrom(byteString);
			User user = new User(userProto.getUsername(), userProto.getPassword());
			log.debug(System.nanoTime() + "\t" + Instant.now().getEpochSecond() + "\t" + JSONObject.toJSONString(user));
			userBiz.addUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put(100, userProto.toBuilder());
		return map;
	}

	public Map<Integer, Builder> login(ByteString byteString) {
		Map<Integer, Builder> map = new HashMap<Integer, Builder>();
		User user = null;
		try {
			LoginProto loginProto = LoginProto.parseFrom(byteString);
			UserProto userProto = loginProto.getUserProto();
			String userName = userProto.getUsername();
			String password = userProto.getPassword();
			user = userBiz.selUser(userName, password);
			if (user == null) {
				log.debug("用户名或密码错误!");
			}
			Cache.session.put(user.getId(), user);
			UserProto.Builder user_proto_builder = UserProto.newBuilder();
			user_proto_builder.setPassword("password");
			user_proto_builder.setUsername("userName");
			map.put(100, user_proto_builder);
		} catch (InvalidProtocolBufferException e) {
			log.error("玩家查询异常", e);
		} catch (Exception e) {
			log.error("玩家查询异常", e);
		}
		return map;
	}

	public String exit(JSONObject jsonObject) {
		log.debug(jsonObject);
		int i = Integer.parseInt("a");
		return "退出成功";
	}

}
