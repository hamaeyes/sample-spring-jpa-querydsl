package com.bong.jpaquerydsl.dto;
 

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberChildrenDto {

	private Long id;

	private String name; 
	
	private String childrenYn;
	
	private Long childrenCount; 
}
