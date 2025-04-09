package com.bong.jpaquerydsl.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.bong.jpaquerydsl.model.Person;
import com.bong.jpaquerydsl.model.QPerson;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class PersonRepositoryImpl extends QuerydslRepositorySupport implements PersonRepositoryCustom {
	
	@Autowired
	private JPAQueryFactory queryFactory;
	
    public PersonRepositoryImpl() {
        super(Person.class);
    }

    @Override
    public Optional<Person> findByEmail(String email) {
        JPAQuery<Person> query = getQuerydsl()
                .createQuery()
                .from(QPerson.person)
                .where(QPerson.person.email.equalsIgnoreCase(email))
                .setHint("org.hibernate.comment", "내용조회, PersonRepositoryImpl.findByEmail")
                .select(QPerson.person);
        return query.fetch().stream().findFirst();
    }

	@Override
	public Optional<String> findByEmailV2(String email) {
		JPAQuery<String> query = queryFactory.select(
				QPerson.person.email
		).from(QPerson.person)
		.setHint("org.hibernate.comment", "내용조회, PersonRepositoryImpl.findByEmailV2")
		.where(QPerson.person.email.equalsIgnoreCase(email));
		
		return query.fetch().stream().findFirst();
		
	}
}
