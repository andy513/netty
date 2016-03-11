package andy.rabbitmq;

/**
 * @author Andy<andy_513@163.com>
 *
 */
public final class AMQP {
	
	/**
	 * 1对1,完全匹配,给指定的人发送
	 */
	public static final String DIRECT = "direct";
	
	/**
	 * 群发,给所有人发送
	 * fanout交换机转发消息是最快的。 
	 */
	public static final String FANOUT = "fanout";
	
	/**
	 * 群发,给相匹配的人发送
	 * 符号“#”匹配一个或多个词，符号“*”匹配不多不少一个词。
	 * 因此“audit.#”能够匹配到“audit.irs.corporate”，
	 * 但是“audit.*”只会匹配到“audit.irs”。
	 */
	public static final String TOPIC = "topic";
	
	

}
