package andy.rabbitmq;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import andy.commom.SpringBeans;

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
	
	private RabbitMQChannel() {
		try {
			connection = factory.newConnection();
			factory.setHost(host);
			factory.setPort(port);
			factory.setUsername(username);
			factory.setPassword(password);
			factory.setAutomaticRecoveryEnabled(true);
			channel = connection.createChannel();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static final RabbitMQChannel mc = SpringBeans.getBean(RabbitMQChannel.class);
	
	public static final Channel getInstance(){
		return mc.getChannel();
	}
	
	public Channel getChannel() {
		return channel;
	}

}
