package andy.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.ThreadLocalRandom;

import andy.commom.GlobalUtil;

/**
 * @author andy<andy_513@163.com>
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
	private boolean update;

	public User() {
	}
	
	public User(String name, String password) {
		this.id = GlobalUtil.UUID();
		setUname(name);
		setPwd(password);
		setAge(ThreadLocalRandom.current().nextInt(100));
		setDate(LocalDate.now());
		setTime(LocalTime.now());
		setDateTime(LocalDateTime.now());
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

	public int getAge() {
		return age;
	}

	public LocalDate getDate() {
		return date;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public String getId() {
		return id;
	}

	public String getPwd() {
		return pwd;
	}

	public LocalTime getTime() {
		return time;
	}

	public String getUname() {
		return uname;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public boolean isUpdate() {
		return update;
	}

	public void setAge(int age) {
		setUpdate(true);
		this.age = age;
	}

	public void setDate(LocalDate date) {
		setUpdate(true);
		this.date = date;
	}

	public void setDateTime(LocalDateTime dateTime) {
		setUpdate(true);
		this.dateTime = dateTime;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPwd(String pwd) {
		setUpdate(true);
		this.pwd = pwd;
	}

	public void setTime(LocalTime time) {
		setUpdate(true);
		this.time = time;
	}

	public void setUname(String uname) {
		setUpdate(true);
		this.uname = uname;
	}

	public void setUpdate(boolean update) {
		this.update = update;
	}

}
