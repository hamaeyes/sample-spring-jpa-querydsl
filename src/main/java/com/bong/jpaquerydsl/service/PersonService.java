package com.bong.jpaquerydsl.service;

import java.util.Optional;

import com.bong.jpaquerydsl.model.Person;

public interface PersonService {
    Optional<Person> findById(Long id);

    Optional<Person> findByEmail(String email);
    
    Optional<String> findByEmailV2(String email);
}
