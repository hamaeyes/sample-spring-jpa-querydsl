package com.bong.jpaquerydsl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bong.jpaquerydsl.domain.Address;
import com.bong.jpaquerydsl.domain.Member;
import com.bong.jpaquerydsl.service.ItemService;
import com.bong.jpaquerydsl.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	MemberService memberService;
	
	@Autowired
	ItemService itemService;

//	@RequestMapping(value = "/members/new", method = RequestMethod.GET)
//	public String createForm() {
//		return "members/createMemberForm";
//	}

	@RequestMapping(value = "/members/new", method = RequestMethod.POST)
	public @ResponseBody Long create(Member member, Address address) {

		//Address address = new Address(city, street, zipcode);
		member.setAddress(address);
		return memberService.join(member);
		//return "redirect:/";
	}

	@RequestMapping(value = "/members", method = RequestMethod.GET)
	public @ResponseBody List<Member> list(Model model) {

		List<Member> members = memberService.findMembers();
		System.err.println("member.order" + members.get(0).getOrders());
		return members;
		//model.addAttribute("members", members);
		//return "members/memberList";
	}
}
//{
//	  "name": "최봉기",
//	  "city": "Seoul",
//	  "street": "Wall Street",
//	  "zipcode": "033120"
//	}