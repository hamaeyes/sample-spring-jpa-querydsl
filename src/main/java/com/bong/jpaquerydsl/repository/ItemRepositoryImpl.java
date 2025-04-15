package com.bong.jpaquerydsl.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.bong.jpaquerydsl.domain.item.Item;

@Repository
public class ItemRepositoryImpl implements ItemRepositoryCustom {
	
	@PersistenceContext
    private EntityManager em;
	
	@Override
	public Item mergeItem(Item detachedItem) {
		return em.merge(detachedItem);
	}

}
