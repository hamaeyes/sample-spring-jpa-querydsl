package com.bong.jpaquerydsl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bong.jpaquerydsl.common.response.PagedResult;
import com.bong.jpaquerydsl.domain.Address;
import com.bong.jpaquerydsl.domain.Member;
import com.bong.jpaquerydsl.dto.MemberChildrenDto;
import com.bong.jpaquerydsl.dto.MemberDto;
import com.bong.jpaquerydsl.dto.SearchDto;
import com.bong.jpaquerydsl.service.ItemService;
import com.bong.jpaquerydsl.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	MemberService memberService;
	
	@Autowired
	ItemService itemService;

	/**
	 * 맴버 등록 
	 * @param member
	 * @param address
	 * @return
	 */
	@RequestMapping(value = "/members/regist", method = RequestMethod.POST)
	public @ResponseBody Long create(Member member, Address address) {

		member.setAddress(address);
		return memberService.join(member);
	}

	/**
	 *  맴버 전체 조회 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/members/list/all", method = RequestMethod.GET)
	public @ResponseBody List<Member> list(Model model) {

		List<Member> members = memberService.findMembers();
		return members;
	}
	
	/**
	 * 맴버 전체 조회 - 페이징 - jpa 방식 
	 * @param search
	 * @return
	 */
	@RequestMapping(value = "/member/list/paging-jpa", method = RequestMethod.GET)
	public @ResponseBody PagedResult<MemberDto> search1(SearchDto search) {

		return memberService.findMembersPageing(search);
	}
	
	/**
	 * 맴버 전체 조회 - 페이징 - querydsl 방식 
	 * @param search
	 * @return
	 */
	@RequestMapping(value = "/member/list/paging-querydsl", method = RequestMethod.GET)
	public @ResponseBody PagedResult<MemberDto> search2(SearchDto search) {

		return memberService.findAllBySearch(search);
	}
	
	/**
	 * 맴버 정보 생성 - 샘플 데이터 
	 * @param model
	 */
	@RequestMapping(value = "/member/generater/sample", method = RequestMethod.GET)
	public @ResponseBody void generate(Model model) {

		memberService.saveBulk();
		return; 
	}
	
	/**
	 * 맴버 단건 조회 - 비 연관관계 테이블 조인.
	 * @param memberId
	 * @return
	 */
	@RequestMapping(value = "/member/children/{memberId}", method = RequestMethod.GET)
	public @ResponseBody MemberDto memberWithChildren(@PathVariable Long memberId) {

		MemberDto memberDto = memberService.findMemberWithChildrenById(memberId);
		return memberDto;
	}
	
	@RequestMapping(value = "/member/projections/{memberId}", method = RequestMethod.GET)
	public @ResponseBody MemberDto findMemberOnlyById(@PathVariable Long memberId) {

		MemberDto memberDto = memberService.findMemberOnlyById(memberId).orElse(null);
		return memberDto;
	}
	
	
	/**
	 * 멤버 단건 조회 - 비 연관관계 테이블 조인. - 서브쿼리 추가 
	 * @param memberId
	 * @return
	 */
	@RequestMapping(value = "/member/subquery-select/{memberId}", method = RequestMethod.GET)
	public @ResponseBody MemberChildrenDto memberWithChildrenSelectSubQuery(@PathVariable Long memberId) {

		MemberChildrenDto resultDto = memberService.findMemberWithChildrenSelectSubQueryById(memberId).orElse(null);
		return resultDto;
	}
	
	/**
	 * 멤버 단건 조회 - 비 연관관계 테이블 조인. - 서브쿼리 추가 
	 * @param memberId
	 * @return
	 */
	@RequestMapping(value = "/member/subquery-where", method = RequestMethod.GET)
	public @ResponseBody List<MemberChildrenDto> memberWithChildrenWhereSubQuery() {

		return memberService.findMemberWithChildrenWhereSubQueryById(); 
	}
	
}