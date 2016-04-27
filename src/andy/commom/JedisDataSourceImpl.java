package andy.commom;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * @author Andy<andy_513@163.com>
 */
public class JedisDataSourceImpl {

	private static final ShardedJedisPool shardedJedisPool = SpringBeans.getBean(ShardedJedisPool.class);

	public static final ShardedJedis getRedisClient() {
		ShardedJedis shardJedis = null;
		try {
			shardJedis = shardedJedisPool.getResource();
			return shardJedis;
		} catch (Exception e) {
			if (null != shardJedis)
				shardJedis.close();
		}
		return null;
	}

}
