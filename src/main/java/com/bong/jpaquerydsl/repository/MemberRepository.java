package com.bong.jpaquerydsl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bong.jpaquerydsl.domain.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {
	
	// 메소드 명으로 쿼리를 만든다. 
	public List<Member> findByName(String name);
}
