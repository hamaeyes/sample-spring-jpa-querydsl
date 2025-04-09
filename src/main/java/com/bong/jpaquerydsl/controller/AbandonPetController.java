package com.bong.jpaquerydsl.controller;

import java.time.LocalDateTime;
import java.util.EnumSet;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bong.jpaquerydsl.common.enums.Characteristic;
import com.bong.jpaquerydsl.model.AbandonPet;
import com.bong.jpaquerydsl.service.AbandonPetService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class AbandonPetController {

	private final AbandonPetService abandonPetService;
	
	// 저장 - 컨버터 이용
	@GetMapping(value = "/abandon")
	public void save() {
		 AbandonPet abandon = new AbandonPet();
//		 abandon.setId("10000002");
//		 abandon.setCareNm("Hi");  
//		 abandon.setColorCds(EnumSet.of(Characteristic.a, Characteristic.b));
//		 abandon.setCreateDt(LocalDateTime.now());
		 abandonPetService.save(abandon);
		 
	 }
	
	// 조회 - 컨버터이용 
	@GetMapping(value = "/abandon/{id}")
	public AbandonPet getId(@PathVariable String id) { 
		 System.err.println("id ===> " +  id);
		 return abandonPetService.find(id);
		 
	 }
}
