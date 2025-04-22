package com.bong.jpaquerydsl.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.bong.jpaquerydsl.common.response.PagedResult;
import com.bong.jpaquerydsl.domain.Address;
import com.bong.jpaquerydsl.domain.Delivery;
import com.bong.jpaquerydsl.domain.DeliveryStatus;
import com.bong.jpaquerydsl.domain.Member;
import com.bong.jpaquerydsl.domain.Order;
import com.bong.jpaquerydsl.domain.OrderItem;
import com.bong.jpaquerydsl.domain.item.Book;
import com.bong.jpaquerydsl.dto.MemberDto;
import com.bong.jpaquerydsl.dto.SearchDto;
import com.bong.jpaquerydsl.repository.ItemRepository;
import com.bong.jpaquerydsl.repository.MemberRepository;
import com.bong.jpaquerydsl.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService  {

	private final MemberRepository memberRepository;
	private final ItemRepository itemRepository; 
	private final OrderRepository orderRepository;

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

	@Override
	public void saveBulk() {
		
		// 주소 
		Address address = new Address();
		address.setCity("Seoul");
		address.setStreet("강서구 허준로");
		address.setZipcode("533890");
		
		// 아이템 
		Book item = new Book();
		item.setAuthor("김순동");
		item.setIsbn("ABCDFFFE");
		item.setName("순자는 무엇을 했는가?");
		item.setPrice(30_000);
		item.setStockQuantity(15);
		itemRepository.save(item);
		
		
		Delivery delivery = new Delivery();
		delivery.setAddress(address);
		delivery.setStatus(DeliveryStatus.READY);

		// 100명의 유저 
		for(int i=100; i< 200; i++) {
			Member member = new Member();
			member.setName("홍길동"+i);
			member.setAddress(address);
			
			if( i< 110) {
				 
				OrderItem orderItem = new OrderItem();
				orderItem.setCount(1);
				orderItem.setItem(item);
				
				Order order = Order.createOrder(member, delivery, orderItem);
				member.setOrders(Arrays.asList(order));
				
				orderRepository.save(order);
				
			}
			
			memberRepository.save(member);
		}
		
	}

	@Override
	public PagedResult<MemberDto> findMembersPageing(SearchDto search) {
		Page<Member> result =  memberRepository.findAll(search.getPageable());
		List<MemberDto> items = result
				.stream()
				.map(MemberDto::of)
				.collect(Collectors.toList());
		
		 return PagedResult.<MemberDto>builder()
	                .items(items)
	                .totalCount(Long.valueOf(result.getTotalElements()).intValue())
	                .search(search)
	                .build();
	}

	@Override
	public PagedResult<MemberDto> findAllBySearch(SearchDto search) {
		return memberRepository.findAllBySearch(search);
	}
}
