package andy.server;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.esotericsoftware.reflectasm.MethodAccess;
import com.google.protobuf.Message.Builder;

import andy.entity.Message;
import andy.entity.MessagesProtos.MessagesProto;
import andy.entity.UserProtos.UserProto;
import io.netty.channel.Channel;

/**
 * @author Andy<andy_513@163.com>
 */
public class BaseServer {

	protected static final Logger logger = LogManager.getLogger(BaseServer.class);

	@SuppressWarnings("unchecked")
	public static final void execute(int id, Channel channel, Message message,UserProto userProto) {
		int index = message.getIndex_map().get(id);
		MethodAccess method = message.getMethod();
		Map<Integer, Builder> builder_map = (Map<Integer, Builder>) method.invoke(message.getObj(), index, userProto);
		for (Entry<Integer, Builder> builder : builder_map.entrySet()) {
//			ByteBuf buf = PooledByteBufAllocator.DEFAULT.buffer();
			MessagesProto.Builder messageProto = MessagesProto.newBuilder();
			messageProto.setId(builder.getKey());
			messageProto.setData(builder.getValue().build().toByteString());
			channel.writeAndFlush(messageProto.build());
		}
		logger.info("发送成功");
	}

}
