package com.ssm.api.bean.entity;

import java.util.Date;

public class UserLog {

	private int id;
	private int user_id;
	private Date date;
	private String msg;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public UserLog(int id, int user_id, Date date, String msg) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.date = date;
		this.msg = msg;
	}
	public UserLog() {
		super();
		// TODO Auto-generated constructor stub
	}
}
