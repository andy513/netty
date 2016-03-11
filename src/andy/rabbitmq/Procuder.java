package andy.rabbitmq;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;

import andy.commom.SpringBeans;

/**
 * @author Andy<andy_513@163.com>
 *
 */
public final class Procuder {

	public static void main(String[] args) {
		while (true) {
			try {
				Thread.sleep(5000);
				String message = "hello world!";
				Procuder.send("andy1", "2", "exchange_key", "", true, message);
				System.out.println("成功发送消息:\t" + message);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static final RabbitMQChannel MC = SpringBeans.getBean(RabbitMQChannel.class);

	/**
	 * @param key
	 *            对应的健
	 * @param type
	 *            发布类型
	 * @param durable
	 *            是否持久化
	 * @param body
	 *            发送消息内容
	 * @throws IOException
	 */
	public static final void send(String queueName, String routingKey, String key, String type, boolean durable, Object... body) throws IOException {
		if (body == null || body.length < 1) {
			return;
		}
		if (type == null || "".equals(type)) {
			type = AMQP.DIRECT;
		}
		Channel channel = MC.getChannel();
		channel.exchangeDeclare(key, type, durable);
		channel.queueDeclare(queueName, durable, false, false, null);
		channel.queueBind(queueName, key, routingKey);
		// for(Object string : body){
		channel.basicPublish(key, routingKey, MessageProperties.PERSISTENT_TEXT_PLAIN, body[0].toString().getBytes());
		// }
	}

}
