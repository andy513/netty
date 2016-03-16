package andy.netty;

import andy.entity.MessagesProtos.MessagesProto;
import andy.entity.UserProtos;
import andy.entity.UserProtos.LoginProto;
import andy.entity.UserProtos.UserProto;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author Andy<andy_513@163.com>
 */
public class ProcuderHandler extends ChannelHandlerAdapter {

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		MessagesProto.Builder builder = MessagesProto.newBuilder();
		builder.setId(101);
		UserProtos.LoginProto.Builder loginProto = LoginProto.newBuilder();
		loginProto.setUserProto(UserProto.newBuilder().setUsesrname("andy").setPassword("andy"));
		builder.setData(loginProto.build().toByteString());
		ctx.writeAndFlush(builder.build());
		System.out.println(101 + ":\t发送成功");
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		MessagesProto messageProto = (MessagesProto) msg;
		UserProto userProto = UserProtos.UserProto.parseFrom(messageProto.getData());
		System.out.println(userProto);
	/*	ByteBuf buf = (ByteBuf) msg;
		int id = buf.readInt();
		UserProto.Builder builder = UserProto.newBuilder();
		int limit = buf.writerIndex();
		System.out.println(limit);
		byte[] bytes = new byte[-4];
		buf.readBytes(bytes);
		builder.mergeFrom(bytes);
		System.out.println(id);
		System.out.println(builder);*/
	}

}
