package com.bong.jpaquerydsl.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bong.jpaquerydsl.domain.OrderSearch;
import com.bong.jpaquerydsl.dto.OrderDto;
import com.bong.jpaquerydsl.service.OrderService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class OrderController {
 
	private final OrderService orderService; 
  
	@PostMapping(value = "/order")
	public @ResponseBody Long  createOrder(@RequestParam("memberId") Long memberId,
			@RequestParam("itemId") Long itemId,
			@RequestParam("count") int count) {
		
		return orderService.order(memberId, itemId, count);
 
	}
	
	@GetMapping(value = "/order")
	public @ResponseBody List<OrderDto>  getOrders(OrderSearch orderSearch) {
		
		return orderService.findOrders(orderSearch).stream()
				.map(OrderDto::from)
				.collect(Collectors.toList());
 
	}

	 
}
 