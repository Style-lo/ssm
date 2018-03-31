package com.ssm.api.dao;

import java.util.List;

import org.apache.poi.ss.formula.functions.T;

import tk.mybatis.mapper.common.Mapper;

import com.ssm.api.bean.response.Order;

public interface OrderDao extends Mapper<T>{

	List<Order> getOrder();
}
