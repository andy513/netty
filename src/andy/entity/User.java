package andy.entity;

import java.io.Serializable;

/**
 * @author Andy<andy_513@163.com>
 */
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private String password;
	
	public User() {
	}

	public User(String name, String password) {
		this.name = name;
		this.password = password;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
