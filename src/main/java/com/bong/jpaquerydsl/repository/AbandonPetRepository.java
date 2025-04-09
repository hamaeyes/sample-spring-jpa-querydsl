package com.bong.jpaquerydsl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bong.jpaquerydsl.model.AbandonPet;

public interface AbandonPetRepository extends JpaRepository<AbandonPet, String> {
}