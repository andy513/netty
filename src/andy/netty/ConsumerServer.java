package andy.netty;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import andy.commom.Global;
import andy.entity.proto.MessagesProtos;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author Andy<andy_513@163.com>
 */
public class ConsumerServer {
	
	private static final Logger logger = LogManager.getLogger(ConsumerServer.class);

	public static void main(String[] args) {
		extracted("127.0.0.1", 8080);
	}

	public static final void extracted(String ip, int port) {
		EventLoopGroup parentGroup = new NioEventLoopGroup(1);
		EventLoopGroup childGroup = new NioEventLoopGroup(1);
		ServerBootstrap server = new ServerBootstrap();
		server.group(parentGroup, childGroup)
		.channel(NioServerSocketChannel.class)
		.option(ChannelOption.SO_BACKLOG, 100)
		.handler(new LoggingHandler(LogLevel.INFO))
		.childHandler(new ChannelInitializer<SocketChannel>() {
			@Override
			protected void initChannel(SocketChannel ch) throws Exception {
				ch.pipeline().addLast(new ProtobufVarint32FrameDecoder());
				ch.pipeline().addLast(new ProtobufDecoder(MessagesProtos.MessagesProto.getDefaultInstance()));
				ch.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
				ch.pipeline().addLast(new ProtobufEncoder());
				ch.pipeline().addLast(new ConsumerHandler());
			}
		});
		try {
			ChannelFuture future = server.bind(8080).sync();
			logger.info("当前服务器数量" + Global.cpu_size + "启动成功:\t请求http:\\\\" + ip + ":" + port + "\\");
			future.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			parentGroup.shutdownGracefully();
			childGroup.shutdownGracefully();
		}
	}

}
