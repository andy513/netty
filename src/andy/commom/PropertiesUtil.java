package andy.commom;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author andy<andy_513@163.com>
 *
 */
public final class PropertiesUtil {

	private static final Logger log = LogManager.getLogger(PropertiesUtil.class);

	/**
	 * properties文件名
	 */
	private static final String BUNDLE_NAME = "andy"; //$NON-NLS-1$

	/**
	 * 国际化读取
	 */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);
	private static final Properties prop = new Properties();
	
	static{
		try {
			String dir = System.getProperty(FileUtil.DIR);
			String separator = System.getProperty(FileUtil.SEPARATOR);
			prop.load(new FileInputStream(dir + separator + "src" + separator + "andy.properties"));
//			prop.list(System.out);
//			System.getProperties().list(System.out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static final String getString(String key) {
		try {
			return prop.getProperty(key);
		} catch (MissingResourceException e) {
			log.error(key + "\t没有找到");
		}
		return null;
	}
	
	public static final int getInt(String key) {
		String value = getString(key);
		try {
			return Integer.parseInt(value);
		} catch (Exception e) {
			log.error(key + "\t" + value, e);
		}
		return 0;
	}
}
