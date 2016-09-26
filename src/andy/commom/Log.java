package andy.commom;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author andy<andy_513@163.com>
 */
public final class Log {

	private static final Logger logger = LogManager.getLogger(Log.class);

	public static final void init() {
		/*Properties props = System.getProperties();
		props.list(System.out);*/
		try {
			InetAddress addr = InetAddress.getLocalHost();
			logger.debug(addr.getHostName() + "\t" + addr.getHostAddress());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		String os_name = System.getProperty("os.name");// 操作系统类型
		String os_version = System.getProperty("os.version");// 操作系统版本号
		String os_arch = System.getProperty("os.arch");// 操作系统是32位还是64位
		String os_user = System.getProperty("user.name");// 当前登录用户名
		String java_version = System.getProperty("java.version");// Java版本号
		String java_vendor = System.getProperty("java.vendor");// Java供应商
		String java_vm_name = System.getProperty("java.vm.name");//JVM版本号是32位还是64位
		Locale defaultLocale = Locale.getDefault();
		String country = defaultLocale.getCountry();// 返回国家地区代码
		String language = defaultLocale.getLanguage();// 返回国家的语言
		String displayCountry = defaultLocale.getDisplayCountry();// 返回适合向用户显示的国家信息
		String displayLanaguage = defaultLocale.getDisplayLanguage();// 返回适合向用户展示的语言信息
		String displayName = defaultLocale.getDisplayName();// 返回适合向用户展示的语言环境名
		logger.debug("当前登录用户:\t" + os_user);
		logger.debug(os_name + " " + os_version + " " + os_arch + " " + Global.cpu_size);
		logger.debug(java_vendor + " " + java_version + " " + java_vm_name);
		logger.debug(displayName + "\t" + displayCountry + "\t" + displayLanaguage + "\t" + language + "\t" + country);
	}
	
	public static void main(String[] args) {
		init();
	}

}
