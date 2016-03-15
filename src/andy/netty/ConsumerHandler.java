package andy.netty;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import andy.netty.ChannelProtos.Channel;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author Andy<andy_513@163.com>
 */
public class ConsumerHandler extends ChannelHandlerAdapter {

	private static final Logger logger = LogManager.getLogger(ConsumerHandler.class);

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("有什么问题吗?");
		ChannelProtos.Channel channel = (ChannelProtos.Channel) msg;
		logger.info(channel);
		ctx.writeAndFlush(createChannel());
	}
	
	private Channel createChannel(){
		Channel.Builder builder = Channel.newBuilder();
		builder.setId(2);
		return builder.build();
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}


}
