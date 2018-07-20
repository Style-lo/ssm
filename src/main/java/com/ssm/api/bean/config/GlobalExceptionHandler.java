package com.ssm.api.bean.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 处理所有错误信息
 * @author Administrator
 *
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
    String handleException(Exception e){
		System.out.println("统一异常处理前----------------------------------------------------------------");
		e.printStackTrace();
		System.out.println("统一异常处理后----------------------------------------------------------------");
        return "系统繁忙  请稍后再试!";
    }
}
