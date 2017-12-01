package com.ssm.api.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.api.bean.response.Order;
import com.ssm.api.dao.OrderDao;
import com.ssm.api.service.OrderService;

@Service("orderService")
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderDao orderDao;
	
	@Override
	public List<Order> getOrder() {
		
		return orderDao.getOrder();
	}

}
