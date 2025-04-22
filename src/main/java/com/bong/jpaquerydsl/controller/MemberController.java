package com.bong.jpaquerydsl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bong.jpaquerydsl.common.response.PagedResult;
import com.bong.jpaquerydsl.domain.Address;
import com.bong.jpaquerydsl.domain.Member;
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

	@RequestMapping(value = "/members/new", method = RequestMethod.POST)
	public @ResponseBody Long create(Member member, Address address) {

		member.setAddress(address);
		return memberService.join(member);
	}

	@RequestMapping(value = "/members", method = RequestMethod.GET)
	public @ResponseBody List<Member> list(Model model) {

		List<Member> members = memberService.findMembers();
		return members;
	}
	
	@RequestMapping(value = "/member/search1", method = RequestMethod.GET)
	public @ResponseBody PagedResult<MemberDto> search1(SearchDto search) {

		return memberService.findMembersPageing(search);
	}
	
	@RequestMapping(value = "/member/search2", method = RequestMethod.GET)
	public @ResponseBody PagedResult<MemberDto> search2(SearchDto search) {

		return memberService.findAllBySearch(search);
	}
	
	@RequestMapping(value = "/member/generate", method = RequestMethod.GET)
	public @ResponseBody void generate(Model model) {

		memberService.saveBulk();
		return; 
	}
	
}