package andy.server;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.esotericsoftware.reflectasm.MethodAccess;
import com.google.protobuf.Message.Builder;

import andy.entity.Message;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;

/**
 * @author Andy<andy_513@163.com>
 */
public class BaseServer {

	protected static final Logger logger = LogManager.getLogger(BaseServer.class);

	@SuppressWarnings("unchecked")
	public static final void execute(int id, Channel channel, Message message) {
		int index = message.getIndex_map().get(id);
		MethodAccess method = message.getMethod();
		JSONObject json = new JSONObject();
		json.put("uid", index);
		Map<Integer, Builder> builder_map = (Map<Integer, Builder>) method.invoke(message.getObj(), index, json);
		for (Entry<Integer, Builder> builder : builder_map.entrySet()) {
			ByteBuf buf = PooledByteBufAllocator.DEFAULT.buffer();
			buf.writeInt(builder.getKey());
			buf.writeBytes(builder.getValue().build().toByteArray());
			channel.writeAndFlush(buf);
		}
		logger.info("发送成功");
	}

}
