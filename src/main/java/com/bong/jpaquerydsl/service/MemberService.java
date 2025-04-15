package com.bong.jpaquerydsl.service;

import java.util.List;

import com.bong.jpaquerydsl.domain.Member;

public interface MemberService {

	public Long join(Member member);
	public List<Member> findMembers();
	public Member findOne(Long memberId);
}
