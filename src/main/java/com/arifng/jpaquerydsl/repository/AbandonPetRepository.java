package com.arifng.jpaquerydsl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arifng.jpaquerydsl.model.AbandonPet;

public interface AbandonPetRepository extends JpaRepository<AbandonPet, String> {
}