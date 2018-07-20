package com.ssm.api.dao;

import java.util.List;
import tk.mybatis.mapper.common.Mapper;

import com.ssm.api.bean.response.Order;

public interface OrderDao extends Mapper<Order>{

	List<Order> getOrder();
}
