package com.bong.jpaquerydsl.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bong.jpaquerydsl.domain.Order;
import com.bong.jpaquerydsl.domain.OrderSearch;
import com.bong.jpaquerydsl.domain.QMember;
import com.bong.jpaquerydsl.domain.QOrder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Repository 
public class OrderRepositoryImpl implements OrderRepositoryCustom {

	@Autowired
	JPAQueryFactory queryFactory;

	public OrderRepositoryImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}
	
	@Override
	public List<Order> search(OrderSearch orderSearch) { 
		
		JPAQuery<Order> query = queryFactory
				.selectFrom(QOrder.order) 
				.innerJoin(QOrder.order.member, QMember.member)
				.where(QOrder.order.status.eq(orderSearch.getOrderStatus()))
				.setHint("org.hibernate.comment", "내용조회, OrderRepositoryImpl.search");
		
		return query.fetch();
	}

}
