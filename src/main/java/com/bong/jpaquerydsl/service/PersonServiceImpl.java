package com.bong.jpaquerydsl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bong.jpaquerydsl.model.Person;
import com.bong.jpaquerydsl.repository.PersonRepository;

import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonRepository personRepository;

    @Override
    public Optional<Person> findById(Long id) {
        return personRepository.findById(id);
    }

    @Override
    public Optional<Person> findByEmail(String email) {
        return personRepository.findByEmail(email);
    }

	@Override
	public Optional<String> findByEmailV2(String email) {
		return personRepository.findByEmailV2(email);
	}
}
