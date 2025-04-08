package com.arifng.jpaquerydsl.service;

import org.springframework.stereotype.Service;

import com.arifng.jpaquerydsl.model.AbandonPet;
import com.arifng.jpaquerydsl.repository.AbandonPetRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AbandonPetServiceImpl implements AbandonPetService {
	
	
	private final AbandonPetRepository abandonPetRepository;

	@Override
	public AbandonPet save(AbandonPet pet) {
		abandonPetRepository.save(pet);
		
		return null;
	}

	@Override
	public AbandonPet find(String id) {
		return abandonPetRepository.findById(id).orElse(null);
	}

}
