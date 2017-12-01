package com.ssm.api.bean.request;

public class User {

	private int id;
	private String user_name;
	private String password;
	private int age;
	public User(int id, String user_name, String password, int age) {
		super();
		this.id = id;
		this.user_name = user_name;
		this.password = password;
		this.age = age;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
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
