package com.bong.jpaquerydsl.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.bong.jpaquerydsl.domain.Delivery;
import com.bong.jpaquerydsl.domain.Member;
import com.bong.jpaquerydsl.domain.Order;
import com.bong.jpaquerydsl.domain.OrderItem;
import com.bong.jpaquerydsl.domain.OrderSearch;
import com.bong.jpaquerydsl.domain.item.Item;
import com.bong.jpaquerydsl.repository.MemberRepository;
import com.bong.jpaquerydsl.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class OrderServiceImpl implements OrderService {
	
	private final MemberRepository memberRepository;
	private final OrderRepository orderRepository;
	private final ItemService itemService;

	@Override
	public Long order(Long memberId, Long itemId, int count) {
		Member member = memberRepository.findById(memberId).orElseThrow(  () -> new RuntimeException("회원정보를 찾을 수 없습니다."));
		Item item = itemService.findOne(itemId);
		
		Delivery delivery = new Delivery(member.getAddress());
		OrderItem orderItems = OrderItem.createOrderItem(item, item.getPrice(), count);
		
		Order order = Order.createOrder(member, delivery, orderItems);
		
		orderRepository.save(order);
		
		return order.getId();
		
	}

	@Override
	public void cancelOrder(Long orderId) {
		Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("주문정보를 찾을 수 없습니다."));
		order.cancel();
		
		
	}

	@Override
	public List<Order> findOrders(OrderSearch orderSearch) {
		return orderRepository.search(orderSearch);
	}

}
