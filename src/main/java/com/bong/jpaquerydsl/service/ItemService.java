package com.bong.jpaquerydsl.service;

import java.util.List;

import com.bong.jpaquerydsl.domain.item.Item;

public interface ItemService {

	public Long save(Item item); 
	public Item findOne(Long id);
	public List<Item> findAll();
}
