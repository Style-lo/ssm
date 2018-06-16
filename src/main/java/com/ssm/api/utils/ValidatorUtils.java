package com.ssm.api.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.hibernate.validator.HibernateValidator;

import com.ssm.api.bean.entity.UserLog;
import com.ssm.api.bean.entity.exception.MyException;

public class ValidatorUtils {

    private static Validator validator = Validation
            .byProvider(HibernateValidator.class).configure().failFast(true).buildValidatorFactory().getValidator();
    /**
     * 实体参数校验
     * @param obj
     */
    public static <T> void validate(T obj) {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(obj);
        // 抛出检验异常
        if (constraintViolations.size() > 0) {
            throw new MyException(String.format("参数校验失败:%s", constraintViolations.iterator().next().getMessage()));
        }
    }
    
    public static void main(String[] args) {
    	List<String> list=new ArrayList<String>();
    	list.add("22");
    	System.out.println(list.get(0));
    	
    	UserLog ul = new UserLog();  
    	ul.setDate(new Date());
         validate(ul);
	}
}
