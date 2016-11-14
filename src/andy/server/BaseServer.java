package andy.server;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.esotericsoftware.reflectasm.MethodAccess;
import com.google.protobuf.ByteString;
import com.google.protobuf.Message.Builder;

import andy.biz.UserBiz;
import andy.biz.impl.UserBizImpl;
import andy.commom.SpringBeans;
import andy.entity.Message;
import andy.entity.proto.MessagesProtos.MessagesProto;
import io.netty.channel.Channel;

/**
 * @author andy<andy_513@163.com>
 */
public class BaseServer {
	
	protected static final UserBiz userBiz = SpringBeans.getBean(UserBizImpl.class);

	protected static final Logger log = LogManager.getLogger(BaseServer.class);

	@SuppressWarnings("unchecked")
	public static final void execute(int id, Channel channel, Message message,ByteString byteString) {
		int index = message.getIndex_map().get(id);
		String openId = "";
		MethodAccess method = message.getMethod();
		/*if (index != 100) {
			User user = Cache.session.get(openId);
			if (user == null) {
				logger.info("玩家未登录");
				channel.writeAndFlush(MessagesProto.newBuilder().setId(1));
			}
		}*/
		Map<Integer, Builder> builder_map = (Map<Integer, Builder>) method.invoke(message.getObj(), index, byteString);
		for (Entry<Integer, Builder> builder : builder_map.entrySet()) {
//			ByteBuf buf = PooledByteBufAllocator.DEFAULT.buffer();
			MessagesProto.Builder messageProto = MessagesProto.newBuilder();
			messageProto.setId(builder.getKey());
			messageProto.setData(builder.getValue().build().toByteString());
			channel.writeAndFlush(messageProto.build());
		}
		log.info("发送成功");
	}

}
