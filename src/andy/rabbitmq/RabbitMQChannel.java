package andy.rabbitmq;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author Andy<andy_513@163.com>
 */
public final class RabbitMQChannel {
	
	private String host;
	private int port;
	private String username;
	private String password;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private ConnectionFactory factory = new ConnectionFactory();
	private Connection connection = null;
	private Channel channel = null;
	
	public RabbitMQChannel() {
		try {
			factory.setHost("127.0.0.1");
			factory.setPort(5672);
			factory.setAutomaticRecoveryEnabled(true);
			factory.setNetworkRecoveryInterval(10000);
//			factory.setThreadFactory(ThreadManager);
			connection = factory.newConnection();
			channel = connection.createChannel();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Channel getChannel() {
		return channel;
	}

}
