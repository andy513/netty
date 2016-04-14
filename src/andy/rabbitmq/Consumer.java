package andy.rabbitmq;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;

import andy.commom.Messages;

/**
 * @author Andy<andy_513@163.com>
 */
public class Consumer {

	public static void main(String[] args) throws IOException {
		receive("queueName", "exchange_key", "", "2");
	}
	
	private static ConnectionFactory factory = new ConnectionFactory();
	private static Connection connection = null;
	private static Channel channel = null;
	private static final ExecutorService es = Executors.newFixedThreadPool(100);

	static {
		try {
			factory.setHost(Messages.getString("RabbitMQChannel.0"));
			factory.setPort(Messages.getInt("RabbitMQChannel.PORT"));
			//设置断线重连
			factory.setAutomaticRecoveryEnabled(true);
			factory.setNetworkRecoveryInterval(10000);
			connection = factory.newConnection(es);
			channel = connection.createChannel();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void receive(String queueName, String key, String type, String routingkey) throws IOException {
		try {
			if (type == null || "".equals(type)) {
				type = AMQP.DIRECT;
			}
			channel.exchangeDeclare(key, type, true);
			channel.queueBind(queueName, key, routingkey);
			QueueingConsumer consumer = new QueueingConsumer(channel);
			channel.basicConsume(queueName, false, consumer);
			while (true) {
				QueueingConsumer.Delivery deliver = consumer.nextDelivery();
				String string = new String(deliver.getBody());
				System.out.println(string);
				channel.basicAck(deliver.getEnvelope().getDeliveryTag(),false);
			}
		} catch (ShutdownSignalException | ConsumerCancelledException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}