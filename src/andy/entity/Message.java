package andy.entity;

import java.io.Serializable;
import java.util.concurrent.ConcurrentMap;

import com.esotericsoftware.reflectasm.MethodAccess;

/**
 * @author andy<andy_513@163.com>
 */
public class Message implements Serializable {

	private static final long serialVersionUID = 1358887093295742039L;

	private Object obj;
	private MethodAccess method;
	private ConcurrentMap<Integer, Integer> index_map;
	
	public Message(Object obj, MethodAccess method, ConcurrentMap<Integer, Integer> index_map) {
		this.obj = obj;
		this.method = method;
		this.index_map = index_map;
	}
	
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	public MethodAccess getMethod() {
		return method;
	}
	public void setMethod(MethodAccess method) {
		this.method = method;
	}
	public ConcurrentMap<Integer, Integer> getIndex_map() {
		return index_map;
	}
	public void setIndex_map(ConcurrentMap<Integer, Integer> index_map) {
		this.index_map = index_map;
	}
	
}
