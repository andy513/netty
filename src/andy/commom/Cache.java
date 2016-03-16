package andy.commom;

import java.util.concurrent.ConcurrentHashMap;

import andy.entity.Message;

/**
 * @author Andy<andy_513@163.com>
 */
public final class Cache {
	
	/**
	 * 通信映射配置文件
	 */
	public static final ConcurrentHashMap<String, Message> message_map = new ConcurrentHashMap<String, Message>();

}
