package com.ssm.api.bean.request;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "user")
public class User {
	@Id
	private int id;
	private String user_name;
	private String password;
	private int age;
	private boolean bool;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(int id, String user_name, String password, int age, boolean bool) {
		super();
		this.id = id;
		this.user_name = user_name;
		this.password = password;
		this.age = age;
		this.bool = bool;
	}
	public boolean isBool() {
		return bool;
	}
	public void setBool(boolean bool) {
		this.bool = bool;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
}
