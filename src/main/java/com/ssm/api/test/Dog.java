package com.ssm.api.test;

public class Dog {

	public String name;
	public int age;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Dog [name=" + name + "]";
	}
	public static void main(String[] args) {
		int i=32;
		long j=64;
		j=i;
		System.out.println(j);
//		i=j;
	}
}
