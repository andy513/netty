package andy.netty;

import andy.netty.ChannelProtos.Channel;
import andy.netty.ChannelProtos.Channel.Builder;
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
		ctx.write(builder.build());
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		Channel.Builder builder = (Builder) msg;
		System.out.println(builder);
	}

}
