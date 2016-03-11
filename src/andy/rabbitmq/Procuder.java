package andy.rabbitmq;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;

/**
 * @author Andy<andy_513@163.com>
 *
 */
public final class Procuder {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		while(true){
			Thread.sleep(5000);
			Procuder.send("", "", true, "hello world!");
		}
	}

	public static final void send(String key, String type, boolean durable, Object... body) throws IOException {
		if (body == null || body.length < 1) {
			return;
		}
		if (type == null || "".equals(type)) {
			type = AMQP.FANOUT;
		}
		String routingKey = "";
		String queueName = "andy";
		Channel channel = RabbitMQChannel.getInstance();
		channel.exchangeDeclare(key, type, durable);
		channel.queueDeclare(queueName, true, false, false, null);
		channel.queueBind(queueName, type, routingKey);
		for(Object string : body){
			channel.basicPublish(type, routingKey, MessageProperties.PERSISTENT_TEXT_PLAIN, string.toString().getBytes());
		}
	}

}
