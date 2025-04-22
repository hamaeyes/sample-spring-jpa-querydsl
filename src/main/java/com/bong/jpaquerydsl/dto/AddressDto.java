package com.bong.jpaquerydsl.dto;

import com.bong.jpaquerydsl.domain.Address;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
	private String city;
	private String street;
	private String zipcode;
	
	public static AddressDto of(Address address) {
		return AddressDto.builder()
				.city(address.getCity())
				.street(address.getStreet())
				.zipcode(address.getZipcode())
				.build();
	}
}
