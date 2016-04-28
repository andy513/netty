package andy.commom;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.alibaba.fastjson.JSONObject;

import redis.clients.jedis.ShardedJedis;

/**
 * @author Andy<andy_513@163.com>
 */
public final class RedisMapCache {

	public static final ShardedJedis shardJedis = JedisDataSourceImpl.getRedisClient();

	public static final long set(String key, String field, String value) {
		return shardJedis.hset(key, field, value);
	}

	public static final <T> T get(String key, String field, Class<T> cls) {
		String value = shardJedis.hget(key, field);
		return JSONObject.parseObject(value, cls);
	}
	
	public static final <T> List<T> getValue(String key,Class<T> cls,String...fields){
		return extracted(cls, shardJedis.hmget(key, fields));
	}

	public static final <T> Set<String> getKeys(String key, Class<String> cls) {
		return shardJedis.hkeys(key);
	}

	public static final <T> List<T> getValues(String key, Class<T> cls) {
		return extracted(cls, shardJedis.hvals(key));
	}

	private static <T> List<T> extracted(Class<T> cls, List<String> lists) {
		List<T> ts = new ArrayList<T>();
		for (int i = 0; i < lists.size(); i++) {
			String value = lists.get(i);
			ts.add(JSONObject.parseObject(value, cls));
		}
		return ts;
	}

	public static final <T> ConcurrentMap<String, T> getAll(String key, Class<T> cls) {
		Map<String, String> map = shardJedis.hgetAll(key);
		ConcurrentMap<String, T> result = new ConcurrentHashMap<String, T>();
		for (Entry<String, String> entry : map.entrySet()) {
			String field = entry.getKey();
			String value = entry.getValue();
			result.put(field, JSONObject.parseObject(value, cls));
		}
		return result;
	}

}
