package andy.commom;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.util.ClassUtils;

import com.esotericsoftware.reflectasm.MethodAccess;

import andy.entity.Message;
import andy.server.LoginServer;

/**
 * @author andy<andy_513@163.com>
 */
public final class XMLUtil {

	private static final Logger log = LogManager.getLogger(XMLUtil.class);

	public static void extracted() {
		String bag = ClassUtils.getPackageName(LoginServer.class);
		String file = bag.replace('.', '/');
		File packeageDir = new File("src/" + file);
		for (String string : packeageDir.list()) {
			System.out.println(bag + "." + string.replaceAll("java", "class"));
		}
	}

	public static final void run() {
		try {
			XMLUtil x = new XMLUtil();
			// x.write();
			x.read_andy();
			log.debug("XML加载完成!");
		} catch (Exception e) {
			log.debug("XML文件配置有问题.", e);
		}
	}

	private static final ConcurrentMap<String, String> map = new ConcurrentHashMap<String, String>();

	static {
		map.put("10", "andy.server.LoginServer");
	}

	public void write() {
		try {
			Document document = DocumentHelper.createDocument();
			document.addElement("andy");
			Element element = document.getRootElement();
			for (Entry<String, String> entry : map.entrySet()) {
				String className = entry.getValue();
				Element cls = element.addElement("class");
				MethodAccess method = MethodAccess.get(Class.forName(className));
				for (String string : method.getMethodNames()) {
					int index = method.getIndex(string);
					String key = entry.getKey();
					cls.addAttribute("id", key).addAttribute("cls", className);
					cls.addElement("method").addAttribute("id", key + index).addAttribute("method", string);
				}
			}
			Writer osWrite = new OutputStreamWriter(new FileOutputStream("src/andy.xml"));// 创建输出流
			OutputFormat format = OutputFormat.createPrettyPrint(); // 获取输出的指定格式
			format.setIndent(true);
			format.setEncoding("UTF-8");// 设置编码 ，确保解析的xml为UTF-8格式
			XMLWriter writer = new XMLWriter(osWrite, format);// XMLWriter
			writer.write(document);// 把document写入xmlFile指定的文件(可以为被解析的文件或者新创建的文件)
			writer.flush();
			osWrite.flush();
			writer.close();
			osWrite.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void read_andy() {
		Element rootElement = reader("src/andy.xml");
		for (Iterator<Element> elements = rootElement.elementIterator("class"); elements.hasNext();) {
			Element element = elements.next();
			try {
				Class<?> cls = Class.forName(getString(element, "cls"));
				Object obj = cls.newInstance();
				MethodAccess method = MethodAccess.get(cls);
				String key = getString(element, "id");
				ConcurrentMap<Integer, Integer> map = new ConcurrentHashMap<Integer, Integer>();
				for (Iterator<Element> es = element.elementIterator(); es.hasNext();) {
					Element e = es.next();
					int index = method.getIndex(getString(e, "method"));
					int id = getInt(e, "id");
					map.put(id, index);
				}
				Cache.message_map.put(key, new Message(obj, method, map));
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1) {
				e1.printStackTrace();
			}
		}
	}

	private String getString(Element e, String key) {
		return e.attributeValue(key);
	}

	private int getInt(Element e, String key) {
		return Integer.parseInt(getString(e, key));
	}

	private Element reader(String file) {
		SAXReader reader = new SAXReader();
		Document document = null;
		try {
			document = reader.read(file);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return document.getRootElement();
	}

}
