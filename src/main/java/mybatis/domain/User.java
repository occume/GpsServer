package mybatis.domain;

import java.io.Serializable;

public class User implements Serializable{
	
	private static final long serialVersionUID = 4184871401453031406L;
	
	private int	id;
	private String 	tableName;
	private String 	name;
	private String 	password;
	private String 	email;
	
	public User(){}
	
	public User(String name, String tableName) {
		this.name = name;
		this.tableName = tableName;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public User(int id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
	}

	public User(int id, String name, String password, String email) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password
				+ ", email=" + email + "]";
	}

}
