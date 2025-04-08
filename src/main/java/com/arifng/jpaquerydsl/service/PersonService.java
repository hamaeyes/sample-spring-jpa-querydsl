package com.arifng.jpaquerydsl.service;

import com.arifng.jpaquerydsl.model.Person;

import java.util.Optional;

public interface PersonService {
    Optional<Person> findById(Long id);

    Optional<Person> findByEmail(String email);
    
    Optional<String> findByEmailV2(String email);
}
