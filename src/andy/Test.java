package andy;

import andy.commom.SpringBeans;
import andy.rabbitmq.RabbitMQChannel;

/**
 * @author Andy<andy_513@163.com>
 */
public class Test {
	
	public static void main(String[] args) {
		RabbitMQChannel mc = SpringBeans.getBean(RabbitMQChannel.class);
		System.out.println(mc.getPort());
	}

}
