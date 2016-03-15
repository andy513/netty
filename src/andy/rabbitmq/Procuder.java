package andy.rabbitmq;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import andy.commom.Messages;

/**
 * @author Andy<andy_513@163.com>
 *
 */
public final class Procuder {

	private static final ExecutorService es = Executors.newFixedThreadPool(500);
	
	private static final ThreadPoolExecutor n_es = new ThreadPoolExecutor(2000, 2000 * 2, 5, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10000));

	public static void main(String[] args) {
		for (int i = 0; i < 2000; i++) {
			final int a = i;
			n_es.execute(() -> {
				for (int k = 0; k < 10000; k++) {
					try {
						String message = (a + k) + "hello world!hello world!hello world!hello world!hello world!hello world!";
						Procuder.send("queueName", "1", "exchange_key", "", true, message, message);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
	}

	private static ConnectionFactory factory = new ConnectionFactory();
	private static Connection connection = null;

	static {
		try {
			factory.setHost(Messages.getString("RabbitMQChannel.0")); //$NON-NLS-1$
			factory.setPort(Messages.getInt("RabbitMQChannel.PORT"));
			factory.setAutomaticRecoveryEnabled(true);
			factory.setNetworkRecoveryInterval(10000);
			// factory.setThreadFactory(ThreadManager);
			connection = factory.newConnection(es);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

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
		Channel channel = connection.createChannel();
		channel.exchangeDeclare(key, type, durable);
		channel.queueDeclare(queueName, durable, false, false, null);
		channel.queueBind(queueName, key, routingKey);
		for (Object string : body) {
			channel.basicPublish(key, routingKey, MessageProperties.PERSISTENT_TEXT_PLAIN, string.toString().getBytes());
		}
		channel.close();
	}

}
