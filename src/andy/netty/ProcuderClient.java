package andy.netty;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import andy.commom.PropertiesUtil;
import andy.entity.proto.MessagesProtos;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

/**
 * @author andy<andy_513@163.com>
 */
public class ProcuderClient {

	private static final ExecutorService es = Executors.newCachedThreadPool();

	public static void main(String[] args) throws Exception {
		String ip = PropertiesUtil.getString("tafang.host");
		int port = PropertiesUtil.getInt("tafang.port");
		int size = 5;
		CountDownLatch cdl = new CountDownLatch(size);
		for (int i = 0; i < size; i++) {
			es.execute(() -> {
				extracted(ip, port, cdl);
			});
		}
		cdl.await();
	}

	private static void extracted(String ip, int port,CountDownLatch cdl) {
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		Bootstrap strap = new Bootstrap();
		strap.group(workerGroup).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true)
				.handler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel ch) throws Exception {
						ch.pipeline().addLast(new ProtobufVarint32FrameDecoder());
						ch.pipeline().addLast(new ProtobufDecoder(MessagesProtos.MessagesProto.getDefaultInstance()));
						ch.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
						ch.pipeline().addLast(new ProtobufEncoder());
						ch.pipeline().addLast(new ProcuderHandler(cdl));
					}
				});
		ChannelFuture future;
		try {
			future = strap.connect(ip, port).sync();
			future.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			workerGroup.shutdownGracefully();
		}
	}

}
