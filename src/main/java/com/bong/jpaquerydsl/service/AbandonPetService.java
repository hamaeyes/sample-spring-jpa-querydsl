package com.bong.jpaquerydsl.service;

import com.bong.jpaquerydsl.model.AbandonPet;

public interface AbandonPetService {

	public AbandonPet save(AbandonPet pet);
	public AbandonPet find(String id);
}
