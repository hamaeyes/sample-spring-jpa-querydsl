package com.bong.jpaquerydsl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bong.jpaquerydsl.domain.item.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> , ItemRepositoryCustom {
}
