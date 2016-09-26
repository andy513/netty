package andy.commom;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

/**
 * XMemcached缓存帮助类
 * 
 * @author andy
 *
 */
public final class XMemcache {

	private static final Logger logger = LogManager.getLogger(XMemcache.class);

	public static final MemcachedClient XMEMCACHED = SpringBeans.getBean("memcachedClient");

	private static final Lock lock = new ReentrantLock();

	public static final boolean add(String key, Object value) {
		boolean bool = false;
		try {
			bool = XMEMCACHED.add(key, 0, value);
		} catch (TimeoutException | InterruptedException | MemcachedException e) {
			logger.error("缓存添加失败:\t" + key + "\t" + value);
		}
		if (!bool) {
			try {
				set(key, value);
			} catch (TimeoutException | InterruptedException | MemcachedException e) {
				e.printStackTrace();
			}
			logger.info("缓存添加失败,已有相同KEY存在:\t" + key);
		}
		return bool;
	}

	private static final boolean set(String key, Object value) throws TimeoutException, InterruptedException, MemcachedException {
		return XMEMCACHED.set(key, 0, value);
	}

	public static final <T> T get(String key) {
		lock.lock();
		try {
			return XMEMCACHED.get(key);
		} catch (TimeoutException | InterruptedException | MemcachedException e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
		return null;
	}

	public static final <T> T get(Integer key) {
		return get(key + "");
	}

	private static final boolean del(String key) {
		boolean bool = false;
		try {
			if (XMEMCACHED.get(key) != null) {
				bool = XMEMCACHED.delete(key);
				if (!bool)
					logger.info("缓存删除失败:\t" + key);
			}
		} catch (TimeoutException | InterruptedException | MemcachedException e) {
			logger.error("缓存删除失败:\t" + key);
		}
		return bool;
	}

	public static final void flushAll() {
		try {
			XMEMCACHED.flushAll();
			logger.debug("缓存删除成功");
		} catch (TimeoutException | InterruptedException | MemcachedException e) {
			e.printStackTrace();
		}
	}

	private static final <T> boolean addT(T t, String key, boolean bool) throws TimeoutException, InterruptedException, MemcachedException {
		boolean result = false;
		List<T> lists = null;
		if (t instanceof List) {
			lists = (List<T>) t;
		} else {
			lists = new ArrayList<T>();
			lists.add(t);
		}
		result = add(lists, key, bool);
		return result;
	}

	private static final <T> boolean add(List<T> lists, String key, boolean bool) throws TimeoutException, InterruptedException, MemcachedException {
		lock.lock();
		try {
			List<T> ts = get(key);
			if (ts == null) {
				return set(key, lists);
			} else {
				if (bool) {
					ts.addAll(lists);
				} else {
					int max_size = ts.size();
					int min_size = lists.size();
					for (int i = 0; i < max_size; i++) {
						T ti = ts.get(i);
						for (int j = 0; j < min_size; j++) {
							T tj = lists.get(j);
							if (ti.equals(tj)) {
								ts.set(i, tj);
								break;
							}
						}
					}
				}
				return set(key, ts);
			}
		} finally {
			lock.unlock();
		}
	}

	public static final <T> boolean addT(T t, String key) throws TimeoutException, InterruptedException, MemcachedException {
		return addT(t, key, true);
	}

	public static final <T> boolean modifyT(T t, String key) throws TimeoutException, InterruptedException, MemcachedException {
		return addT(t, key, false);
	}

	public static final <T> void delT(List<T> lists, String key) throws TimeoutException, InterruptedException, MemcachedException {
		List<T> ts = get(key);
		if (ts != null) {
			ts.removeAll(lists);
			set(key, ts);
		}
	}

	public static final <T> void delT(T t, String key) throws TimeoutException, InterruptedException, MemcachedException {
		List<T> ts = get(key);
		if (ts != null) {
			ts.remove(t);
			set(key, ts);
		}
	}

	public static final void delT(String key) {
		del(key);
	}

}
