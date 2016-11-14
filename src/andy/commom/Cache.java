package andy.commom;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import andy.entity.Message;
import andy.entity.User;

/**
 * @author andy<andy_513@163.com>
 */
public final class Cache {
	
	/**
	 * 在线玩家缓存
	 */
	public static final Map<Object, User> session = new ConcurrentHashMap<>();
	
	/**
	 * 通信映射配置文件
	 */
	public static final Map<String, Message> message_map = new ConcurrentHashMap<>();

}
