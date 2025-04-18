package com.bong.jpaquerydsl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bong.jpaquerydsl.model.AbandonPet;

@Repository
public interface AbandonPetRepository extends JpaRepository<AbandonPet, String> {
	
}