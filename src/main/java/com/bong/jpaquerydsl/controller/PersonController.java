package com.bong.jpaquerydsl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bong.jpaquerydsl.model.Person;
import com.bong.jpaquerydsl.service.PersonService;

import java.util.Optional;

@RestController
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping(value = "/person/{id}")
    public Person getPerson(@PathVariable("id") Long id) {
        Optional<Person> person = personService.findById(id);
        return person.orElse(null);
    }

    @GetMapping(value = "/person/email/{email}")
    public Person getByEmail(@PathVariable("email") String email) {
        Optional<Person> person = personService.findByEmail(email);
        return person.orElse(null);
    }
    
    @GetMapping(value = "/person/email/V2/{email}")
    public String getByEmailV2(@PathVariable("email") String email) {
        Optional<String> person = personService.findByEmailV2(email);
        return person.orElse(null);
    }
}
