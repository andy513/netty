package andy.rabbitmq;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;

import andy.commom.SpringBeans;

/**
 * @author Andy<andy_513@163.com>
 */
public class Consumer {

	public static final RabbitMQChannel MC = SpringBeans.getBean(RabbitMQChannel.class);

	public static void main(String[] args) throws IOException {
		receive("andy1", "exchange_key", "", "2");
	}

	public static void receive(String queueName, String key, String type, String routingkey) throws IOException {
		try {
			Channel channel = MC.getChannel();
			if (type == null || "".equals(type)) {
				type = AMQP.DIRECT;
			}
			channel.exchangeDeclare(key, type, true);
			channel.queueBind(queueName, key, routingkey);
			QueueingConsumer consumer = new QueueingConsumer(channel);
			channel.basicConsume(queueName, true, consumer);
			while (true) {
				QueueingConsumer.Delivery deliver = consumer.nextDelivery();
				String string = new String(deliver.getBody());
				System.out.println(string);
//				channel.basicAck(deliver.getEnvelope().getDeliveryTag(), false);
			}
		} catch (ShutdownSignalException | ConsumerCancelledException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}