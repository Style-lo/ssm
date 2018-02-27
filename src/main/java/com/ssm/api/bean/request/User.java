package com.ssm.api.bean.request;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "user")
@Data					//提供类所有属性的 getting 和 setting 方法，此外还提供了equals、canEqual、hashCode、toString 方法
@AllArgsConstructor		//为类提供一个全参构造方法
@NoArgsConstructor		//为类提供一个无参构造方法
public class User {
	@Id
	private int id;
	private String user_name;
	private String password;
	private int age;
	private boolean bool;
	
}
