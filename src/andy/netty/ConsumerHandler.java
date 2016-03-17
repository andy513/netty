package andy.netty;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import andy.commom.Cache;
import andy.entity.Message;
import andy.entity.proto.MessagesProtos.MessagesProto;
import andy.server.BaseServer;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author Andy<andy_513@163.com>
 */
public class ConsumerHandler extends ChannelHandlerAdapter {

	private static final Logger logger = LogManager.getLogger(ConsumerHandler.class);
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		logger.info("连接成功");
//		super.channelActive(ctx);
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		logger.info("成功关闭");
//		super.channelInactive(ctx);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		MessagesProto messages = (MessagesProto) msg;
		int id = messages.getId();
		String key = (id + "").substring(0, 2);
		Message message = Cache.message_map.get(key);
		Channel channel = ctx.channel();
		BaseServer.execute(id, channel, message, messages.getData());
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}


}
