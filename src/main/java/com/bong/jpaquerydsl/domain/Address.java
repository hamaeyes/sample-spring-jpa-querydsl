package com.bong.jpaquerydsl.domain;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Address {

	private String city;
	private String street;
	private String zipcode;

	@Override
	public String toString() {
		return "Address{" + "city='" + city + '\'' + ", street='" + street + '\'' + ", zipcode='" + zipcode + '\''
				+ '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Address))
			return false;

		Address address = (Address) o;

		if (city != null ? !city.equals(address.city) : address.city != null)
			return false;
		if (street != null ? !street.equals(address.street) : address.street != null)
			return false;
		if (zipcode != null ? !zipcode.equals(address.zipcode) : address.zipcode != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = city != null ? city.hashCode() : 0;
		result = 31 * result + (street != null ? street.hashCode() : 0);
		result = 31 * result + (zipcode != null ? zipcode.hashCode() : 0);
		return result;
	}
}