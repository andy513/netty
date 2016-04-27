package andy.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.ThreadLocalRandom;

import andy.commom.GlobalUtil;

/**
 * @author Andy<andy_513@163.com>
 */
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String uname;
	private String pwd;
	private LocalDate date;
	private LocalTime time;
	private LocalDateTime dateTime;
	private int age;

	public User() {
	}
	
	public User(String id, String uname, String pwd) {
		setId(id);
		setUname(uname);
		setPwd(pwd);
		setAge(ThreadLocalRandom.current().nextInt(100));
		setDate(LocalDate.now());
		setTime(LocalTime.now());
		setDateTime(LocalDateTime.now());
	}


	public User(String name, String password) {
		this.id = GlobalUtil.UUID();
		this.uname = name;
		this.pwd = password;
		setAge(ThreadLocalRandom.current().nextInt(100));
		setDate(LocalDate.now());
		setTime(LocalTime.now());
		setDateTime(LocalDateTime.now());
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
