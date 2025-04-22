package com.bong.jpaquerydsl.repository;

import com.bong.jpaquerydsl.common.response.PagedResult;
import com.bong.jpaquerydsl.dto.MemberDto;
import com.bong.jpaquerydsl.dto.SearchDto;

public interface MemberRepositoryCustom {
	public PagedResult<MemberDto> findAllBySearch(SearchDto search);
}