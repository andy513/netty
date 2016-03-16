package andy;

import andy.commom.XMLUtil;
import andy.netty.ConsumerServer;

/**
 * @author Andy<andy_513@163.com>
 */
public class Main {
	
	public static void main(String[] args) {
		XMLUtil.run();
		ConsumerServer.extracted("127.0.0.1", 8080);
	}

}
