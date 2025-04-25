package com.bong.jpaquerydsl.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.bong.jpaquerydsl.common.response.PagedResult;
import com.bong.jpaquerydsl.domain.Children;
import com.bong.jpaquerydsl.domain.Member;
import com.bong.jpaquerydsl.domain.QChildren;
import com.bong.jpaquerydsl.domain.QMember;
import com.bong.jpaquerydsl.domain.QOrder;
import com.bong.jpaquerydsl.domain.QOrderItem;
import com.bong.jpaquerydsl.domain.item.QItem;
import com.bong.jpaquerydsl.dto.AddressDto;
import com.bong.jpaquerydsl.dto.ChildrenDto;
import com.bong.jpaquerydsl.dto.MemberChildrenDto;
import com.bong.jpaquerydsl.dto.MemberDto;
import com.bong.jpaquerydsl.dto.SearchDto;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
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

	@Override
	public MemberDto findMemberWithChildrenById(long memberId) {
		QMember qMember = QMember.member;
		QChildren qChildren = QChildren.children;
		
		// 1. Member - Children를 조인해서 Tuple로 전체 조회. 
		JPAQuery<Tuple> query = queryFactory.select(qMember, qChildren)
			.from(qMember)
			.leftJoin(qChildren).on(qChildren.memberId.eq(qMember.id))
			.where(qMember.id.eq(memberId));
		
		List<Tuple> tuples = query.fetch();
		
		Map<Long, MemberDto> grouped = new LinkedHashMap<>();
		
		// 2. Java코드로 1:N 구조로 그룹핑.
		for(Tuple tuple: tuples) {
			Member member = tuple.get(qMember);
			Children children = tuple.get(qChildren);

			grouped.computeIfAbsent(memberId, id ->
				new MemberDto(id, member.getName(), AddressDto.of(member.getAddress()), new ArrayList<>(), new ArrayList<>())
			).getChildrens().add(ChildrenDto.of(children));
		}
		
		// 3. 응답 데이터 리턴.
		List<MemberDto> list = new ArrayList<>(grouped.values()); 
		if(!CollectionUtils.isEmpty(list)) {
			return list.get(0);
		}
		
		return null; 
	}
	
	
	@Override
	public Optional<MemberChildrenDto> findMemberWithChildrenSelectSubQueryById(long memberId) {
		QMember qMember = QMember.member;
		QChildren qChildren = QChildren.children;
		
		// 1. Member - Children를 조인해서 Tuple로 전체 조회. 
		JPAQuery<MemberChildrenDto> query = queryFactory.select(
				Projections.fields(MemberChildrenDto.class, 
						qMember.id.as("id"),
						qMember.name.as("name"),
						Expressions.as(
							JPAExpressions
								.select(qChildren.id.count())
								.from(qChildren)
								.where(qChildren.memberId.eq(qMember.id))
						, "childrenCount"),
						Expressions.cases().when(
							JPAExpressions
								.select(qChildren.id.count())
								.from(qChildren)
								.where(qChildren.memberId.eq(qMember.id))
								.gt(0L)).then("Y").otherwise("N")
						.as("childrenYn")
						
					)
			)
			.from(qMember)
			.leftJoin(qChildren).on(qChildren.memberId.eq(qMember.id))
			.where(qMember.id.eq(memberId));
		
		return Optional.of(query.fetchFirst());
		
	}
	
	@Override
	public List<MemberChildrenDto> findMemberWithChildrenWhereSubQueryById() {
		QMember qMember = QMember.member;
		QChildren qChildren = QChildren.children;
		
		JPAQuery<MemberChildrenDto> query = queryFactory.select(
				Projections.fields(MemberChildrenDto.class, 
						qMember.id.as("id"),
						qMember.name.as("name"),
						Expressions.as(
							JPAExpressions
								.select(qChildren.id.count())
								.from(qChildren)
								.where(qChildren.memberId.eq(qMember.id))
						, "childrenCount"),
						Expressions.cases().when(
							JPAExpressions
								.select(qChildren.id.count())
								.from(qChildren)
								.where(qChildren.memberId.eq(qMember.id))
								.gt(0L)).then("Y").otherwise("N")
						.as("childrenYn")
						
					)
			)
			.from(qMember) 
			.where(
					qMember.id.in(
							JPAExpressions
							.select(qChildren.memberId).distinct()
							.from(qChildren)
							.where(qChildren.gender.eq("F"))
							)
					
			); 
		
		return query.fetch();
	}
	

	@Override
	public Optional<MemberDto> findMemberOnlyById1(long memberId) {
		// 생성자로 Projections 하기 
		QMember qMember = QMember.member;
		System.out.println(Arrays.toString(MemberDto.class.getConstructors()));
		System.out.println(Arrays.toString(AddressDto.class.getConstructors()));
		 
		JPAQuery<MemberDto> query = queryFactory.select(
				Projections.constructor(MemberDto.class,
					qMember.id,
					qMember.name,
					Projections.constructor(AddressDto.class,
					        qMember.address.city,
					        qMember.address.street,
					        qMember.address.zipcode
					    )
					,
					Expressions.constant(List.of()),
					Expressions.constant(List.of())
					)
				)
				.from(qMember)
				.where(qMember.id.eq(memberId))
				.setHint("org.hibernate.comment", "멤버 조회, MemberRepositoryImpl.findMemberOnlyById")
				.orderBy(qMember.name.desc())
				.limit(1);  
		
		return Optional.of(query.fetchFirst());
	}
	
	@Override
	public Optional<MemberDto> findMemberOnlyById2(long memberId) {
		// 필드명으로 Projections 하기 
		QMember qMember = QMember.member;
		System.out.println(Arrays.toString(MemberDto.class.getConstructors()));
		System.out.println(Arrays.toString(AddressDto.class.getConstructors()));
		 
		JPAQuery<MemberDto> query = queryFactory.select(
				Projections.fields(MemberDto.class,
					qMember.id.as("id"),
					qMember.name.as("name"),
					Projections.fields(AddressDto.class,
					        qMember.address.city.as("city"),
					        qMember.address.street.as("street"),
					        qMember.address.zipcode.as("zipcode")
					    ).as("address")
					//,
					//ExpressionUtils.as(Expressions.constant(List.of()), "orders"),
					//ExpressionUtils.as(Expressions.constant(List.of()), "childrens")
					)
				)
				.from(qMember)
				.where(qMember.id.eq(memberId))
				.setHint("org.hibernate.comment", "멤버 조회, MemberRepositoryImpl.findMemberOnlyById")
				.orderBy(qMember.name.desc())
				.limit(1); 
		return Optional.of(query.fetchFirst());
	}
	
	@Override
	public Optional<MemberDto> findMemberOnlyById3(long memberId) {
		// Setter 메소드로 Projections 하기 
		QMember qMember = QMember.member;
		System.out.println(Arrays.toString(MemberDto.class.getConstructors()));
		System.out.println(Arrays.toString(AddressDto.class.getConstructors())); 
		
		JPAQuery<MemberDto> query = queryFactory.select(
				Projections.bean(MemberDto.class,
					qMember.id,
					qMember.name,
					Projections.bean(AddressDto.class,
					        qMember.address.city,
					        qMember.address.street,
					        qMember.address.zipcode
					    ).as("address")
					)
				)
				.from(qMember)
				.where(qMember.id.eq(memberId))
				.setHint("org.hibernate.comment", "멤버 조회, MemberRepositoryImpl.findMemberOnlyById")
				.orderBy(qMember.name.desc())
				.limit(1); 
		
		return Optional.of(query.fetchFirst());
	}

	

	

}
