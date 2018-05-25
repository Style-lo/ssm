package com.ssm.api.bean.entity.exception;

public class MyException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8490262408755268398L;
	
	public MyException() {
		super();
	} 
	
	public MyException(String msg) {
		super(msg);
	}
}
