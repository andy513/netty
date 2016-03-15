package andy.netty;

import andy.netty.ChannelProtos.Channel;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author Andy<andy_513@163.com>
 */
public class ProcuderHandler extends ChannelHandlerAdapter {

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ChannelProtos.Channel.Builder builder = Channel.newBuilder();
		builder.setId(1);
		ctx.writeAndFlush(builder.build());
		System.out.println("发送成功");
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		Channel builder = (Channel) msg;
		System.out.println(builder);
	}

}
