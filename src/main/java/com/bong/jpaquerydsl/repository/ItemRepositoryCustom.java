package com.bong.jpaquerydsl.repository;

import com.bong.jpaquerydsl.domain.item.Item;

public interface ItemRepositoryCustom {
    Item mergeItem(Item detachedItem);
}