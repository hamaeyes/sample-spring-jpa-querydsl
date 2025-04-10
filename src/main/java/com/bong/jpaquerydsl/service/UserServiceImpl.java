package com.bong.jpaquerydsl.service;

import org.springframework.stereotype.Service;

import com.bong.jpaquerydsl.model.User;
import com.bong.jpaquerydsl.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	
	@Override
	public User save(User user) {
		userRepository.save(user);
		return userRepository.findByNicknm(user.getNicknm()).orElse(null);
		
	}

}
