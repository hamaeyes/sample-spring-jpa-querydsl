package com.bong.jpaquerydsl.dto;
 

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.bong.jpaquerydsl.domain.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {

	private Long id;

	private String name;

	private AddressDto address;
	
	private List<OrderDto> orders;
	
	private List<ChildrenDto> childrens;
	
	public static MemberDto of(Member member) { 

		return MemberDto.builder()
				.id(member.getId())
				.name(member.getName())
				.address(AddressDto.of(member.getAddress()))
				.orders(
						Optional.ofNullable(member.getOrders())
						.orElse(Collections.emptyList())
						.stream()
						.map(OrderDto::from)  
						.collect(Collectors.toList())
				)
				.build();
	}
}
