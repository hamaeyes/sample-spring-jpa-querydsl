package com.bong.jpaquerydsl.dto;
 

import java.util.List;
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
	
	public static MemberDto of(Member member) {
		return MemberDto.builder()
				.id(member.getId())
				.name(member.getName())
				.address(AddressDto.of(member.getAddress()))
				.orders(member.getOrders().stream().map(OrderDto::from).collect(Collectors.toList()))
				.build();
	}
}
