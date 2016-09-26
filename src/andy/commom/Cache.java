package andy.commom;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import andy.entity.Message;
import andy.entity.User;

/**
 * @author andy<andy_513@163.com>
 */
public final class Cache {
	
	/**
	 * 在线玩家缓存
	 */
	public static final ConcurrentMap<Object, User> session = new ConcurrentHashMap<Object, User>();
	
	/**
	 * 通信映射配置文件
	 */
	public static final ConcurrentHashMap<String, Message> message_map = new ConcurrentHashMap<String, Message>();

}
