package com.bong.jpaquerydsl.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.bong.jpaquerydsl.common.response.PagedResult;
import com.bong.jpaquerydsl.domain.Member;
import com.bong.jpaquerydsl.domain.QMember;
import com.bong.jpaquerydsl.domain.QOrder;
import com.bong.jpaquerydsl.domain.QOrderItem;
import com.bong.jpaquerydsl.domain.item.QItem;
import com.bong.jpaquerydsl.dto.MemberDto;
import com.bong.jpaquerydsl.dto.SearchDto;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class MemberRepositoryImpl extends QuerydslRepositorySupport implements MemberRepositoryCustom {

	@Autowired
	private JPAQueryFactory queryFactory;

	public MemberRepositoryImpl() {
		super(Member.class);
	}

	@Override
	public PagedResult<MemberDto> findAllBySearch(SearchDto search) {
		QMember member = QMember.member;
		QOrder order = QOrder.order; 
		QOrderItem orderItem = QOrderItem.orderItem; 
		QItem item = QItem.item;
		
		List<Member> members = queryFactory.select(member)
				.from(member)
				.leftJoin(member.orders, order).fetchJoin()
				.leftJoin(order.orderItems, orderItem).fetchJoin()
				.leftJoin(orderItem.item, item).fetchJoin()
				.setHint("org.hibernate.comment", "멤버 조회, MemberRepositoryImpl.findAllBySearch2")
				.offset(search.getPageable().getOffset())
			    .limit(search.getPageable().getPageSize())
			    .fetch();
		
		List<MemberDto> items =  members.stream()
				.map(MemberDto::of)
				.collect(Collectors.toList());
				
		long totalCount = queryFactory
			    .select(member.count())
			    .from(member) 
			    .fetchOne(); 

        return PagedResult.<MemberDto>builder()
        		.items(items)
        		.totalCount(totalCount)
        		.search(search)
                .build();  
        		
	}

}
