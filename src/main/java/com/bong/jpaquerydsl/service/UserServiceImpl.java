package com.bong.jpaquerydsl.service;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
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

	@Override
	public User findByNicknm(String nicknm) {
		if (StringUtils.isEmpty(nicknm)) {
			throw new RuntimeException("유저를 찾을 수 없습니다.");
		}
		
		Optional<User> ret = userRepository.findByNicknm(nicknm); 
		if(ret == null) {
			new RuntimeException("유저를 찾을 수 없습니다.");
		} 
		
		return ret.get();
	} 

}
