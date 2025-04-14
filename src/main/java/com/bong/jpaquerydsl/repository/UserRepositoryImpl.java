package com.bong.jpaquerydsl.repository;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.bong.jpaquerydsl.model.QUser;
import com.bong.jpaquerydsl.model.User;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	public UserRepositoryImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}

	@Override
	public User findCustom(String nick) {
		return queryFactory.selectFrom(QUser.user)
	            .where(QUser.user.nicknm.eq(nick))
	            .fetchOne();
	}

}
