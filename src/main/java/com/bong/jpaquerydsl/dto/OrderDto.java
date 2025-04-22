package com.bong.jpaquerydsl.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.bong.jpaquerydsl.domain.Order;
import com.bong.jpaquerydsl.domain.OrderItem;
import com.bong.jpaquerydsl.domain.item.Item;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto  {
  
	private Long id;
    private String memberName;
    private String status; 
    List<String> items;

    public OrderDto() {}

    public OrderDto(Long id, String memberName, String status, List<String> items ) {
        this.id = id;
        this.memberName = memberName;
        this.status = status; 
        this.items = items;
    }

    public static OrderDto from(Order order) {
        return new OrderDto(
            order.getId(),
            order.getMember().getName(), // 연관된 Member에서 이름 추출
            order.getStatus().name(),       // enum -> 문자열
            order.getOrderItems().stream().map(OrderItem::getItem).map(Item::getName).collect(Collectors.toList())
        );
    }
}