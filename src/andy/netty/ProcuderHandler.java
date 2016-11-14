package andy.netty;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

import andy.entity.proto.MessagesProtos.MessagesProto;
import andy.entity.proto.UserProtos;
import andy.entity.proto.UserProtos.UserProto;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author andy<andy_513@163.com>
 */
public class ProcuderHandler extends ChannelHandlerAdapter {
	
	private CountDownLatch cdl;

//	private static final ExecutorService es = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

	public ProcuderHandler(CountDownLatch cdl) {
		this.cdl = cdl;
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
			MessagesProto.Builder builder = MessagesProto.newBuilder();
			builder.setId(102);
			UserProto.Builder userProto = UserProto.newBuilder();
			userProto.setUsername("andy");
			userProto.setPassword("1");
			builder.setData(userProto.build().toByteString());
			/*UserProtos.LoginProto.Builder loginProto = LoginProto.newBuilder();
			loginProto.setUserProto(UserProto.newBuilder().setUsername("andy").setPassword("andy"));
			builder.setData(loginProto.build().toByteString());*/
			cdl.countDown();
			ctx.writeAndFlush(builder.build());
		// System.out.println(101 + ":\t发送成功");
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		MessagesProto messageProto = (MessagesProto) msg;
		UserProto userProto = UserProtos.UserProto.parseFrom(messageProto.getData());
		System.out.println(userProto);
	}

}
