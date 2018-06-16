package com.ssm.api.bean.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.Data;

import org.hibernate.validator.constraints.NotBlank;

@Data
public class UserLog {

	@NotBlank(message="id不能为空-")
	private Integer id;
	@NotBlank(message="user_id不能为空--")
	private Integer user_id;
	@NotNull(message="date不能为空---")
	private Date date;
	@NotBlank(message="msg不能为空----")
	private String msg;

}
