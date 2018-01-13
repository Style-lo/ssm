package com.ssm.api.bean.entity;

import java.util.Date;

public class UserMoney {

	private int id;
	private int user_id;
	private double money;
	private String pay;
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
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public String getPay() {
		return pay;
	}
	public void setPay(String pay) {
		this.pay = pay;
	}
	public UserMoney(int id, int user_id, double money, String pay) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.money = money;
		this.pay = pay;
	}
	public UserMoney() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
