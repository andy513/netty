package andy.commom;

import java.util.concurrent.TimeUnit;

import com.alibaba.fastjson.JSONObject;

import redis.clients.jedis.ShardedJedis;

/**
 * @author Andy<andy_513@163.com>
 */
public final class RedisStringCache {
	
	public static final ShardedJedis shardJedis = JedisDataSourceImpl.getRedisClient();
	
	/**
	 * 永久保存,有相同的缓存键不添加
	 * 
	 * @param key				缓存键
	 * @param object			缓存值
	 * @return					成功返回1,失败返回0
	 */
	public static final long add(String key, Object object) {
		return add(key, object, 0);
	}
	
	/**
	 * 
	 * 根据设置的时间保存时长,有相同的缓存键不添加
	 * 
	 * @param key				缓存键
	 * @param object			缓存值
	 * @param second			缓存时间戳(单位:秒,小于或等于0永久保存)
	 * @return
	 */
	public static final long add(String key, Object object,int second) {
		long result = shardJedis.setnx(key, JSONObject.toJSONString(object));
		if (second > 0 && result > 0)
			shardJedis.expire(key, second);
		return result;
	}
	
	/**
	 * 永久保存,有相同的缓存键覆盖添加
	 * 
	 * @param key
	 * @param object
	 * @return
	 */
	public static final String set(String key, Object object) {
		return shardJedis.set(key, JSONObject.toJSONString(object));
	}
	
	/**
	 * 
	 * 根据设置的时间保存时长,有相同的缓存键覆盖添加
	 * 
	 * @param key
	 * @param object
	 * @param second
	 * @return
	 */
	public static final String set(String key, Object object,int second) {
		return shardJedis.setex(key, second, JSONObject.toJSONString(object));
	}
	
	/**
	 * 在已有的缓存数据中追加值
	 * 
	 * @param key
	 * @param string
	 * @return
	 */
	public static final long append(String key, String string) {
		return shardJedis.append(key, string);
	}
	
	/**
	 * 删除redis中已有缓存
	 * @param key
	 * @return
	 */
	public static final long del(String key) {
		return shardJedis.del(key);
	}
	
	/**
	 * 获取redis中的缓存
	 * @param key
	 * @return
	 */
	public static final String get(String key) {
		return shardJedis.get(key);
	}
	
	/**
	 * 获取redis中的缓存
	 * @param key
	 * @param cls
	 * @return
	 */
	public static final <T> T get(String key, Class<T> cls) {
		String value = get(key);
		if (value == null) return null;
		return JSONObject.parseObject(value, cls);
	}

}