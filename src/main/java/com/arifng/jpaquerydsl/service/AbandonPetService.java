package com.arifng.jpaquerydsl.service;

import com.arifng.jpaquerydsl.model.AbandonPet;

public interface AbandonPetService {

	public AbandonPet save(AbandonPet pet);
	public AbandonPet find(String id);
}
