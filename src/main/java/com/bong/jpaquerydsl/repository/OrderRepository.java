package com.bong.jpaquerydsl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bong.jpaquerydsl.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> , OrderRepositoryCustom {
	
	 
}
