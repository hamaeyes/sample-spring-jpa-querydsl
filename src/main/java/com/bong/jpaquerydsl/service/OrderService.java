package com.bong.jpaquerydsl.service;

import java.util.List;

import com.bong.jpaquerydsl.domain.Order;
import com.bong.jpaquerydsl.domain.OrderSearch;

public interface OrderService {

	public Long order(Long memberId, Long itemId, int count);
	
	public void cancelOrder(Long orderId);
	
	public List<Order> findOrders(OrderSearch orderSearch);
}
