package andy.commom;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Andy<andy_513@163.com>
 */
public final class SpringBeans {

	private static final class SpringBeansFactory{
		public static final ClassPathXmlApplicationContext CONTEXT = new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	
	public static final ClassPathXmlApplicationContext getInstance(){
		return SpringBeansFactory.CONTEXT;
	}
	
	public static final <T> T getBean(Class<T> cls){
		return getInstance().getBean(cls);
	}
	
	@SuppressWarnings("unchecked")
	public static final <T> T getBean(String string){
		return (T) getInstance().getBean(string);
	}
	
}
