package andy.entity;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author Andy<andy_513@163.com>
 */
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String uname;
	private String pwd;

	public User() {
	}

	public User(String name, String password) {
		this.id = UUID.randomUUID().toString();
		this.uname = name;
		this.pwd = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
}
