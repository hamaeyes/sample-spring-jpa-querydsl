package com.bong.jpaquerydsl.repository;

import org.springframework.data.repository.NoRepositoryBean;

import com.bong.jpaquerydsl.model.Person;

import java.util.Optional;

@NoRepositoryBean
public interface PersonRepositoryCustom {
    Optional<Person> findByEmail(String email);
    Optional<String> findByEmailV2(String email);
}
