package com.bong.jpaquerydsl.repository;

import java.util.List;

import com.bong.jpaquerydsl.domain.Order;
import com.bong.jpaquerydsl.domain.OrderSearch;

public interface OrderRepositoryCustom {

	public List<Order> search(OrderSearch orderSearch);
}
