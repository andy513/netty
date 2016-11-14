package andy.entity;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import andy.biz.UserBiz;
import andy.biz.impl.UserBizImpl;
import andy.commom.SpringBeans;

public final class ID implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final Logger log = LogManager.getLogger(ID.class);
	private static final UserBiz userBiz = SpringBeans.getBean(UserBizImpl.class);

	public static AtomicInteger userId = null;
	
	static {
		try {
			userId = new AtomicInteger(userBiz.getUserId());
		} catch (Exception e) {
			log.error("玩家数据ID初始化有问题", e);
		}
	}

}
