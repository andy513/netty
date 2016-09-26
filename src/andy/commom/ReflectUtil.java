package andy.commom;

import com.esotericsoftware.reflectasm.ConstructorAccess;
import com.esotericsoftware.reflectasm.MethodAccess;

import andy.entity.User;

/**
 * @author andy<andy_513@163.com>
 */
public final class ReflectUtil {

	public static void main(String[] args) {
		Class<?> cls = null;
		try {
			cls = Class.forName("andy.entity.User");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		ConstructorAccess<?> constructor = ConstructorAccess.get(cls);
		Object obj = constructor.newInstance();
		MethodAccess access = MethodAccess.get(User.class);
		int index = access.getIndex("setName");
		access.invoke(obj, index, "31332");
		Object name = access.invoke(obj, "getName");
		System.out.println(name);
	}

}
