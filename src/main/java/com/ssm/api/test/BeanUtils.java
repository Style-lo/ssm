package com.ssm.api.test;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.cglib.beans.BeanMap;

import com.ssm.api.bean.entity.ShopncGoods;

public class BeanUtils {

	public static Map<String, Object> beanToMap(Object obj){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			PropertyDescriptor[] pds = Introspector.getBeanInfo(obj.getClass()).getPropertyDescriptors();
			for (PropertyDescriptor p : pds) {
				if (!"class".equals(p.getName())) {
					map.put(p.getName(), p.getReadMethod().invoke(obj));
				}
			}
		} catch (IntrospectionException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
	public static <T> T mapToBean(Map<String, Object> map,T bean){
		BeanMap beanMap = BeanMap.create(bean);
		beanMap.putAll(map);
		return bean;
		
	}
	public static Object ss(Map<String, Object> map,Class<?> bean){
        try {
        	Object obj = bean.newInstance();  
			org.apache.commons.beanutils.BeanUtils.populate(obj, map);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}  
		return bean;
	}
	
	public static void main(String[] args) {
		ShopncGoods shopncGoods = new ShopncGoods();
		shopncGoods.setGoods_id(2);
		shopncGoods.setGoods_name("dsdfsfs");
		shopncGoods.setStore_id(2);
		beanToMap(shopncGoods);
		
	}
}
