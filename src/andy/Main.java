package andy;

import andy.commom.PropertiesUtil;
import andy.commom.XMLUtil;
import andy.netty.ConsumerServer;

/**
 * @author andy<andy_513@163.com>
 */
public class Main {

	public static void main(String[] args) {
		String ip = PropertiesUtil.getString("tafang.host");
		int port = PropertiesUtil.getInt("tafang.port");
		XMLUtil.run();
		ConsumerServer.extracted(ip, port);
	}

}
