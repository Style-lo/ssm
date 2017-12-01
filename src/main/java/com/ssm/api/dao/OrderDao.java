package com.ssm.api.dao;

import java.util.List;

import com.ssm.api.bean.response.Order;

public interface OrderDao {

	List<Order> getOrder();
}
