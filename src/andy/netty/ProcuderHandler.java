package andy.netty;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import andy.entity.proto.MessagesProtos.MessagesProto;
import andy.entity.proto.UserProtos;
import andy.entity.proto.UserProtos.UserProto;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author andy<andy_513@163.com>
 */
public class ProcuderHandler extends ChannelHandlerAdapter {

	private static final ExecutorService es = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		es.execute(() -> {
			MessagesProto.Builder builder = MessagesProto.newBuilder();
			builder.setId(102);
			UserProto.Builder userProto = UserProto.newBuilder();
			userProto.setUsername("andy");
			userProto.setPassword("andy");
			builder.setData(userProto.build().toByteString());
			/*UserProtos.LoginProto.Builder loginProto = LoginProto.newBuilder();
			loginProto.setUserProto(UserProto.newBuilder().setUsername("andy").setPassword("andy"));
			builder.setData(loginProto.build().toByteString());*/
			ctx.writeAndFlush(builder.build());
		});
		// System.out.println(101 + ":\t发送成功");
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		MessagesProto messageProto = (MessagesProto) msg;
		UserProto userProto = UserProtos.UserProto.parseFrom(messageProto.getData());
		System.out.println(userProto);
	}

}
