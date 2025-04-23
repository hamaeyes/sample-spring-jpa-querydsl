package com.bong.jpaquerydsl.dto;

import com.bong.jpaquerydsl.domain.Children;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChildrenDto {
	
	private Long id;
	private Integer age;
	private String name;
	private String gender;
	private Long memberId;

	public static ChildrenDto of(Children children) {
		return ChildrenDto.builder()
				.id(children.getId())
				.name(children.getChildName()) 
				.gender(children.getGender())
				.age(children.getAge())
				.memberId(children.getMemberId())
				.build();
	}
}
