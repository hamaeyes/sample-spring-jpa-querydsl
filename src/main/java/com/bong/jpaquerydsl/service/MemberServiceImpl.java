package com.bong.jpaquerydsl.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.bong.jpaquerydsl.domain.Member;
import com.bong.jpaquerydsl.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService  {

	private final MemberRepository memberRepository;

	@Override
	public Long join(Member member) {
		validateDuplicateMember(member);
		memberRepository.save(member);
		return member.getId();
		
	}
	
	private void validateDuplicateMember(Member member) {
		List<Member> members = memberRepository.findByName(member.getName());
		if(!CollectionUtils.isEmpty(members)) { 
			throw new IllegalStateException("이미 존재하는 회원입니다.");
		}
	}

	@Override
	public List<Member> findMembers() {
		return memberRepository.findAll();
	}

	@Override
	public Member findOne(Long memberId) {
		return memberRepository.findById(memberId).orElse(null);
	}
}
