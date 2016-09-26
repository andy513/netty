package andy.test;

import andy.commom.XMemcache;
import net.rubyeye.xmemcached.GetsResponse;

/**
 * @author andy<andy_513@163.com>
 */
public class XMemcachedTest {

	public static void main(String[] args) throws Exception {
		GetsResponse<Integer> result = XMemcache.XMEMCACHED.gets("a");
		if (result != null) {
			long cas = result.getCas();
			System.out.println(cas);
			if (!XMemcache.XMEMCACHED.cas("a", 0, 2, cas)) {
				System.err.println("cas error");
			}
		}
	}

}
