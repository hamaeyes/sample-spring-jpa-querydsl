package com.bong.jpaquerydsl.repository;

import java.util.List;
import java.util.Optional;

import com.bong.jpaquerydsl.common.response.PagedResult;
import com.bong.jpaquerydsl.dto.MemberChildrenDto;
import com.bong.jpaquerydsl.dto.MemberDto;
import com.bong.jpaquerydsl.dto.SearchDto;

public interface MemberRepositoryCustom {
	/**
	 * Member를 조회하되 의존관계에 있는 엔티티도 조회한다.
	 * 
	 * @param search
	 * @return
	 */
	public PagedResult<MemberDto> findAllBySearch(SearchDto search);
	
	public MemberDto findMemberWithChildrenById(long memberId);
	public Optional<MemberChildrenDto> findMemberWithChildrenSelectSubQueryById(long memberId);
	public List<MemberChildrenDto> findMemberWithChildrenWhereSubQueryById();
	
	public Optional<MemberDto> findMemberOnlyById1(long memberId);
	public Optional<MemberDto> findMemberOnlyById2(long memberId);
	public Optional<MemberDto> findMemberOnlyById3(long memberId);
}