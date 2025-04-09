package com.bong.jpaquerydsl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.bong.jpaquerydsl.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>,
        QuerydslPredicateExecutor<Person>, PersonRepositoryCustom {
}
