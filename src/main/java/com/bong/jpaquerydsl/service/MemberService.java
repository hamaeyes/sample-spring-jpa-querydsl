package com.bong.jpaquerydsl.service;

import java.util.List;

import com.bong.jpaquerydsl.common.response.PagedResult;
import com.bong.jpaquerydsl.domain.Member;
import com.bong.jpaquerydsl.dto.MemberDto;
import com.bong.jpaquerydsl.dto.SearchDto;

public interface MemberService {

	public Long join(Member member);
	public List<Member> findMembers();
	public Member findOne(Long memberId);
	public void saveBulk();

	public PagedResult<MemberDto> findMembersPageing(SearchDto search);
	public PagedResult<MemberDto> findAllBySearch(SearchDto search);
	
	public MemberDto findMemberWithChildrenById(Long memberId);
}
