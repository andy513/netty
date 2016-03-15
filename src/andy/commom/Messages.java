package andy.commom;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Andy<andy_513@163.com>
 *
 */
public final class Messages {

	private static final Logger log = LogManager.getLogger(Messages.class);

	/**
	 * properties文件名
	 */
	private static final String BUNDLE_NAME = "andy"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

	private Messages() {
	}

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			log.error(key + "\t没有找到");
		}
		return "";
	}

	public static int getInt(String key) {
		return Integer.parseInt(getString(key));
	}
}
