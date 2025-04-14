package com.bong.jpaquerydsl.repository;

import org.springframework.data.repository.NoRepositoryBean;

import com.bong.jpaquerydsl.model.User;

@NoRepositoryBean
public interface UserRepositoryCustom {
	public User findCustom(String nick);
}
