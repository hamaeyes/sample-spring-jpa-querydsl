package com.bong.jpaquerydsl.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.bong.jpaquerydsl.domain.item.Item;
import com.bong.jpaquerydsl.repository.ItemRepository;

import lombok.RequiredArgsConstructor;


@Transactional
@RequiredArgsConstructor
@Service
public class ItemServiceImpl implements ItemService {

	private final ItemRepository itemRepository;

	@Override
	public Long save(Item item) {
		if(item.getId() == null) {
			itemRepository.save(item);			
		}else {
			itemRepository.mergeItem(item);
		}
		return item.getId();
	}

	@Override
	public Item findOne(Long id) {
		return itemRepository.findById(id).orElse(null);
	}

	@Override
	public List<Item> findAll() {
		return itemRepository.findAll();
	}

	 
}
