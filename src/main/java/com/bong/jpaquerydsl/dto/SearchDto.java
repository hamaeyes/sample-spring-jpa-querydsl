package com.bong.jpaquerydsl.dto;

import org.springframework.data.domain.Pageable;

import com.bong.jpaquerydsl.dto.request.OffsetBasedPageRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SearchDto {
	
	@Builder.Default
	private int offset = 0;
	
	@Builder.Default
	private int limit = 15;    

	@JsonIgnore
	public Pageable getPageable() {
        return OffsetBasedPageRequest.of(this.offset, this.limit);
    }
	
}
